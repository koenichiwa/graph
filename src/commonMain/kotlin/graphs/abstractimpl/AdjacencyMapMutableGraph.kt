package graphs.abstractimpl

import graphs.MutableGraph

abstract class AdjacencyMapMutableGraph<Vertex, EdgeType> :
    MutableGraph<Vertex, EdgeType>,
    MutableMap<Vertex, MutableSet<EdgeType>> {

    private val _adjacencyMap = mutableMapOf<Vertex, MutableSet<EdgeType>>()

    override fun addVertex(vertex: Vertex): Boolean =
        put(vertex, mutableSetOf()) == null

    override fun removeVertex(vertex: Vertex): Boolean =
        remove(vertex) != null

    override fun containsVertex(vertex: Vertex): Boolean =
        containsKey(vertex)

    override fun isAdjacent(from: Vertex, to: Vertex): Boolean =
        neighbors(from)?.contains(to) ?: false

    override fun getNeighboringEdges(vertex: Vertex): Set<EdgeType>? =
        get(vertex)

    override fun neighbors(vertex: Vertex): Set<Vertex>? =
        get(vertex)?.mapTo(mutableSetOf()) { it.getVertex() }

    override fun equals(other: Any?): Boolean =
        other === this ||
            other is AdjacencyMapMutableGraph<*, *> &&
            this.toMap() == other.toMap()

    override fun hashCode(): Int =
        _adjacencyMap.hashCode()

    // Map
    override val size: Int
        get() = _adjacencyMap.size
    override val entries: MutableSet<MutableMap.MutableEntry<Vertex, MutableSet<EdgeType>>>
        get() = _adjacencyMap.entries
    override val keys: MutableSet<Vertex>
        get() = _adjacencyMap.keys
    override val values: MutableCollection<MutableSet<EdgeType>>
        get() = _adjacencyMap.values

    override fun containsKey(key: Vertex): Boolean = _adjacencyMap.containsKey(key)
    override fun containsValue(value: MutableSet<EdgeType>): Boolean = _adjacencyMap.containsValue(value)
    override fun get(key: Vertex): MutableSet<EdgeType>? = _adjacencyMap[key]
    override fun isEmpty(): Boolean = _adjacencyMap.isEmpty()
    override fun clear() = _adjacencyMap.clear()
    override fun put(key: Vertex, value: MutableSet<EdgeType>): MutableSet<EdgeType>? = _adjacencyMap.put(key, value)
    override fun putAll(from: Map<out Vertex, MutableSet<EdgeType>>) = _adjacencyMap.putAll(from)
    override fun remove(key: Vertex): MutableSet<EdgeType>? = _adjacencyMap.remove(key)
}
