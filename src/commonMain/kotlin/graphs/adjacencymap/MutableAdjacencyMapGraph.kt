package graphs.adjacencymap

import graphs.baseinterfaces.GraphBase
import graphs.baseinterfaces.Mutable

abstract class MutableAdjacencyMapGraph<Vertex, in EdgeValue> (isDirected: Boolean) :
    GraphBase<Vertex>,
    Mutable<Vertex, EdgeValue>,
    AdjacencyMapGraph<Vertex>(isDirected) {

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

    override fun removeEdge(from: Vertex, to: Vertex): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        val res = get(from)!!.remove(to)
        return if (!isDirected && res)
            get(to)!!.remove(from)
        else res
    }

    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue?): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        val res = get(from)!!.add(to)
        return if (!isDirected && res)
            get(to)!!.add(from)
        else res
    }
}
