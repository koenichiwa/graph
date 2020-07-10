package graphs

import graphs.baseinterfaces.Graph
import graphs.baseinterfaces.Valued

interface ValuedGraph<Vertex, EdgeValue> :
    Graph<Vertex>,
    Valued<Vertex, EdgeValue>
