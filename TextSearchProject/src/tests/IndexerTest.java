import org.junit.jupiter.api.Test;
import structures.HashTable;
import structures.Indexer;
import structures.KeyValue;
import structures.NoMoreTokensException;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class IndexerTest {
    @Test
    void test() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");
        assertTrue(indexer.getKeyValue("of").getValues().contains("cat"));
        assertTrue(indexer.getKeyValue("ate").getValues().contains("bird"));
        assertTrue(indexer.getKeyValue("dog").getValues().contains("dog"));
        assertTrue(indexer.getKeyValue("big").getValues().contains("dog"));

        assertTrue(indexer.getKeyValue("my").getValues().contains("dog"));
        assertTrue(indexer.getKeyValue("my").getValues().contains("cat"));
        assertTrue(indexer.getKeyValue("my").getValues().contains("bird"));
        assertTrue(indexer.getKeyValue("my").getValues().contains("fish"));

        assertTrue(indexer.getKeyValue("red").getValues().contains("dog"));

        assertTrue(indexer.getKeyValue("cat").getValues().contains("cat"));
        assertTrue(indexer.getKeyValue("cat").getValues().contains("bird"));
        assertTrue(indexer.getKeyValue("cat").getValues().contains("fish"));

        assertTrue(indexer.getKeyValue("friendly").getValues().contains("dog"));
        assertTrue(indexer.getKeyValue("friendly").getValues().contains("bird"));

        assertNull(indexer.getKeyValue("lkjfdsglkjhsdfgjhdsfgkljh"));
        assertNull(indexer.getKeyValue("nothing"));
        assertNull(indexer.getKeyValue(" "));
        assertNull(indexer.getKeyValue(":"));
    }

    @Test
    void index() throws IOException, NoMoreTokensException {
        Indexer indexer = new Indexer("text1.txt");

        assertEquals( Math.abs(new KeyValue("abc").hashCode()) % table(indexer).size(), indexer.index("abc"));
        assertEquals( Math.abs(new KeyValue("a").hashCode()) % table(indexer).size(), indexer.index("a"));
        assertEquals( Math.abs(new KeyValue("b").hashCode()) % table(indexer).size(), indexer.index("b"));
        assertEquals( Math.abs(new KeyValue("c").hashCode()) % table(indexer).size(), indexer.index("c"));
        assertEquals( Math.abs(new KeyValue("d").hashCode()) % table(indexer).size(), indexer.index("d"));
        assertEquals( Math.abs(new KeyValue("asdfghjkl").hashCode()) % table(indexer).size(), indexer.index("asdfghjkl"));
    }

    HashTable table(Indexer indexer) {
        try {
            Field field = indexer.getClass().getDeclaredField("table");
            field.setAccessible(true);
            return (HashTable) field.get(indexer);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}