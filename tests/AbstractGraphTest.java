import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractGraphTest {

    private MatrixGraph graph;

    @Before
    public void setUp() {
        graph = new MatrixGraph();
    }

    @Test
    public void testGetNumV() {
        assertEquals("Expected and actual don't match", 0, graph.getNumV());
    }

    @Test
    public void testIsDirected() {
        assertFalse("Should not be directed", graph.isDirected());
    }
}