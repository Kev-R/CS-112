package structures;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            Indexer indexer = new Indexer("text1.txt");
            SimpleSearch search = new SimpleSearch();
            ArrayList<String> string = search.query("my", indexer);
            System.out.println(string.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
