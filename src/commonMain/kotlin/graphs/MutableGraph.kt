package graphs

import graphs.baseinterfaces.Graph
import graphs.baseinterfaces.Mutable

interface MutableGraph <Vertex> :
    Graph<Vertex>,
    Mutable<Vertex> {

    fun addEdge(from: Vertex, to: Vertex): Boolean
}
