package graphs.adjacencymap

import graphs.util.mutableGraphOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdjacencyMapMutableGraphImplTest {
    @Test
    fun `addVertex works`() {
        val graph = mutableGraphOf<Int>(false)
        assertFalse(graph.containsVertex(0))
        assertTrue(graph.addVertex(0))
        assertTrue(graph.containsVertex(0))
        assertFalse(graph.addVertex(0))
    }

    @Test
    fun `cannot add edge to inexistent vertex`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        assertFalse(graph.addEdge(0, 1))
        graph.addVertex(1)
        assertTrue(graph.addEdge(0, 1))
    }

    @Test
    fun `cannot add edge from inexistent vertex`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(1)
        assertFalse(graph.addEdge(0, 1))
        graph.addVertex(0)
        assertTrue(graph.addEdge(0, 1))
    }

    @Test
    fun `isAdjacent works`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertFalse(graph.isAdjacent(0, 1))
        graph.addEdge(0, 1)
        assertTrue(graph.isAdjacent(0, 1))
    }

    @Test
    fun `neighbors works`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        assertEquals(setOf(), graph.neighbors(0))
        graph.addEdge(0, 1)
        assertEquals(setOf(1), graph.neighbors(0))
        graph.addEdge(0, 2)
        assertEquals(setOf(1, 2), graph.neighbors(0))
    }

    @Test
    fun `undirected works`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
    }

    @Test
    fun `directed works`() {
        val graph = mutableGraphOf<Int>(true)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1)
        assertTrue(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }

    @Test
    fun `removeVertex works`() {
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        assertTrue(graph.containsVertex(0))
        graph.addVertex(1)
        graph.addEdge(0, 1)
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
        val graph = mutableGraphOf<Int>(false)
        graph.addVertex(0)
        assertTrue(graph.containsVertex(0))
        graph.addVertex(1)
        graph.addEdge(0, 1)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
        graph.removeEdge(0, 1)
        assertTrue(graph.containsVertex(0))
        assertTrue(graph.containsVertex(1))
        assertFalse(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }
}
