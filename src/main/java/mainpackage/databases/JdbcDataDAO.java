package mainpackage.databases;

import mainpackage.datas.Data;
import mainpackage.datas.DataMapper;
import mainpackage.datas.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lukasz on 09.01.16.
 */
@Repository
public class JdbcDataDAO implements DataDAO {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Data> getAll() {
        return template.query(DATA_SELECT, new DataMapper());
    }

    @Override
    public void insert(List<Data> datas) {
        try {
            template.batchUpdate(ADD_TO_TABLE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Data tmp = datas.get(i);

                    preparedStatement.setString(1, tmp.getText());

                    preparedStatement.executeUpdate();
                }

                @Override
                public int getBatchSize() {
                    return datas.size();
                }
            });
        } catch (DuplicateKeyException e)
        {
            System.out.println("Text already in Database. Skipped.");

        }

    }
}
