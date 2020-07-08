package graphs.impl

import graphs.DirectedGraph
import graphs.abstractimpl.ValuedAdjacencyMapMutableGraph

open class ValuedDirectedAdjacencyMapMutableGraph<Vertex, EdgeValue> :
    DirectedGraph<Vertex, Pair<Vertex, EdgeValue>>,
    ValuedAdjacencyMapMutableGraph<Vertex, EdgeValue>() {

    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (!containsVertex(to))
            TODO("Throw exception")

        return get(from)?.add(to to value)
            ?: TODO("Throw exception")
    }

    override fun removeEdge(from: Vertex, to: Vertex): EdgeValue? =
        get(from)?.run { first { it.first == to }.also { this.remove(it) }.second }
}
