import org.junit.Test;

import static org.junit.Assert.*;

public class wordVertexTest {

    private wordVertex wordV;
    private wordVertex wordV2;

    @Test
    public void testDecrementWeight() {
        wordV = new wordVertex();
        wordV.decrementWeight();
        assertEquals("Expected and actual don't match", 0, wordV.getWeight(), 0);

        wordV = new wordVertex("test", 1);
        wordV.decrementWeight();
        assertEquals("Expected and actual don't match", 0.99998, wordV.getWeight(), 0);
    }

    @Test
    public void testToString() {
        wordV = new wordVertex("test", 1);
        assertEquals("Expected and actual don't match", "weight:"+wordV.getWeight()+"\nValue:"+wordV.getValue(), wordV.toString());
    }

    @Test
    public void testCompareTo() {
        wordV = new wordVertex("test1");
        wordV2 = new wordVertex("test2");
        assertEquals("Expected and actual don't match", -1, wordV.compareTo(wordV2));

        wordV2 = new wordVertex("test1");
        assertEquals("Expected and actual don't match", 0, wordV.compareTo(wordV2));
    }
}