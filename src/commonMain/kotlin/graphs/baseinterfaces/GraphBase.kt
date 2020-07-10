package graphs.baseinterfaces

/**
 * Base interface where each graph is derived from
 */
interface GraphBase<Vertex> {
    /**
     * If this graph is directed this will be true,
     * e.g. when there is a path from A to B then
     * that doesn't mean necessarily that there is a path from B to A
     */
    val isDirected: Boolean

    fun containsVertex(vertex: Vertex): Boolean

    fun isAdjacent(from: Vertex, to: Vertex): Boolean

    fun neighbors(vertex: Vertex): Set<Vertex>?
}
