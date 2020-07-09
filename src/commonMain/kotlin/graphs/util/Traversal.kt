package graphs.util

import graphs.baseinterfaces.GraphBase

@ExperimentalStdlibApi
fun <Vertex, EdgeType> GraphBase<Vertex, EdgeType>.dfs(startVertex: Vertex): List<Vertex> {
    val traversalList = mutableListOf<Vertex>()
    val visitedMap = mutableMapOf<Vertex, Boolean>().withDefault { false }
    val stack = ArrayDeque<Vertex>()

    stack.addFirst(startVertex)

    while (stack.isNotEmpty()) {
        val currentVertex = stack.removeFirst()

        if (!visitedMap.getValue(currentVertex)) {
            traversalList.add(currentVertex)
            visitedMap[currentVertex] = true
            this.neighbors(currentVertex)?.forEach { stack.addFirst(it) }
        }
    }

    return traversalList
}

@ExperimentalStdlibApi
fun <Vertex, EdgeType> GraphBase<Vertex, EdgeType>.bfs(startVertex: Vertex): List<Vertex> {
    val traversalList = mutableListOf<Vertex>()
    val visitedMap = mutableMapOf<Vertex, Boolean>().withDefault { false }
    val queue = ArrayDeque<Vertex>()

    queue.addLast(startVertex)

    while (queue.isNotEmpty()) {
        val currentVertex = queue.removeFirst()

        if (!visitedMap.getValue(currentVertex)) {
            traversalList.add(currentVertex)
            visitedMap[currentVertex] = true
            this.neighbors(currentVertex)?.forEach { queue.addLast(it) }
        }
    }

    return traversalList
}
