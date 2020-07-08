package graphs.adjacencymap

import graphs.DirectedGraph

open class UnvaluedDirectedAdjacencyMapMutableGraph<Vertex> :
    DirectedGraph<Vertex, Vertex>,
    UnvaluedAdjacencyMapMutableGraph<Vertex>() {

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(to))
            TODO("Throw exception")

        return get(from)?.add(to)
            ?: TODO("Throw exception")
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean =
        get(from)?.remove(to) ?: false
}
