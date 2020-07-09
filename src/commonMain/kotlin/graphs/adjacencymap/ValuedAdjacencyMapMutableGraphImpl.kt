package graphs.adjacencymap

import graphs.ValuedMutableGraph
import graphs.exceptions.VertexNotFoundException

open class ValuedAdjacencyMapMutableGraphImpl<Vertex, EdgeValue>(isDirected: Boolean) :
    ValuedMutableGraph<Vertex, EdgeValue>,
    AdjacencyMapGraph<Vertex, Pair<Vertex, EdgeValue>>(isDirected) {

    override fun Pair<Vertex, EdgeValue>.getVertex(): Vertex = this.first

    override fun addVertex(vertex: Vertex): Boolean {
        return if (get(vertex) != null)
            false
        else {
            put(vertex, mutableSetOf())
            true
        }
    }

    override fun removeVertex(vertex: Vertex): Boolean {
        val res = remove(vertex) != null
        if (res)
            values.forEach { set -> set.removeAll { it.first == vertex } }
        return res
    }

    override fun addEdge(from: Vertex, to: Pair<Vertex, EdgeValue>): Boolean {
        if (!containsVertex(from))
            throw VertexNotFoundException(from)
        if (!containsVertex(to.first))
            throw VertexNotFoundException(to)

        return if (!get(from)!!.add(to))
            false
        else if (!isDirected)
            get(to.first)!!.add(from to to.second)
        else true
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            throw VertexNotFoundException(from)
        if (!containsVertex(to))
            throw VertexNotFoundException(to)

        val oneWay = get(from)!!
            .firstOrNull { to == it.getVertex() }
            ?.let { get(from)!!.remove(it) }
            ?: false

        return if (!isDirected && oneWay)
            get(to)!!
                .firstOrNull { from == it.getVertex() }
                ?.let { get(to)!!.remove(it) }
                ?: false
        else oneWay
    }
}
