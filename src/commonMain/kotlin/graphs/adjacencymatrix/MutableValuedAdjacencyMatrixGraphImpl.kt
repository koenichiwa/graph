package graphs.adjacencymatrix

import graphs.ValuedMutableGraph
import graphs.exceptions.VertexNotFoundException

class MutableValuedAdjacencyMatrixGraphImpl<Vertex, EdgeValue>(isDirected: Boolean) :
    ValuedMutableGraph<Vertex, EdgeValue>,
    MutableAdjacencyMatrixGraph<Vertex>(isDirected) {
    private val _edgeValueMap = mutableMapOf<Pair<Vertex, Vertex>, EdgeValue>()
    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex < 0)
            throw VertexNotFoundException(from)
        val toIndex = _vertexList.indexOf(to)
        if (toIndex < 0)
            throw VertexNotFoundException(to)

        _adjacencyMatrix[fromIndex][toIndex] = true
        _edgeValueMap[from to to] = value
        if (!isDirected) {
            _adjacencyMatrix[toIndex][fromIndex] = true
            _edgeValueMap[to to from] = value
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MutableValuedAdjacencyMatrixGraphImpl<*, *>) return false
        if (!super.equals(other)) return false

        if (_edgeValueMap != other._edgeValueMap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + _edgeValueMap.hashCode()
        return result
    }
}
