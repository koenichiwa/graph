package graphs.abstractimpl

import graphs.UnvaluedMutableGraph

abstract class UnvaluedAdjacencyMapMutableGraph<Vertex> :
    UnvaluedMutableGraph<Vertex>,
    AdjacencyMapMutableGraph<Vertex, Vertex>() {

    override fun Vertex.getVertex(): Vertex = this
}
