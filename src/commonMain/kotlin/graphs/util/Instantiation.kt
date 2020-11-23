package graphs.util

import graphs.MutableGraph
import graphs.ValuedGraph
import graphs.ValuedMutableGraph
import graphs.adjacencymap.MutableAdjacencyMapGraphImpl
import graphs.adjacencymap.MutableValuedAdjacencyMapGraphImpl
import graphs.baseinterfaces.Graph

fun <Vertex, EdgeValue> mutableValuedGraphOf(
    isDirected: Boolean = true,
    vararg vertexEdgePairs: Pair<Vertex, MutableMap<Vertex, EdgeValue>>
): ValuedMutableGraph<Vertex, EdgeValue> = mutableValuedGraphOf(
    isDirected,
    mutableMapOf<Vertex, MutableMap<Vertex, EdgeValue>>()
        .also { it.putAll(vertexEdgePairs) }
)

fun <Vertex, EdgeValue> mutableValuedGraphOf(
    isDirected: Boolean = true,
    vertexEdgeMap: Map<Vertex, Map<Vertex, EdgeValue>> = emptyMap()
): ValuedMutableGraph<Vertex, EdgeValue> = MutableValuedAdjacencyMapGraphImpl<Vertex, EdgeValue>(isDirected)
    .apply {
        vertexEdgeMap.forEach { addVertex(it.key) }
        vertexEdgeMap.forEach {
            from ->
            from.value.forEach {
                to ->
                addEdge(from.key, to.key, to.value)
            }
        }
    }

fun <Vertex, EdgeValue> valuedGraphOf(
    isDirected: Boolean = true,
    vararg vertexEdgePairs: Pair<Vertex, Map<Vertex, EdgeValue>>
): ValuedGraph<Vertex, EdgeValue> = valuedGraphOf(
    isDirected,
    mutableMapOf<Vertex, Map<Vertex, EdgeValue>>()
        .also { it.putAll(vertexEdgePairs) }
)

fun <Vertex, EdgeValue> valuedGraphOf(
    isDirected: Boolean = true,
    vertexEdgeMap: Map<Vertex, Map<Vertex, EdgeValue>> = emptyMap()
): ValuedGraph<Vertex, EdgeValue> = mutableValuedGraphOf(isDirected, vertexEdgeMap)

fun <Vertex> mutableGraphOf(
    isDirected: Boolean = true,
    vararg vertexEdgePairs: Pair<Vertex, Set<Vertex>>
) = mutableGraphOf(
    isDirected,
    mutableMapOf<Vertex, Set<Vertex>>()
        .also { it.putAll(vertexEdgePairs) }
)

fun <Vertex> mutableGraphOf(
    isDirected: Boolean = true,
    vertexEdgeMap: Map<Vertex, Set<Vertex>> = emptyMap()
): MutableGraph<Vertex> = MutableAdjacencyMapGraphImpl<Vertex>(isDirected)
    .apply {
        vertexEdgeMap.forEach { addVertex(it.key) }
        vertexEdgeMap.forEach {
            from ->
            from.value.forEach {
                to ->
                addEdge(from.key, to)
            }
        }
    }

fun <Vertex> graphOf(
    isDirected: Boolean = true,
    vararg vertexEdgePairs: Pair<Vertex, Set<Vertex>>
) = graphOf(
    isDirected,
    mutableMapOf<Vertex, Set<Vertex>>()
        .also { it.putAll(vertexEdgePairs) }
)

fun <Vertex> graphOf(
    isDirected: Boolean = true,
    vertexEdgeMap: Map<Vertex, Set<Vertex>> = emptyMap()
): Graph<Vertex> = mutableGraphOf(isDirected, vertexEdgeMap)
