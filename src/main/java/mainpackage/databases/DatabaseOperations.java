package mainpackage.databases;

import mainpackage.datas.Data;
import mainpackage.datas.DataHandler;
import mainpackage.datas.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

/**
 * Created by lukasz on 12.01.16.
 */
@Component
public class DatabaseOperations  {
    @Autowired
    private JdbcURLsDAO urlTemplate;

    @Autowired
    private JdbcDataDAO dataTemplate;

    @Autowired
    private DataHandler dataHandler;

    public DatabaseOperations(){

    }


    public List<Data> getDataFromDatabase()
    {
        return dataTemplate.getAll();
    }


    public void saveLinksIntoDatabase()
    {

        if(!dataHandler.linksToDatabase.isEmpty()) {
            System.out.println("Size: of dataHandler"+dataHandler
                    .linksToDatabase.size());
            urlTemplate.addLinks(dataHandler.linksToDatabase);
            System.out.println("AFTER ADD LINKS");
        }

    }

    public void saveDataIntoDabase()
    {
        dataTemplate.insert(dataHandler.dataFromWebsites);
    }

    public void updateDatabase()
    {
        urlTemplate.updateTable();
    }




    public List<Links> getLinksFromDatabase()
    {
        return urlTemplate.getLinks();
    }

}
