package mainpackage.databases;

import mainpackage.datas.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lukasz on 16.01.16.
 */
@Component
public class TransactionManager {

    @Autowired
    DatabaseOperations databaseOperations;

    public TransactionManager(){}

    @Autowired
    DataHandler handler;


    @Transactional
    public void SaveAndUpdate()
    {
        databaseOperations.saveLinksIntoDatabase();
        databaseOperations.saveDataIntoDabase();
        handler.linksToSearch = databaseOperations.getLinksFromDatabase();
        databaseOperations.updateDatabase();
    }


}
