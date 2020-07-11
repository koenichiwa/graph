package graphs.exceptions

open class GraphException(message: String, cause: Throwable? = null) : Exception(message, cause)

class VertexNotFoundException(vertex: Any? = null, cause: Throwable? = null) : GraphException("Vertex not found: $vertex", cause)
