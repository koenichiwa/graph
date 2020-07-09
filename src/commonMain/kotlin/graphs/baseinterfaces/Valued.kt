package graphs.baseinterfaces

interface Valued<in Vertex, EdgeValue> {
    fun getEdgeValue(from: Vertex, to: Vertex): EdgeValue?
    fun putEdgeValue(from: Vertex, to: Vertex, value: EdgeValue)
    fun removeEdgeValue(from: Vertex, to: Vertex): Boolean
}
