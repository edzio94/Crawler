package mainpackage.spring;

import mainpackage.databases.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lukasz on 03.01.16.
 */
@Configuration
@ComponentScan(basePackages = "mainpackage")
@EnableWebMvc
@EnableAsync
@EnableScheduling
//@PropertySource("classpath:app.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

    public static final String DRIVER = "org.postgresql.Driver";


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Bean(destroyMethod = "close")
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "clawlerdb","clawler");
    }


    @Bean
    public javax.sql.DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("clawlerdb");
        ds.setPassword("clawler");

        return ds;
    }


    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();

        pool.setMaxPoolSize(100);
        pool.setCorePoolSize(5);
        pool.setQueueCapacity(100);
        //pool.setKeepAliveSeconds(4);
        pool.setWaitForTasksToCompleteOnShutdown(true);


        return pool;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(javax.sql.DataSource dataSource) {
        return new JdbcTemplate( dataSource);
    }

  /*  @Bean
    public TransactionManager transactionManager()
    {
        TransactionManager t = new TransactionManager();

    }*/

}
