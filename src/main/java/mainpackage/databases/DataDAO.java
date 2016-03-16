package mainpackage.databases;

import mainpackage.datas.Data;

import java.util.List;

/**
 * Created by lukasz on 09.01.16.
 */
public interface DataDAO {
    String DATA_SELECT = "SELECT * FROM datas";
    String ADD_TO_TABLE = "INSERT INTO datas(text) VALUES(?)";

    List<Data> getAll();

    void insert (List<Data> datas);
}
