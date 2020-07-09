package graphs.adjacencymap

import graphs.MutableGraph

open class AdjacencyMapMutableGraphImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    MutableAdjacencyMapGraph<Vertex, Nothing>(isDirected) {

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        return if (!get(from)!!.add(to))
            false
        else if (!isDirected)
            get(to)!!.add(from)
        else true
    }
}
