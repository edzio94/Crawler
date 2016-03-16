package mainpackage.searcher;

import mainpackage.datas.Links;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lukasz on 12.01.16.
 */
public class FirstResults {
    String request;
    private List<Links> linksList;
    Document doc;

    public FirstResults(String request) {
        linksList = new ArrayList<>();
        this.request = "https://www.google.com/search?q="
                + request + "&num=30";
    }

    public void getConnection() {
        System.out.println("Sending request");
        try {
            doc = Jsoup.connect(request)
                    .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLinks();
    }

    public void getLinks() {
        Elements links = doc.select("a[href]");
        for (org.jsoup.nodes.Element x : links) {
            String tmp = x.attr("href");
            if (tmp.startsWith("/url?q=") && (!tmp.contains("google"))) {
                String[] q = tmp.split("\\&");
                String g = q[0].substring(7).trim();
                linksList.add(new Links(Links.counter++, g.toString()));
            }
        }

    }

    public List<Links> getLinksList() {
        return linksList;
    }

}
