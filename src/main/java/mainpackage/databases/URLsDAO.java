package mainpackage.databases;

import mainpackage.datas.Links;

import java.util.List;

/**
 * Created by lukasz on 12.01.16.
 */
public interface URLsDAO {
    String DATA_SELECT = "SELECT * FROM urls WHERE is_used IS FALSE";
    String FILL_WITH_TRUE = "UPDATE urls SET is_used = true WHERE is_used = false";

    String ADD_TO_TABLE = "INSERT INTO urls(url) SELECT (?) WHERE NOT EXISTS(" +
            "SELECT url FROM urls WHERE url = (?))";

    List<Links> getLinks();

    void addLinks(List<Links> links);

    void updateTable();



}
