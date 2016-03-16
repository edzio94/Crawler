package mainpackage.datas;

import java.util.HashSet;

/**
 * Created by lukasz on 09.01.16.
 */
public class Links {
    public static int counter = 0;
    private int id;
    private String url;
    private boolean is_used;

    public Links(int ID, String url)
    {
        this.url = url;
        this.id = ID;
    }
    public Links(){}


    public boolean is_used() {
        return is_used;
    }

    public void setIs_used(boolean is_used) {
        this.is_used = is_used;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Links links = (Links) o;

        return url.equals(links.url);

    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
