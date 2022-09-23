import org.junit.jupiter.api.Test;
import structures.Indexer;
import structures.NoMoreTokensException;
import structures.SimpleSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSearchTest {

    @Test
    void cat() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("cat", indexer);

        assertNotNull(results);

        assertTrue(results.contains("cat"));
        assertTrue(results.contains("bird"));
        assertTrue(results.contains("fish"));
        assertFalse(results.contains("dog"));
        assertFalse(results.contains("nothing"));
    }

    @Test
    void bigRedDog() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("big red dog", indexer);

        assertNotNull(results);

        assertTrue(results.contains("dog"));
        assertFalse(results.contains("cat"));
        assertFalse(results.contains("bird"));
        assertFalse(results.contains("fish"));
        assertFalse(results.contains("nothing"));
    }

    @Test
    void bigCat() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("big cat", indexer);

        assertNotNull(results);

        assertTrue(results.contains("dog"));
        assertTrue(results.contains("cat"));
        assertTrue(results.contains("bird"));
        assertTrue(results.contains("fish"));
        assertFalse(results.contains("nothing"));
    }

    @Test
    void bigFriendlyCat() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("big friendly cat", indexer);

        assertNotNull(results);

        assertTrue(results.contains("dog"));
        assertTrue(results.contains("cat"));
        assertTrue(results.contains("bird"));
        assertTrue(results.contains("fish"));
        assertFalse(results.contains("nothing"));
    }

    @Test
    void random() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("random", indexer);

        assertNull(results);
    }

    @Test
    void noDuplicates() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("big friendly cat", indexer);

        assertNotNull(results);

        Map<String, Integer> counts = new HashMap<>();

        for (String res : results) {
            counts.put(res, counts.getOrDefault(res, 0) + 1);
        }

        for (int num : counts.values()) {
            assertEquals(1, num);
        }
    }

    @Test
    void specialCharacters() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("cat; ds;fsd;.,'.,.f sdf:!!????dfsdfdg", indexer);

        assertNotNull(results);

        assertTrue(results.contains("cat"));
        assertTrue(results.contains("bird"));
        assertTrue(results.contains("fish"));
        assertFalse(results.contains("dog"));
        assertFalse(results.contains("nothing"));
    }

    @Test
    void onlySpecialCharacters() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        ArrayList<String> results = SimpleSearch.query("   ;::::??!!!!!','..'.',',", indexer);

       assertNull(results);
    }
}