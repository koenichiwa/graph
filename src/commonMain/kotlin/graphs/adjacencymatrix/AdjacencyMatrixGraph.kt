package graphs.adjacencymatrix

import graphs.baseinterfaces.Graph

abstract class AdjacencyMatrixGraph<Vertex>(override val isDirected: Boolean) : Graph<Vertex> {
    @Suppress("PropertyName")
    protected val _vertexList = mutableListOf<Vertex>()
    @Suppress("PropertyName")
    protected val _adjacencyMatrix = mutableListOf<MutableList<Boolean>>()
    override fun containsVertex(vertex: Vertex): Boolean =
        _vertexList.contains(vertex)

    override fun isAdjacent(from: Vertex, to: Vertex): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex <0)
            return false
        val toIndex = _vertexList.indexOf(to)
        if (toIndex <0)
            return false

        return _adjacencyMatrix[fromIndex][toIndex]
    }

    override fun neighbors(vertex: Vertex): Set<Vertex>? {
        val vertexIndex = _vertexList.indexOf(vertex)
        if (vertexIndex <0)
            return null

        return _adjacencyMatrix[vertexIndex]
            .mapIndexed { index, value -> Pair(value, _vertexList[index]) }
            .filter { it.first }
            .map { it.second }
            .toSet()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AdjacencyMatrixGraph<*>) return false

        if (isDirected != other.isDirected) return false
        if (_vertexList != other._vertexList) return false
        if (_adjacencyMatrix != other._adjacencyMatrix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isDirected.hashCode()
        result = 31 * result + _vertexList.hashCode()
        result = 31 * result + _adjacencyMatrix.hashCode()
        return result
    }
}
