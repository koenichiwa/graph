package graphs.baseinterfaces

interface Mutable<in Vertex, EdgeType> {

    fun addVertex(vertex: Vertex): Boolean
    fun removeVertex(vertex: Vertex): Boolean

    fun addEdge(from: Vertex, to: EdgeType): Boolean
    fun removeEdge(from: Vertex, to: Vertex): Boolean
}
