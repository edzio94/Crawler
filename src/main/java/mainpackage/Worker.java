package mainpackage;

import mainpackage.components.Statistics;
import mainpackage.databases.DatabaseOperations;

import mainpackage.databases.TransactionManager;
import mainpackage.datas.DataHandler;
import mainpackage.datas.Links;
import mainpackage.searcher.FirstResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by lukasz on 03.01.16.
 */
@Component
public class Worker {
    @Autowired
    Statistics statistics;

    @Autowired
    DatabaseOperations operations;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    TransactionManager transactionManager;

    @Autowired
    DataHandler dataHandler;


    FirstResults firstResults;


    public Worker() {

    }

    @Async
    public void run() {
        for (; ; ) {
            for (Links l : dataHandler.linksToSearch) {
                threadPoolTaskExecutor.execute(new HTMLWorker(l.getUrl(), dataHandler));
            }

            dataHandler.linksToSearch.clear();

            transactionManager.SaveAndUpdate();

            dataHandler.dataFromWebsites.clear();
            dataHandler.linksToDatabase.clear();
        }
    }

    public void startFirstSearch(String word) {
        firstResults = new FirstResults(word);
        firstResults.getConnection();
        dataHandler.linksToDatabase = firstResults.getLinksList();
    }


}
