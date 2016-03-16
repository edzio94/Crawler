package mainpackage.parser;

import mainpackage.databases.JdbcDataDAO;
import mainpackage.datas.Data;
import mainpackage.datas.DataHandler;
import mainpackage.datas.Links;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 03.01.16.
 */

public class WebParser {

    public Document doc;


    public WebParser(String URL) {
        String tmpURL;
        if (!URL.startsWith("http"))
            tmpURL = "http://" + URL;
        else
            tmpURL = URL;

        try {
            doc = Jsoup.connect(tmpURL).get();
        } catch (IOException e) {
            System.out.println("QQQ");
        }
    }

    public WebParser() {

    }

    public List<Links> getLinks() {
        List<Links> links = new ArrayList<>();
        Elements x = doc.select("a");
        x.forEach((link) -> {
            if (link.attr("abs:href").length() < 250 && link.attr("abs:href").contains("www"))
                links.add(new Links(Links.counter++, link.attr("abs:href")));//x.toString()))
        });

        return links;
    }

    public List<Data> getData() {
        String[] sentences = doc.body().text().toString().split("\\.");
        List<Data> data = new ArrayList<>();
        for (String sentence : sentences) {
            if (sentence.toLowerCase().contains("zeus") && sentence.length() < 2000) {
                data.add(
                        new Data(Data.counter++, sentence));

            }
        }
        return data;
    }

}
