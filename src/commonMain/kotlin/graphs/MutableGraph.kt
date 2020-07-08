package graphs

interface MutableGraph <Vertex, EdgeType> : Graph<Vertex, EdgeType> {
    fun addVertex(vertex: Vertex): Boolean
    fun removeVertex(vertex: Vertex): Boolean
}
