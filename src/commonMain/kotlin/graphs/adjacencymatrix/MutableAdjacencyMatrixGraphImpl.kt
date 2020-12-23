package graphs.adjacencymatrix

import graphs.MutableGraph
import graphs.exceptions.VertexNotFoundException

open class MutableAdjacencyMatrixGraphImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    MutableAdjacencyMatrixGraph<Vertex>(isDirected) {
    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        val fromIndex = _vertexList.indexOf(from)
        if (fromIndex < 0)
            throw VertexNotFoundException(from)
        val toIndex = _vertexList.indexOf(to)
        if (toIndex < 0)
            throw VertexNotFoundException(to)

        _adjacencyMatrix[fromIndex][toIndex] = true
        if (!isDirected)
            _adjacencyMatrix[toIndex][fromIndex] = true
        return true
    }
}
