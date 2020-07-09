package graphs

import graphs.baseinterfaces.GraphBase
import graphs.baseinterfaces.Mutable

interface MutableGraph <Vertex> :
    GraphBase<Vertex>,
    Mutable<Vertex, Nothing> {

    fun addEdge(from: Vertex, to: Vertex): Boolean
}
