package graphs

import graphs.baseinterfaces.GraphBase
import graphs.baseinterfaces.Mutable
import graphs.baseinterfaces.Valued

interface ValuedMutableGraph <Vertex, EdgeValue> :
    GraphBase<Vertex>,
    Valued<Vertex, EdgeValue>,
    Mutable<Vertex, EdgeValue> {

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean
}
