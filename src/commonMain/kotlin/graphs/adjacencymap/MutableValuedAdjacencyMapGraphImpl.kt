package graphs.adjacencymap

import graphs.ValuedMutableGraph

open class MutableValuedAdjacencyMapGraphImpl<Vertex, EdgeValue>(isDirected: Boolean) :
    ValuedMutableGraph<Vertex, EdgeValue>,
    MutableAdjacencyMapGraph<Vertex>(isDirected) {

    @Suppress("PropertyName")
    private val _edgeValueMap = mutableMapOf<Pair<Vertex, Vertex>, EdgeValue>()

    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

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
}
