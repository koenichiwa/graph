package graphs.abstractimpl

import graphs.ValuedMutableGraph

abstract class ValuedAdjacencyMapMutableGraph<Vertex, EdgeValue> :
    ValuedMutableGraph<Vertex, EdgeValue>,
    AdjacencyMapMutableGraph<Vertex, Pair<Vertex, EdgeValue>>() {

    override fun Pair<Vertex, EdgeValue>.getVertex(): Vertex = this.first
}
