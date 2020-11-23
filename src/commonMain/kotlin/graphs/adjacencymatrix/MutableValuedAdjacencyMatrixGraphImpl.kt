package graphs.adjacencymatrix

import graphs.ValuedMutableGraph

class MutableValuedAdjacencyMatrixGraphImpl<Vertex, EdgeValue>(isAdjacent: Boolean) :
    ValuedMutableGraph<Vertex, EdgeValue>,
    MutableAdjacencyMatrixGraph<Vertex>(isAdjacent) {
    private val _edgeValueMap = mutableMapOf<Pair<Vertex, Vertex>, EdgeValue>()
    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex < 0)
            return false
        val toIndex = _vertexList.indexOf(to)
        if (toIndex < 0)
            return false

        _adjacencyMatrix[fromIndex][toIndex] = true
        _edgeValueMap[from to to] = value
        if (!isDirected) {
            _adjacencyMatrix[toIndex][fromIndex] = true
            _edgeValueMap[from to to] = value
        }
        return true
    }

    override fun updateEdgeValue(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (isAdjacent(from, to)) {
            _edgeValueMap[from to to] = value
            if (!isDirected)
                _edgeValueMap[to to from] = value
            return true
        }
        return false
    }

    override fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue? =
        _edgeValueMap[from to to]
}
