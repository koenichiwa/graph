package graphs

import graphs.baseinterfaces.GraphBase
import graphs.baseinterfaces.Valued

interface ValuedGraph<Vertex, EdgeValue> :
    GraphBase<Vertex>,
    Valued<Vertex, EdgeValue>
