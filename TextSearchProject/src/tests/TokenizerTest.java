


import org.junit.jupiter.api.Test;
import structures.NoMoreTokensException;
import structures.Tokenizer;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    @Test
    void emptyTokenizer() {
        Tokenizer tokenizer = new Tokenizer("", "");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void noDelims() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer("", "lets go");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "lets go");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void noDelimsInString() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer(":;-", "lets go");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "lets go");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void oneDelimInString() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer(":;- ", "lets:;- go");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "lets");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "go");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void multiDelimsInString() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer(" ", "lets go 100 8 9");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "lets");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "go");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "100");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "8");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "9");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void twoConsecutiveDelims() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer("-", "lets--go");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "lets");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "go");

        assertFalse(tokenizer.hasToken());
        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }

    @Test
    void onlyDelimsInString() throws NoMoreTokensException {
        Tokenizer tokenizer = new Tokenizer("-;:", "-:;");

        assertTrue(tokenizer.hasToken());
        assertEquals(tokenizer.nextToken(), "-:;");


        assertThrows(NoMoreTokensException.class, tokenizer::nextToken);
    }
}