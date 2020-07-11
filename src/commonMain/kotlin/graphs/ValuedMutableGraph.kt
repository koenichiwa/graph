package graphs

import graphs.baseinterfaces.Graph
import graphs.baseinterfaces.Mutable
import graphs.baseinterfaces.Valued

interface ValuedMutableGraph <Vertex, EdgeValue> :
    Graph<Vertex>,
    Valued<Vertex, EdgeValue>,
    Mutable<Vertex> {

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean
    fun putEdgeValue(from: Vertex, to: Vertex, value: EdgeValue)
    fun removeEdgeValue(from: Vertex, to: Vertex): Boolean
}
