package mainpackage;

import mainpackage.databases.DatabaseOperations;
import mainpackage.datas.Data;
import mainpackage.datas.DataHandler;
import mainpackage.datas.Links;
import mainpackage.parser.WebParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 14.01.16.
 */

public class HTMLWorker implements Runnable{



    DataHandler dataHandler;



    private WebParser parser;


    public HTMLWorker(String URL,DataHandler dataHandler)
    {
        this.parser = new WebParser(URL);
        this.dataHandler = dataHandler;
        System.out.println("Link to parse: "+URL);


    }

    public HTMLWorker(){}

    @Override
    public void run() {
        dataHandler.linksToDatabase.addAll(parser.getLinks());

        dataHandler.dataFromWebsites.addAll(parser.getData());
    }


}
