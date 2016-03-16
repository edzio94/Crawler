package mainpackage.controllers;

import mainpackage.Worker;
import mainpackage.databases.DatabaseOperations;
import mainpackage.databases.Phrases;
import mainpackage.datas.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lukasz on 03.01.16.
 */
@RestController
public class URLController {
    @Autowired
    Worker w;

    @Autowired
    DatabaseOperations operations;

    @RequestMapping("/test")
    public String getStarted()//(@RequestBody Phrases phrases)
    {

        w.startFirstSearch("Zeus");
        w.run();

        return "Crawler started working for word. ";//phrases.getBasePhrase();
    }

    @RequestMapping("/getData")
    public List<Data> fetchData() {
        return operations.getDataFromDatabase();
    }


}
