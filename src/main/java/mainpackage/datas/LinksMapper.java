package mainpackage.datas;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lukasz on 09.01.16.
 */
public class LinksMapper implements RowMapper<Links>{
    @Override
    public Links mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Links(resultSet.getInt("id"),resultSet.getString("URL"));
    }
}
