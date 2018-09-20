package lesson5

import lesson5.impl.GraphBuilder
import org.junit.jupiter.api.Tag
import kotlin.test.*

class BridgesTest {
    @Test
    @Tag("Example")
    fun simple() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(a, c)
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            addConnection(d, e)
            addConnection(e, f)
            addConnection(d, f)
            addConnection(c, d)
        }.build()
        val bridges = graph.findBridges()
        assertEquals(1, bridges.size)
        val bridge = bridges.first()
        assertEquals(graph.getConnection(graph["C"]!!, graph["D"]!!), bridge)
    }

    @Test
    @Tag("Example")
    fun none() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(a, c)
            addConnection(a, d)
            addConnection(b, d)
            addConnection(c, d)
            addConnection(a, e)
            addConnection(b, e)
            addConnection(c, e)
            addConnection(d, e)
        }.build()
        assertEquals(0, graph.findBridges().size)
    }
}