package graphs.adjacencymap

import graphs.MutableGraph

open class AdjacencyMapMutableGraphImpl<Vertex>(isDirected: Boolean) :
    MutableGraph<Vertex>,
    AdjacencyMapGraph<Vertex, Vertex>(isDirected) {

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
            values.forEach { it.remove(vertex) }
        return res
    }

    override fun addEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        return if (!get(from)!!.add(to))
            false
        else if (!isDirected)
            get(to)!!.add(from)
        else true
    }

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        val oneWay = get(from)!!.remove(to)

        return if (!isDirected && oneWay)
            get(to)!!.remove(from)
        else oneWay
    }

    override fun Vertex.getVertex(): Vertex = this
}
