public abstract class AbstractGraph{ //implements Graph -Not working IDK why
    /** The number of vertices */
    private int numV;
    /** Flag to indicate whether this is a directed graph */
    private boolean directed;

    /**
     * Default constructor that creates a default AbstractClass object
     * with default values
     */
    public AbstractGraph() {
        this.numV = 0;
        this.directed = false;
    }

    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     * @param numV The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraph(int numV, boolean directed) {
        if(numV >= 0) {
            this.numV = numV;
        }
        this.directed = directed;
    }

    /**
     * Return the number of vertices
     * @return The number of vertices
     */
    public int getNumV() {
        return numV;
    }

    /**
     * Return whether this a directed graph
     * @return true if this a directed graph
     */
    public boolean isDirected() {
        return directed;
    }
}
