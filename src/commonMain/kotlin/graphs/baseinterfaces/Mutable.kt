package graphs.baseinterfaces

interface Mutable<in Vertex, in EdgeValue> {

    fun addVertex(vertex: Vertex): Boolean
    fun removeVertex(vertex: Vertex): Boolean

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue?): Boolean
    fun removeEdge(from: Vertex, to: Vertex): Boolean
}
