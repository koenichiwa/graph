package graphs

interface ValuedMutableGraph <Vertex, EdgeValue> :
    ValuedGraph<Vertex, EdgeValue>,
    MutableGraph<Vertex, Pair<Vertex, EdgeValue>> {

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean
    fun removeEdge(from: Vertex, to: Vertex): EdgeValue?
}
