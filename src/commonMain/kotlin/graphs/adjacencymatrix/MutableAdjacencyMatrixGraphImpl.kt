package graphs.adjacencymatrix

import graphs.MutableGraph
import graphs.exceptions.VertexNotFoundException

class MutableAdjacencyMatrixGraphImpl<Vertex>(isDirected: Boolean) :
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MutableAdjacencyMatrixGraphImpl<*>) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
