package graphs.adjacencymap

import graphs.ValuedMutableGraph
import graphs.exceptions.VertexNotFoundException

class MutableValuedAdjacencyMapGraphImpl<Vertex, EdgeValue>(isDirected: Boolean) :
    ValuedMutableGraph<Vertex, EdgeValue>,
    MutableAdjacencyMapGraph<Vertex>(isDirected) {

    @Suppress("PropertyName")
    private val _edgeValueMap = mutableMapOf<Pair<Vertex, Vertex>, EdgeValue>()

    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (!containsVertex(from))
            throw VertexNotFoundException(from)
        if (!containsVertex(to))
            throw VertexNotFoundException(to)

        val res = _adjacencyMap[from]!!.add(to)
        if (!res)
            return false

        if (!isDirected) {
            if (!_adjacencyMap[to]!!.add(from))
                return false

            _edgeValueMap[to to from] = value
        }
        _edgeValueMap[from to to] = value
        return true
    }

    override fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue? =
        _edgeValueMap[from to to]

    override fun updateEdgeValue(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (isAdjacent(from, to)) {
            _edgeValueMap[from to to] = value
            if (!isDirected)
                _edgeValueMap[to to from] = value
            return true
        }
        return false
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        _edgeValueMap.remove(from to to)
        if (!isDirected)
            _edgeValueMap.remove(to to from)
        return super.removeEdge(from, to)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MutableValuedAdjacencyMapGraphImpl<*, *>) return false
        if (!super.equals(other)) return false

        if (_edgeValueMap != other._edgeValueMap) return false

        return true
    }

    override fun hashCode(): Int =
        31 * super.hashCode() + _edgeValueMap.hashCode()
}
