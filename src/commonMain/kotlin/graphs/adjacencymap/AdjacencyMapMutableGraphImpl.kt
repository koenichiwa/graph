package graphs.adjacencymap

import graphs.MutableGraph

open class AdjacencyMapMutableGraphImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    MutableAdjacencyMapGraph<Vertex>(isDirected) {

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        return if (!_adjacencyMap[from]!!.add(to))
            false
        else if (!isDirected)
            _adjacencyMap[to]!!.add(from)
        else true
    }
}
