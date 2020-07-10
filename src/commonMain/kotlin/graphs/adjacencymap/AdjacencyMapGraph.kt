package graphs.adjacencymap

import graphs.baseinterfaces.Graph

abstract class AdjacencyMapGraph<Vertex>(override val isDirected: Boolean) : Graph<Vertex> {

    protected val _adjacencyMap = mutableMapOf<Vertex, MutableSet<Vertex>>()

    override fun containsVertex(vertex: Vertex): Boolean =
        _adjacencyMap.containsKey(vertex)

    override fun isAdjacent(from: Vertex, to: Vertex): Boolean =
        neighbors(from)?.contains(to) ?: false

    override fun neighbors(vertex: Vertex): Set<Vertex>? =
        _adjacencyMap[vertex]

    override fun equals(other: Any?): Boolean =
        other === this ||
            other is AdjacencyMapGraph<*> && this.hashCode() == other.hashCode()

    override fun hashCode(): Int =
        _adjacencyMap
            .flatMap { entry -> entry.value.map { entry.key to it } }
            .fold(0) { acc, pair -> (acc * 7 + pair.hashCode()) % Int.MAX_VALUE }
}
