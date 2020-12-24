package graphs.util

import graphs.baseinterfaces.Graph
import graphs.exceptions.VertexNotFoundException

fun <Vertex> Graph<Vertex>.dfs(startVertex: Vertex): Sequence<Vertex> {
    if (!containsVertex(startVertex))
        throw VertexNotFoundException(startVertex)

    return sequence {
        val visitedMap = mutableMapOf<Vertex, Boolean>().withDefault { false }
        val stack = ArrayDeque<Vertex>()

        stack.addFirst(startVertex)

        while (stack.isNotEmpty()) {
            val currentVertex = stack.removeFirst()

            if (!visitedMap.getValue(currentVertex)) {
                yield(currentVertex)
                visitedMap[currentVertex] = true
                this@dfs.neighbors(currentVertex)?.forEach { stack.addFirst(it) }
            }
        }
    }
}

fun <Vertex> Graph<Vertex>.bfs(startVertex: Vertex): Sequence<Vertex> {
    if (!containsVertex(startVertex))
        throw VertexNotFoundException(startVertex)

    return sequence {
        val visitedMap = mutableMapOf<Vertex, Boolean>().withDefault { false }
        val queue = ArrayDeque<Vertex>()

        queue.addLast(startVertex)

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()

            if (!visitedMap.getValue(currentVertex)) {
                yield(currentVertex)
                visitedMap[currentVertex] = true
                this@bfs.neighbors(currentVertex)?.forEach { queue.addLast(it) }
            }
        }
    }
}
