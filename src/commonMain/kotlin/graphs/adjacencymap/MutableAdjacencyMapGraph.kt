package graphs.adjacencymap

import graphs.baseinterfaces.Mutable
import graphs.exceptions.VertexNotFoundException

abstract class MutableAdjacencyMapGraph<Vertex> (isDirected: Boolean) :
    Mutable<Vertex>,
    AdjacencyMapGraph<Vertex>(isDirected) {

    override fun addVertex(vertex: Vertex): Boolean {
        return if (_adjacencyMap[vertex] != null)
            false
        else {
            _adjacencyMap[vertex] = mutableSetOf()
            true
        }
    }

    override fun removeVertex(vertex: Vertex): Boolean {
        val res = _adjacencyMap.remove(vertex) != null
        if (res)
            _adjacencyMap.values.forEach { it.remove(vertex) }
        return res
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            throw VertexNotFoundException(from)
        if (!containsVertex(to))
            throw VertexNotFoundException(to)

        val res = _adjacencyMap[from]!!.remove(to)
        return if (!isDirected && res)
            _adjacencyMap[to]!!.remove(from)
        else res
    }
}
