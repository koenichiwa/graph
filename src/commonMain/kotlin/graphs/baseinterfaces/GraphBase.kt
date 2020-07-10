package graphs.baseinterfaces

/**
 * Base interface where each graph is derived from
 */
interface GraphBase<Vertex> {
    /**
     * If this graph is directed this will be true, e.g. when there is a path from A to B then that doesn't mean
     * necessarily that there is a path from B to A
     */
    val isDirected: Boolean

    /**
     * Checks if this graph contains a specific vertex
     *
     * @param vertex the vertex to check
     * @return true if this graph contains that specific vertex
     */
    fun containsVertex(vertex: Vertex): Boolean

    /**
     * Checks if there is an edge from one vertex to the other
     *
     * @param from Vertex to start from
     * @param to Vertex to arrive to
     * @return true if this path exists
     */
    fun isAdjacent(from: Vertex, to: Vertex): Boolean

    /**
     * Lists all the vertices for which isAdjacent(vertex, otherVertex) == true
     *
     * @param vertex Vertex to check for
     * @return Set of all vertices that the input vertex has an edge to, null if the graph does not contain the input vertex
     */
    fun neighbors(vertex: Vertex): Set<Vertex>?
}
