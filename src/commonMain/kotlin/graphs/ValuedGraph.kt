package graphs

interface ValuedGraph<Vertex, EdgeValue> :
    Graph<Vertex, Pair<Vertex, EdgeValue>>
