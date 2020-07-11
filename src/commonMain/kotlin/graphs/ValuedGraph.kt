package graphs

import graphs.baseinterfaces.Graph
import graphs.baseinterfaces.Valued

interface ValuedGraph<Vertex, out EdgeValue> :
    Graph<Vertex>,
    Valued<Vertex, EdgeValue>
