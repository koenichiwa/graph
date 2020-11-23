package graphs.adjacencymatrix

import graphs.baseinterfaces.Graph

abstract class AdjacencyMatrixGraph<Vertex>(override val isDirected: Boolean) : Graph<Vertex> {
    protected val _vertexList = mutableListOf<Vertex>()
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

        return _adjacencyMatrix[vertexIndex].mapIndexed { index, _ -> _vertexList[index] }.toSet()
    }
}
