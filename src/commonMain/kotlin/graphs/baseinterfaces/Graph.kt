package graphs.baseinterfaces

interface Graph<Vertex> {
    val isDirected: Boolean

    fun containsVertex(vertex: Vertex): Boolean
    fun isAdjacent(from: Vertex, to: Vertex): Boolean
    fun neighbors(vertex: Vertex): Set<Vertex>?
}
