package mainpackage.datas;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lukasz on 09.01.16.
 */
public class DataMapper implements RowMapper<Data>{
    @Override
    public Data mapRow(ResultSet resultSet, int i) throws SQLException {
        return  new Data(resultSet.getInt("id"), resultSet.getString("text"));
    }
}
