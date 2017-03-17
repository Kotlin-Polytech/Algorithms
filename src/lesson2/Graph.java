package lesson2;

import java.util.Map;
import java.util.Set;

public interface Graph {

    Set<Vertex> getVertices();

    default Set<Vertex> getNeighbors(Vertex v) {
        return getConnections(v).keySet();
    }

    Map<Vertex, Edge> getConnections(Vertex v);
}