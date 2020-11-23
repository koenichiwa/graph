package graphs.adjacencymap

import graphs.MutableGraph
import graphs.exceptions.VertexNotFoundException

open class MutableAdjacencyMapGraphImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    MutableAdjacencyMapGraph<Vertex>(isDirected) {

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            throw VertexNotFoundException(from)
        if (!containsVertex(to))
            throw VertexNotFoundException(to)

        return if (!_adjacencyMap[from]!!.add(to))
            false
        else if (!isDirected)
            _adjacencyMap[to]!!.add(from)
        else true
    }
}
