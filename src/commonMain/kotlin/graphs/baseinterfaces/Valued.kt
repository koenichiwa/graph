package graphs.baseinterfaces

interface Valued<in Vertex, out EdgeValue> {
    fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue?
}
