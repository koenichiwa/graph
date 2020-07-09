package graphs.baseinterfaces

interface GraphBase<Vertex> {
    val isDirected: Boolean

    fun containsVertex(vertex: Vertex): Boolean

    fun isAdjacent(from: Vertex, to: Vertex): Boolean

    fun neighbors(vertex: Vertex): Set<Vertex>?
}
