package graphs.baseinterfaces

interface GraphBase<Vertex, EdgeType> {
    val isDirected: Boolean

    fun containsVertex(vertex: Vertex): Boolean

    fun isAdjacent(from: Vertex, to: Vertex): Boolean

    fun getNeighboringEdges(vertex: Vertex): Set<EdgeType>?
    fun neighbors(vertex: Vertex): Set<Vertex>?

    fun EdgeType.getVertex(): Vertex
}
