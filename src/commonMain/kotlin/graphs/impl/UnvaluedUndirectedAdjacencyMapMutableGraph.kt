package graphs.impl

import graphs.UndirectedGraph
import graphs.abstractimpl.UnvaluedAdjacencyMapMutableGraph

open class UnvaluedUndirectedAdjacencyMapMutableGraph<Vertex> :
    UndirectedGraph<Vertex, Vertex>,
    UnvaluedAdjacencyMapMutableGraph<Vertex>() {

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (! containsVertex(from) || !containsVertex(to))
            TODO("Throw exception")

        return get(from)!!.add(to) && get(to)!!.add(from)
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean =
        get(from)?.remove(to)
            ?: get(to)?.remove(from)
            ?: false
}
