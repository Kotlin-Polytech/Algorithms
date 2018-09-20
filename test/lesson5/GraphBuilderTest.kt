package lesson5

import lesson5.impl.GraphBuilder
import org.junit.jupiter.api.Tag
import kotlin.test.*

class GraphBuilderTest {

    @Test
    @Tag("Example")
    fun generalTest() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b, 10)
            addConnection(b, c, 5)
        }.build()
        assertEquals(3, graph.vertices.size)
        val b = graph["B"]!!
        assertEquals(2, graph.getNeighbors(b).size)
        for ((vertex, edge) in graph.getConnections(b)) {
            when (vertex.name) {
                "A" -> assertEquals(10, edge.weight)
                "C" -> assertEquals(5, edge.weight)
                else -> throw AssertionError()
            }
        }
    }
}