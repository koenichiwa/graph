package graphs.adjacencymap

import graphs.baseinterfaces.Graph

abstract class AdjacencyMapGraph<Vertex>(override val isDirected: Boolean) : Graph<Vertex> {
    @Suppress("PropertyName")
    protected val _adjacencyMap = mutableMapOf<Vertex, MutableSet<Vertex>>()

    override fun containsVertex(vertex: Vertex): Boolean =
        _adjacencyMap.containsKey(vertex)

    override fun isAdjacent(from: Vertex, to: Vertex): Boolean =
        neighbors(from)?.contains(to) ?: false

    override fun neighbors(vertex: Vertex): Set<Vertex>? =
        _adjacencyMap[vertex]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AdjacencyMapGraph<*>) return false

        if (isDirected != other.isDirected) return false
        if (_adjacencyMap != other._adjacencyMap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isDirected.hashCode()
        result = 31 * result + _adjacencyMap.hashCode()
        return result
    }
}
