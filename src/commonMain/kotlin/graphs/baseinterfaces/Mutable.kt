package graphs.baseinterfaces

interface Mutable<in Vertex> {
    fun addVertex(vertex: Vertex): Boolean
    fun removeVertex(vertex: Vertex): Boolean
    fun removeEdge(from: Vertex, to: Vertex): Boolean
}
