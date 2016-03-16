package mainpackage.components;

import org.springframework.stereotype.Component;

/**
 * Created by lukasz on 12.01.16.
 */
@Component
public class Statistics {
    public int numberOfLinks;
    public int numberOfSearchedStuff;
    public int dataID;

    public Statistics()
    {
        numberOfLinks =numberOfSearchedStuff=0;
    }


}
