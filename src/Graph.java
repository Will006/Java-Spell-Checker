import java.util.*;

public interface Graph { //Do wee need this?
    int getNumV();

    boolean isDirected();

    //void insert(Edge edge);

    boolean isEdge(int source, int dest);

    //Edge getEdge(int source, int dest);

    //Iterator<Edge> edgeIterator(int source);

}
