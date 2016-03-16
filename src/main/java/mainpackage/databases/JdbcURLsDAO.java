package mainpackage.databases;

import mainpackage.datas.DataHandler;
import mainpackage.datas.Links;
import mainpackage.datas.LinksMapper;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lukasz on 12.01.16.
 */
@Repository
public class JdbcURLsDAO implements URLsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Links> getLinks() {

        return jdbcTemplate.query(DATA_SELECT, new LinksMapper());
    }

    @Override
    public void addLinks(List<Links> links) {
        try {
            jdbcTemplate.batchUpdate(ADD_TO_TABLE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {

                    Links tmp = links.get(i);
                    preparedStatement.setString(1, tmp.getUrl());
                    preparedStatement.setString(2, tmp.getUrl());

                    preparedStatement.executeUpdate();


                }

                @Override
                public int getBatchSize() {
                    return links.size();
                }
            });
        } catch (DuplicateKeyException e) {
            System.out.println("Url already in database. Skipped");
        }

    }

    @Override
    public void updateTable() {
        jdbcTemplate.execute(FILL_WITH_TRUE);
    }

    public void huj() {
        jdbcTemplate.execute("SELECT * FROM urls");
    }


}
