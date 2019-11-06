import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class wordReplacementQueueTest {

    private wordReplacementQueue queue;

    @Before
    public void setUp() {
        queue = new wordReplacementQueue("hallew");
        queue.addSuggestion(new wordVertex("cat"));
        queue.addSuggestion(new wordVertex("hallow"));
        queue.addSuggestion(new wordVertex("hat"));
        queue.addSuggestion(new wordVertex("hello"));
        queue.addSuggestion(new wordVertex("batman"));
        queue.addSuggestion(new wordVertex("heat"));
        queue.addSuggestion(new wordVertex("there"));
        queue.addSuggestion(new wordVertex("freedom"));
    }

    @Test
    public void testAddSuggestion() {
        assertEquals("Expected and actual don't match", "hallow", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "hello", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "hat", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "there", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "batman", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "cat", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "heat", queue.mainQueue.poll().suggestion.getValue());
        assertEquals("Expected and actual don't match", "freedom", queue.mainQueue.poll().suggestion.getValue());
    }

    @Test
    public void testGetTopFive() {
        wordVertex[] wordV = queue.getTopFive();
        assertEquals("Expected and actual don't match", "hello", wordV[0].value);
        assertEquals("Expected and actual don't match", "hat", wordV[1].value);
        assertEquals("Expected and actual don't match", "there", wordV[2].value);
        assertEquals("Expected and actual don't match", "batman", wordV[3].value);
        assertEquals("Expected and actual don't match", "cat", wordV[4].value);
    }
}