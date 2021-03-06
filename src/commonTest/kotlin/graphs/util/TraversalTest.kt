package graphs.util

import graphs.exceptions.GraphException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TraversalTest {
    @Test
    fun `bfs works`() {
        val graph = mutableGraphOf<Int>()

        assertFailsWith(GraphException::class) {
            graph.bfs(1)
        }

        graph.addVertex(1)

        assertEquals(listOf(1), graph.bfs(1).toList())

        graph.addVertex(2)
        graph.addEdge(1, 2)

        assertEquals(listOf(1, 2), graph.bfs(1).toList())
        assertEquals(listOf(2), graph.bfs(2).toList())

        graph.addVertex(3)
        graph.addEdge(1, 3)

        assertEquals(listOf(1, 2, 3), graph.bfs(1).toList())

        graph.addVertex(4)
        graph.addEdge(2, 4)

        assertEquals(listOf(1, 2, 3, 4), graph.bfs(1).toList())

        graph.addVertex(5)
        graph.addEdge(3, 5)

        assertEquals(listOf(1, 2, 3, 4, 5), graph.bfs(1).toList())

        graph.addVertex(6)
        graph.addEdge(5, 6)

        assertEquals(listOf(1, 2, 3, 4, 5, 6), graph.bfs(1).toList())
    }

    @Test
    fun `dfs works`() {
        val graph = mutableGraphOf<Int>()

        assertFailsWith(GraphException::class) {
            graph.dfs(1)
        }

        graph.addVertex(1)

        assertEquals(listOf(1), graph.dfs(1).toList())

        graph.addVertex(2)
        graph.addEdge(1, 2)

        assertEquals(listOf(1, 2), graph.dfs(1).toList())
        assertEquals(listOf(2), graph.dfs(2).toList())

        graph.addVertex(3)
        graph.addEdge(1, 3)

        assertEquals(listOf(1, 3, 2), graph.dfs(1).toList())

        graph.addVertex(4)
        graph.addEdge(2, 4)

        assertEquals(listOf(1, 3, 2, 4), graph.dfs(1).toList())

        graph.addVertex(5)
        graph.addEdge(3, 5)

        assertEquals(listOf(1, 3, 5, 2, 4), graph.dfs(1).toList())

        graph.addVertex(6)
        graph.addEdge(5, 6)

        assertEquals(listOf(1, 3, 5, 6, 2, 4), graph.dfs(1).toList())
    }
}
