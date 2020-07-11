package graphs.baseinterfaces

interface Valued<in Vertex, EdgeValue> {
    fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue?
}
