import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.HashTable;
import structures.KeyValue;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    HashTable hashTable;

    @BeforeEach
    void reset() {
        hashTable = new HashTable();
    }

    @Test
    void emptyTable() {
        assertEquals(0, numNodes());
    }

    @Test
    void insertOne() {
        KeyValue keyValue = new KeyValue("testing");
        keyValue.addValue("one");

        hashTable.insertKeyValue(index("testing"), keyValue);

        assertEquals(1, numNodes());

        assertNotNull(hashTable.lookUpKey(index("testing"), "testing"));
    }

    @Test
    void insertDuplicate() {
        KeyValue keyValue = new KeyValue("testing");
        keyValue.addValue("one");

        hashTable.insertKeyValue(index("testing"), keyValue);

        assertEquals(1, numNodes());

        KeyValue keyValue2 = new KeyValue("testing");
        keyValue2.addValue("two");

        hashTable.insertKeyValue(index("testing"), keyValue2);

        assertEquals(numNodes(), 1);

        assertNotNull(hashTable.lookUpKey(index("testing"), "testing"));
    }

    @Test
    void insertTwo() {
        KeyValue keyValue = new KeyValue("testing");
        keyValue.addValue("one");

        hashTable.insertKeyValue(index("testing"), keyValue);

        assertEquals(1, numNodes());

        KeyValue keyValue2 = new KeyValue("testing2");
        keyValue2.addValue("two");

        hashTable.insertKeyValue(index("testing2"), keyValue2);

        assertEquals(2, numNodes());

        assertNotNull(hashTable.lookUpKey(index("testing2"), "testing2"));
    }

    @Test
    void rehash() {
        for (int i = 0; i < 12; i++) {
            KeyValue keyValue = new KeyValue("key" + (char) (i + 'a'));
            keyValue.addValue("value");

            hashTable.insertKeyValue(index(keyValue.getKey()), keyValue);
            assertEquals(i + 1, numNodes());
            assertEquals(15, hashTable.size());

            assertNotNull(hashTable.lookUpKey(index(keyValue.getKey()), keyValue.getKey()));
        }

        KeyValue keyValue2 = new KeyValue("keyre");
        keyValue2.addValue("rehashing");

        hashTable.insertKeyValue(index("keyre"), keyValue2);

        //assertEquals(12, numNodes());
        assertEquals(46, hashTable.size());

        assertNotNull(hashTable.lookUpKey(index("keyre"), keyValue2.getKey()));
    }

    @Test
    void dontRehashOnSameKey() {
        for (int i = 0; i < 20; i++) {
            KeyValue keyValue = new KeyValue("key");
            keyValue.addValue("value"+ (char) (i + 'a'));

            hashTable.insertKeyValue(index(keyValue.getKey()), keyValue);
            assertEquals(1, numNodes());
            assertEquals(15, hashTable.size());

            assertNotNull(hashTable.lookUpKey(index(keyValue.getKey()), keyValue.getKey()));
        }
    }

    @Test
    void duplicateKeysGetMerged() {
        KeyValue keyValue = new KeyValue("samekey");
        keyValue.addValue("one");

        hashTable.insertKeyValue(index("samekey"), keyValue);

        assertEquals(1, numNodes());

        KeyValue keyValue2 = new KeyValue("samekey");
        keyValue2.addValue("two");

        hashTable.insertKeyValue(index("samekey"), keyValue2);

        assertEquals(1, numNodes());

        assertNotNull(hashTable.lookUpKey(index("samekey"), "samekey"));

        assertEquals(2, hashTable.lookUpKey(index("samekey"), "samekey").getValues().size());
    }

    int index(String key) {
        return new KeyValue(key).hashCode() % hashTable.size();
    }

    int numNodes() {
        try {
            Field field = hashTable.getClass().getDeclaredField("numNodes");
            field.setAccessible(true);
            return (Integer) field.get(hashTable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }
}