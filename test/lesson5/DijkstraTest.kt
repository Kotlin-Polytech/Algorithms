package lesson5

import lesson5.impl.GraphBuilder
import org.junit.jupiter.api.Tag
import kotlin.test.*

class DijkstraTest {

    @Test
    @Tag("Example")
    fun test1() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b, 10)
            addConnection(b, c, 5)
        }.build()
        val pathMap = graph.shortestPath(graph["A"]!!)
        assertEquals(10, pathMap[graph["B"]!!]?.distance)
        assertEquals(15, pathMap[graph["C"]!!]?.distance)
        assertEquals(listOf(graph["A"], graph["B"], graph["C"]), pathMap.unrollPath(graph["C"]!!))
    }

    @Test
    @Tag("Example")
    fun test2() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            addConnection(a, b, 15)
            addConnection(b, c, 15)
            addConnection(b, d, 20)
            addConnection(a, c, 10)
            addConnection(c, e, 5)
            addConnection(d, e, 15)
            addConnection(d, f, 15)
        }.build()
        val pathMap = graph.shortestPath(graph["A"]!!)
        assertEquals(15, pathMap[graph["B"]!!]?.distance)
        assertEquals(10, pathMap[graph["C"]!!]?.distance)
        assertEquals(30, pathMap[graph["D"]!!]?.distance)
        assertEquals(15, pathMap[graph["E"]!!]?.distance)
        assertEquals(
            listOf(graph["A"], graph["C"], graph["E"], graph["D"], graph["F"]),
            pathMap.unrollPath(graph["F"]!!)
        )
    }
}