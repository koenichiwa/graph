package graphs.adjacencymap

import graphs.exceptions.VertexNotFoundException
import graphs.util.mutableValuedGraphOf
import graphs.util.valuedGraphOf
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ValuedAdjacencyMapMutableGraphImplTest {
    @Test
    fun `instantiation works()`() {
        val graph = valuedGraphOf(
            true,
            1 to mapOf(
                2 to 1,
                3 to 1,
                4 to 1,
            ),
            2 to mapOf(
                3 to 2,
                4 to 2,
            ),
            3 to mapOf(
                4 to 3,
            ),
            4 to emptyMap()
        )

        assertTrue { (1..4).all { graph.containsVertex(it) } }

        assertEquals(graph.neighbors(1), setOf(2, 3, 4))
        assertTrue { graph.neighbors(1)?.all { graph.getEdgeValue(1, it) == 1 } ?: false }

        assertEquals(graph.neighbors(2), setOf(3, 4))
        assertTrue { graph.neighbors(2)?.all { graph.getEdgeValue(2, it) == 2 } ?: false }

        assertEquals(graph.neighbors(3), setOf(4))
        assertTrue { graph.neighbors(3)?.all { graph.getEdgeValue(3, it) == 3 } ?: false }

        assertEquals(graph.neighbors(4), emptySet())
    }

    @Test
    fun `addVertex works`() {
        val graph = mutableValuedGraphOf<Int, Int>(false)
        assertEquals(false, graph.containsVertex(0))
        assertTrue(graph.addVertex(0))
        assertTrue(graph.containsVertex(0))
        assertFalse(graph.addVertex(0))
    }

    @Test
    fun `cannot add edge to nonexistent vertex`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        assertFailsWith(VertexNotFoundException::class) { graph.addEdge(0, 1, null) }
        graph.addVertex(1)
        assertTrue(graph.addEdge(0, 1, null))
    }

    @Test
    fun `cannot add edge from nonexistent vertex`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(1)
        assertFailsWith(VertexNotFoundException::class) { graph.addEdge(0, 1, null) }
        graph.addVertex(0)
        assertTrue(graph.addEdge(0, 1, null))
    }

    @Test
    fun `isAdjacent works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertFalse(graph.isAdjacent(0, 1))
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
    }

    @Test
    fun `neighbors works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        assertEquals(setOf(), graph.neighbors(0))
        graph.addEdge(0, 1, null)
        assertEquals(setOf(1), graph.neighbors(0))
        graph.addEdge(0, 2, null)
        assertEquals(setOf(1, 2), graph.neighbors(0))
    }

    @Test
    fun `undirected works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
    }

    @Test
    fun `directed works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(true)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }

    @Test
    fun `removeVertex works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        assertTrue(graph.containsVertex(0))
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
        graph.removeVertex(0)
        assertFalse(graph.containsVertex(0))
        assertTrue(graph.containsVertex(1))
        assertFalse(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }

    @Test
    fun `removeEdge works`() {
        val graph = mutableValuedGraphOf<Int, Int?>(false)
        graph.addVertex(0)
        assertTrue(graph.containsVertex(0))
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
        graph.removeEdge(0, 1)
        assertTrue(graph.containsVertex(0))
        assertTrue(graph.containsVertex(1))
        assertFalse(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }

    @Test
    fun `getEdgeValue works`() {
        val value = Random.nextInt()
        val value2 = Random.nextInt()
        val graph = mutableValuedGraphOf<Int, Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertNull(graph.getEdgeValue(0, 1))
        graph.addEdge(0, 1, value)
        assertEquals(value, graph.getEdgeValue(0, 1))
        assertEquals(value, graph.getEdgeValue(1, 0))

        val directedGraph = mutableValuedGraphOf<Int, Int>(true)
        directedGraph.addVertex(0)
        directedGraph.addVertex(1)
        assertNull(directedGraph.getEdgeValue(0, 1))
        directedGraph.addEdge(0, 1, value)
        assertEquals(value, directedGraph.getEdgeValue(0, 1))
        assertNull(directedGraph.getEdgeValue(1, 0))
        directedGraph.addEdge(1, 0, value2)
        assertEquals(value2, directedGraph.getEdgeValue(1, 0))
    }

    @Test
    fun `updateEdgeValue works`() {
        val value = Random.nextInt()
        val value2 = Random.nextInt()
        val graph = mutableValuedGraphOf<Int, Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertNull(graph.getEdgeValue(0, 1))
        graph.addEdge(0, 1, value)
        assertEquals(value, graph.getEdgeValue(0, 1))
        assertEquals(value, graph.getEdgeValue(1, 0))
        graph.updateEdgeValue(0, 1, value2)
        assertEquals(value2, graph.getEdgeValue(0, 1))
        assertEquals(value2, graph.getEdgeValue(1, 0))

        val directedGraph = mutableValuedGraphOf<Int, Int>(true)
        directedGraph.addVertex(0)
        directedGraph.addVertex(1)
        assertNull(directedGraph.getEdgeValue(0, 1))
        directedGraph.addEdge(0, 1, value)
        assertEquals(value, directedGraph.getEdgeValue(0, 1))
        assertNull(directedGraph.getEdgeValue(1, 0))
        directedGraph.updateEdgeValue(0, 1, value2)
        assertEquals(value2, directedGraph.getEdgeValue(0, 1))
        assertNull(directedGraph.getEdgeValue(1, 0))
    }
}
