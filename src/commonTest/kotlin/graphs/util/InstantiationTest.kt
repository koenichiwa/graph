package graphs.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InstantiationTest {
    @Test
    fun `graphOf works()`() {
        val graph = graphOf(
            true,
            1 to setOf(2, 3, 4),
            2 to setOf(3, 4),
            3 to setOf(4),
            4 to emptySet()
        )

        assertEquals(
            mutableGraphOf(
                true,
                1 to setOf(2, 3, 4),
                2 to setOf(3, 4),
                3 to setOf(4),
                4 to emptySet()
            ),
            graph
        )

        assertEquals(
            graphOf(
                true,
                mapOf(
                    1 to setOf(2, 3, 4),
                    2 to setOf(3, 4),
                    3 to setOf(4),
                    4 to emptySet()
                )
            ),
            graph
        )

        assertTrue { (1..4).all { graph.containsVertex(it) } }

        assertEquals(graph.neighbors(1), setOf(2, 3, 4))
        assertEquals(graph.neighbors(2), setOf(3, 4))
        assertEquals(graph.neighbors(3), setOf(4))
        assertEquals(graph.neighbors(4), emptySet())
    }

    @Test
    fun `valuedGraphOf works()`() {
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

        assertEquals(
            mutableValuedGraphOf(
                true,
                1 to mapOf(2 to 1, 3 to 1, 4 to 1),
                2 to mapOf(3 to 2, 4 to 2),
                3 to mapOf(4 to 3),
                4 to emptyMap()
            ),
            graph
        )

        assertEquals(
            valuedGraphOf(
                true,
                mapOf(
                    1 to mapOf(2 to 1, 3 to 1, 4 to 1),
                    2 to mapOf(3 to 2, 4 to 2),
                    3 to mapOf(4 to 3),
                    4 to emptyMap()
                )
            ),
            graph
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
}
