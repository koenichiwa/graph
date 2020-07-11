package graphs

import graphs.baseinterfaces.Mutable

interface ValuedMutableGraph <Vertex, EdgeValue> :
    Mutable<Vertex>,
    ValuedGraph<Vertex, EdgeValue> {

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean
    fun updateEdgeValue(from: Vertex, to: Vertex, value: EdgeValue)
}
