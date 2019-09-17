package lesson5

import lesson5.Graph.Vertex
import java.util.*

class VertexInfo(
    val vertex: Vertex,
    val distance: Int,
    val prev: Vertex?
) : Comparable<VertexInfo> {
    override fun compareTo(other: VertexInfo): Int {
        return distance.compareTo(other.distance)
    }
}

fun Graph.shortestPath(from: Vertex): Map<Vertex, VertexInfo> {
    val info = mutableMapOf<Vertex, VertexInfo>()
    for (vertex in this.vertices) {
        info[vertex] = VertexInfo(vertex, Int.MAX_VALUE, null)
    }
    val visited = mutableSetOf<Vertex>()
    val fromInfo = VertexInfo(from, 0, null)
    val queue = PriorityQueue<VertexInfo>()
    queue.add(fromInfo)
    info[from] = fromInfo
    while (queue.isNotEmpty()) {
        val currentInfo = queue.poll()
        val currentVertex = currentInfo.vertex
        visited += currentVertex
        for (vertex in this.getNeighbors(currentVertex)) {
            if (vertex in visited) continue
            val weight = this.getConnection(currentVertex, vertex)?.weight
            if (weight != null) {
                val newDistance = info[currentVertex]!!.distance + weight
                if (info[vertex]!!.distance > newDistance) {
                    val newInfo = VertexInfo(vertex, newDistance, currentVertex)
                    queue.add(newInfo)
                    info[vertex] = newInfo
                }
            }
        }
    }
    return info
}

fun Map<Vertex, VertexInfo>.unrollPath(to: Vertex): List<Vertex> {
    val result = mutableListOf<Vertex>()
    var current: Vertex? = to
    while (current != null) {
        result += current
        current = this[current]?.prev
    }
    result.reverse()
    return result
}