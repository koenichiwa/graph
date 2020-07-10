package graphs

import graphs.baseinterfaces.Graph
import graphs.baseinterfaces.Mutable
import graphs.baseinterfaces.Valued

interface ValuedMutableGraph <Vertex, EdgeValue> :
    Graph<Vertex>,
    Valued<Vertex, EdgeValue>,
    Mutable<Vertex, EdgeValue> {

    fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean
}
