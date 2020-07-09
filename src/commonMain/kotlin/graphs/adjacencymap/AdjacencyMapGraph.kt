package graphs.adjacencymap

import graphs.baseinterfaces.GraphBase

abstract class AdjacencyMapGraph<Vertex> protected constructor(override val isDirected: Boolean) :
    GraphBase<Vertex>,
    MutableMap<Vertex, MutableSet<Vertex>> {

    private val _adjacencyMap = mutableMapOf<Vertex, MutableSet<Vertex>>()

    override fun containsVertex(vertex: Vertex): Boolean =
        containsKey(vertex)

    override fun isAdjacent(from: Vertex, to: Vertex): Boolean =
        neighbors(from)?.contains(to) ?: false

    override fun neighbors(vertex: Vertex): Set<Vertex>? =
        get(vertex)

    override fun equals(other: Any?): Boolean =
        other === this ||
            other is AdjacencyMapGraph<*> &&
            this.toMap() == other.toMap()

    override fun hashCode(): Int =
        _adjacencyMap.hashCode()

    // Map
    override val size: Int
        get() = _adjacencyMap.size
    override val entries: MutableSet<MutableMap.MutableEntry<Vertex, MutableSet<Vertex>>>
        get() = _adjacencyMap.entries
    override val keys: MutableSet<Vertex>
        get() = _adjacencyMap.keys
    override val values: MutableCollection<MutableSet<Vertex>>
        get() = _adjacencyMap.values

    override fun containsKey(key: Vertex): Boolean = _adjacencyMap.containsKey(key)
    override fun containsValue(value: MutableSet<Vertex>): Boolean = _adjacencyMap.containsValue(value)
    override fun get(key: Vertex): MutableSet<Vertex>? = _adjacencyMap[key]
    override fun isEmpty(): Boolean = _adjacencyMap.isEmpty()
    override fun clear() = _adjacencyMap.clear()
    override fun put(key: Vertex, value: MutableSet<Vertex>): MutableSet<Vertex>? = _adjacencyMap.put(key, value)
    override fun putAll(from: Map<out Vertex, MutableSet<Vertex>>) = _adjacencyMap.putAll(from)
    override fun remove(key: Vertex): MutableSet<Vertex>? = _adjacencyMap.remove(key)
}
