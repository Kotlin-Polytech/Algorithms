package lesson5.genetic

import lesson2.Graph
import lesson2.impl.GraphBuilder
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class GeneticVoyagingPathSearcherTest {
    @Test
    fun findVoyagingPath() {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            addConnection(a, b, 10)
            addConnection(b, c, 15)
            addConnection(c, f, 30)
            addConnection(a, d, 20)
            addConnection(d, e, 25)
            addConnection(e, f, 15)
            addConnection(a, f, 40)
            addConnection(b, d, 10)
            addConnection(c, e, 5)
        }.build()
        val path = GeneticVoyagingPathSearcher(graph).findVoyagingPath(chromosomeNumber = 30, generationNumber = 20)
        assertEquals(105, path.length)
        assertEquals(listOf(graph["A"], graph["D"], graph["B"], graph["C"], graph["E"], graph["F"], graph["A"]),
                path.vertices)
    }

    @Test
    fun findRandomVoyagingPath() {
        val random = Random()
        val graph = GraphBuilder().apply {
            val vertices = mutableListOf<Graph.Vertex>()
            for (i in 0..99) {
                vertices += addVertex(i.toString())
            }
            for (i in 0..99) {
                for (j in i + 1..99) {
                    addConnection(vertices[i], vertices[j], 1 + random.nextInt(100))
                }
            }
        }.build()
        val path = GeneticVoyagingPathSearcher(graph).findVoyagingPath(chromosomeNumber = 100, generationNumber = 200)
        println(path.length)
        println(path)
    }

}