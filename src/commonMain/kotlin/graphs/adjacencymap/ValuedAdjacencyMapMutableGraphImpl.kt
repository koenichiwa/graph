package graphs.adjacencymap

import graphs.ValuedGraph
import graphs.ValuedMutableGraph

open class ValuedAdjacencyMapMutableGraphImpl<Vertex, EdgeValue>(isDirected: Boolean) :
    ValuedGraph<Vertex, EdgeValue>,
    ValuedMutableGraph<Vertex, EdgeValue>,
    MutableAdjacencyMapGraph<Vertex, EdgeValue>(isDirected) {

    private val _edgeValueMap = mutableMapOf<Pair<Vertex, Vertex>, EdgeValue>()

    override fun addEdge(from: Vertex, to: Vertex, value: EdgeValue): Boolean {
        if (!containsVertex(from))
            return false
        if (!containsVertex(to))
            return false

        return if (!get(from)!!.add(to)) {
            putEdgeValue(from, to, value)
            false
        } else if (!isDirected)
            get(to)!!.add(from)
        else true
    }

    override fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue? =
        _edgeValueMap[from to to]

    override fun putEdgeValue(from: Vertex, to: Vertex, value: EdgeValue) {
        _edgeValueMap[from to to] = value
        if (!isDirected)
            _edgeValueMap[to to from] = value
    }

    override fun removeEdgeValue(from: Vertex, to: Vertex): Boolean {
        val res = _edgeValueMap.remove(from to to) != null
        if (!isDirected)
            return res && (_edgeValueMap.remove(to to from) != null)
        return res
    }
}
