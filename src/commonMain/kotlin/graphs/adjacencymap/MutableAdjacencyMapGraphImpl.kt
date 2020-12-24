package graphs.adjacencymap

import graphs.MutableGraph
import graphs.exceptions.VertexNotFoundException

class MutableAdjacencyMapGraphImpl<Vertex>(isDirected: Boolean) :
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MutableAdjacencyMapGraphImpl<*>) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}
