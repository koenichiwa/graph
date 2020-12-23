package graphs.adjacencymatrix

import graphs.baseinterfaces.Mutable

abstract class MutableAdjacencyMatrixGraph<Vertex>(isDirected: Boolean) :
    Mutable<Vertex>,
    AdjacencyMatrixGraph<Vertex>(isDirected) {

    override fun addVertex(vertex: Vertex): Boolean {
        if (containsVertex(vertex))
            return false
        _vertexList.add(vertex)
        _adjacencyMatrix.forEach { it.add(false) }
        _adjacencyMatrix.add(MutableList(_vertexList.size) { false })
        return true
    }

    override fun removeVertex(vertex: Vertex): Boolean {
        val index = _vertexList.indexOf(vertex)
        if (index <0)
            return false
        _vertexList.removeAt(index)
        _adjacencyMatrix.removeAt(index)
        _adjacencyMatrix.forEach { it.removeAt(index) }
        return true
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex < 0)
            return false
        val toIndex = _vertexList.indexOf(to)
        if (toIndex < 0)
            return false

        _adjacencyMatrix[fromIndex][toIndex] = false
        if (!isDirected)
            _adjacencyMatrix[toIndex][fromIndex] = false
        return true
    }
}
