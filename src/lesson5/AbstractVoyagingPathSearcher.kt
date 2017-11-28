package lesson5

import lesson2.Graph
import lesson2.Path
import java.util.*

abstract class AbstractVoyagingPathSearcher(val g: Graph) {
    protected val size = g.vertices.size
    protected val vertexByIndex: MutableMap<Int, Graph.Vertex> = mutableMapOf()
    protected val random = Random()

    init {
        for ((index, vertex) in g.vertices.withIndex()) {
            vertexByIndex[index] = vertex
        }
    }

    protected fun List<Int>.evaluation(): Int {
        var result = 0
        for (i in 0 until size) {
            val previousVertex = vertexByIndex[this[i]]!!
            val nextVertex = vertexByIndex[this[if (i < size - 1) i + 1 else 0]]!!
            val edge = g.getConnection(previousVertex, nextVertex)
            result += edge?.weight ?: 1000000
        }
        return result
    }

    protected fun List<Int>.buildPath(): Path {
        var path = Path(vertexByIndex[this[0]]!!)
        for (i in 1 until size) {
            path = Path(path, g, vertexByIndex[this[i]]!!)
        }
        return Path(path, g, vertexByIndex[this[0]]!!)
    }

    abstract fun findVoyagingPath(): Path
}