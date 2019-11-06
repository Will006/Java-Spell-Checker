import java.io.Serializable;

/**
 * wordVertex.java used to store a word as the value and a weight that acts as a frequency counter between the
 * stored value and the value that occurs before the saved value
 * William Pickering, Xavier Finney, and Leo Beltran
 * Last Edit: 5/20/18
 */
public class wordVertex implements Comparable<wordVertex>, Serializable//, Comparator
{
    final double DECREMENT_VALUE=0.00002;
    double weight;
    String value;

    /**
     * Default constructor that creates a wordVertex object with default values
     * for the instance variables
     */
    public wordVertex()
    {
        weight=0;
        value=null;
    }

    /**
     * Constructor that takes in a string for the value and creates a wordVertex
     * object
     * @param valueIn The value of the wordVertex
     */
    public wordVertex(String valueIn)
    {
        weight=0;
        value=valueIn;
    }

    /**
     * Full constructor that takes in a string for the value and a double for
     * the weight, then creates a wordVertex object with the given information
     * @param valueIn The value of the wordVertex
     * @param weightIn The weight of the wordVertex
     */
    public wordVertex(String valueIn, double weightIn)
    {
        weight=weightIn;
        value=valueIn;
    }

    /**
     * Decrements the weight of the wordVertex by the DECREMENT_VALUE if the
     * result leads to a positive value
     */
    public void decrementWeight()
    {
        if(weight>DECREMENT_VALUE)
        {
            weight-=DECREMENT_VALUE;
        }
    }

    /**
     * Retrieves the weight of the wordVertex
     * @return the weight of the wordVertex
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Retrieves the value of the wordVertex
     * @return the value of the wordVertex
     */
    public String getValue() {
        return value;
    }

    /**
     * Retrieves a string representation of the weight and value of the
     * wordVertex
     * @return a string containing the weight and value of the wordVertex
     */
    public String toString()
    {
        return ("weight:"+weight+"\nValue:"+value);
    }

    /**
     * Retrieves an int representing the hashcode value for the value of
     * the wordVertex
     * @return a calculated hashcode value for the value of the wordVertex
     */
    @Override
    public int hashCode()
    {
        //The Java API algorithm accounts for position of the characters as
        //well
        //String.hashCode() returns the integer calculated by the
        //formula:
        return value.hashCode();
    }

    /**
     * Will get the error count, the number of spelling changes from the given string to the value.
     * ie teh would have one error count to the
     * @param wordIn The string to compare to value of the wordVertex
     * @return The number of spelling changes between the given string an the value
     *         in the wordVertex
     */
    public int getErrorCount(String wordIn)
    {
        return 0;
    }

    /**
     * Compares the value in the given wordVertex to the value in the
     * current wordVertex
     * @param vertexIn The wordVertex used to compare its value to the
     *                 value of the current wordVertex
     * @return The result of comparing the value in the given wordVertex
     *         to the value in the current wordVertex
     */
    @Override
    public int compareTo(wordVertex vertexIn)
    {
        return value.compareTo(vertexIn.value);
    }
}