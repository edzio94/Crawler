package mainpackage.datas;

import org.springframework.stereotype.Component;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lukasz on 12.01.16.
 */

@Component
public class DataHandler {
    public List<Links> linksToDatabase;
    public List<Links> linksToSearch;

    public List<Data> dataFromWebsites;

    public int dataCounter;
    public DataHandler()
    {
        dataCounter = 0;
        linksToDatabase = new ArrayList<>();
        linksToSearch = new ArrayList<>();
        dataFromWebsites = new ArrayList<>();

    }
}
