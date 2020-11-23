package graphs.util

import graphs.MutableGraph
import graphs.ValuedGraph
import graphs.ValuedMutableGraph
import graphs.adjacencymap.MutableAdjacencyMapGraphImpl
import graphs.adjacencymap.MutableValuedAdjacencyMapGraphImpl
import graphs.baseinterfaces.Graph

fun <Vertex, EdgeValue> mutableValuedGraphOf(
    isDirected: Boolean,
    vertexSet: Set<Vertex> = emptySet(),
    edgeSet: Set<Triple<Vertex, Vertex, EdgeValue>> = emptySet()
): ValuedMutableGraph<Vertex, EdgeValue> = MutableValuedAdjacencyMapGraphImpl<Vertex, EdgeValue>(isDirected)
    .apply {
        vertexSet.forEach { addVertex(it) }
        edgeSet.forEach { addEdge(it.first, it.second, it.third) }
    }

fun <Vertex, EdgeValue> valuedGraphOf(
    isDirected: Boolean,
    vertexSet: Set<Vertex> = emptySet(),
    edgeSet: Set<Triple<Vertex, Vertex, EdgeValue>> = emptySet()
): ValuedGraph<Vertex, EdgeValue> = mutableValuedGraphOf(isDirected, vertexSet, edgeSet)

fun <Vertex> mutableGraphOf(
    isDirected: Boolean,
    vertexSet: Set<Vertex> = emptySet(),
    edgeSet: Set<Pair<Vertex, Vertex>> = emptySet()
): MutableGraph<Vertex> = MutableAdjacencyMapGraphImpl<Vertex>(isDirected)
    .apply {
        vertexSet.forEach { addVertex(it) }
        edgeSet.forEach { addEdge(it.first, it.second) }
    }

fun <Vertex> graphOf(
    isDirected: Boolean,
    vertexSet: Set<Vertex> = emptySet(),
    edgeSet: Set<Pair<Vertex, Vertex>> = emptySet()
): Graph<Vertex> = mutableGraphOf(isDirected, vertexSet, edgeSet)
