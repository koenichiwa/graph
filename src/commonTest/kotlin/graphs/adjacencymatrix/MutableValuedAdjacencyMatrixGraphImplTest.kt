package graphs.adjacencymatrix

import graphs.exceptions.VertexNotFoundException
import graphs.util.mutableValuedGraphOf
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MutableValuedAdjacencyMatrixGraphImplTest {
    @Test
    fun `addVertex works`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int>(false)
        assertEquals(false, graph.containsVertex(0))
        assertTrue(graph.addVertex(0))
        assertTrue(graph.containsVertex(0))
        assertFalse(graph.addVertex(0))
    }

    @Test
    fun `cannot add edge to nonexistent vertex`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
        graph.addVertex(0)
        assertFailsWith(VertexNotFoundException::class) { graph.addEdge(0, 1, null) }
        graph.addVertex(1)
        assertTrue(graph.addEdge(0, 1, null))
    }

    @Test
    fun `cannot add edge from nonexistent vertex`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
        graph.addVertex(1)
        assertFailsWith(VertexNotFoundException::class) { graph.addEdge(0, 1, null) }
        graph.addVertex(0)
        assertTrue(graph.addEdge(0, 1, null))
    }

    @Test
    fun `isAdjacent works`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertFalse(graph.isAdjacent(0, 1))
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
    }

    @Test
    fun `neighbors works`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
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
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertTrue(graph.isAdjacent(1, 0))
    }

    @Test
    fun `directed works`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(true)
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, null)
        assertTrue(graph.isAdjacent(0, 1))
        assertFalse(graph.isAdjacent(1, 0))
    }

    @Test
    fun `removeVertex works`() {
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
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
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int?>(false)
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
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int>(false)
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
        val graph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int>(false)
        graph.addVertex(0)
        graph.addVertex(1)
        assertNull(graph.getEdgeValue(0, 1))
        graph.addEdge(0, 1, value)
        assertEquals(value, graph.getEdgeValue(0, 1))
        assertEquals(value, graph.getEdgeValue(1, 0))
        graph.updateEdgeValue(0, 1, value2)
        assertEquals(value2, graph.getEdgeValue(0, 1))
        assertEquals(value2, graph.getEdgeValue(1, 0))

        val directedGraph = MutableValuedAdjacencyMatrixGraphImpl<Int, Int>(true)
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
