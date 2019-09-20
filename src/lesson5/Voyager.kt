package lesson5

import lesson5.Graph.Vertex

class Path private constructor(
    val vertices: List<Vertex>,
    val length: Int
) : Comparable<Path> {
    override fun compareTo(other: Path) = length.compareTo(other.length)

    fun isLoop(graph: Graph): Boolean =
        vertices.size == graph.vertices.size + 1 && vertices.first() == vertices.last()

    operator fun contains(v: Vertex) = v in vertices

    constructor(first: Vertex) : this(listOf(first), 0)

    constructor() : this(emptyList(), 0)

    constructor(previous: Path, g: Graph, next: Vertex) : this(
        previous.vertices + next,
        previous.length + g.getConnection(previous.vertices.last(), next)!!.weight
    )

    override fun toString() = "<$vertices of length $length>"
}

fun findVoyagingPath(
    g: Graph,
    currentPath: Path = Path(g.vertices.first()),
    bestPath: Path? = null
): Path? {
    var best = bestPath
    for (next in g.getNeighbors(currentPath.vertices.last())) {
        if (next != g.vertices.first() && next in currentPath) continue
        val nextPath = Path(currentPath, g, next)
        if (best != null && best.length <= nextPath.length) continue
        val cyclePath = if (nextPath.isLoop(g)) {
            nextPath
        } else {
            findVoyagingPath(g, nextPath, best) ?: continue
        }
        if (best == null || best.length > cyclePath.length) {
            best = cyclePath
        }
    }
    return best
}
