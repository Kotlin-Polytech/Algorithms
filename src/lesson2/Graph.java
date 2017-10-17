package lesson2;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public interface Graph {

    interface Vertex {
        @NotNull
        String getName();
    }

    interface Edge {
        int getWeight();
    }

    @NotNull
    Set<Vertex> getVertices();

    @Nullable
    Vertex get(String name);

    @NotNull
    default Set<Vertex> getNeighbors(@NotNull  Vertex v) {
        return getConnections(v).keySet();
    }

    @NotNull
    Map<Vertex, Edge> getConnections(@NotNull Vertex v);
}