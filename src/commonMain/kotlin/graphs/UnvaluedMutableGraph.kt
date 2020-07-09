package graphs

interface UnvaluedMutableGraph<Vertex> :
    UnvaluedGraph<Vertex>,
    MutableGraph<Vertex, Vertex> {

    fun addEdge(from: Vertex, to: Vertex): Boolean
    fun removeEdge(from: Vertex, to: Vertex): Boolean
}
