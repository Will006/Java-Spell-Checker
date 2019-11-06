import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixGraphTest {

    private MatrixGraph matrixG;

    @Before
    public void setUp() {
        matrixG = new MatrixGraph();
    }

    @Test
    public void testAdd() {
        matrixG.add("The", "test");
        assertEquals("Expected and actual don't match", "The", matrixG.mainMatrix[6201][6201].value);
        assertEquals("Expected and actual don't match", "test", matrixG.mainMatrix[6201][4683].value);
    }

    @Test
    public void testGetLength() {
        assertEquals("Expected and actual don't match", matrixG.MATRIX_START_SIZE, matrixG.getLength());
    }
}