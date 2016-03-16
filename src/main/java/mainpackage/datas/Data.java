package mainpackage.datas;

/**
 * Created by lukasz on 09.01.16.
 */
public class Data {

    public static int counter = 0;

    private int ID;
    private String text;

    public Data(int id, String text)
    {
        this.ID = id;
        this.text = text;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
