package graphs.adjacencymatrix

import graphs.MutableGraph

open class MutableAdjacencyMatrixImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    MutableAdjacencyMatrix<Vertex>(isDirected) {
    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex < 0)
            return false
        val toIndex = _vertexList.indexOf(to)
        if (toIndex < 0)
            return false

        _adjacencyMatrix[fromIndex][toIndex] = true
        if (!isDirected)
            _adjacencyMatrix[toIndex][fromIndex] = true
        return true
    }
}
