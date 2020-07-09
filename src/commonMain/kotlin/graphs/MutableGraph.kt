package graphs

import graphs.baseinterfaces.GraphBase
import graphs.baseinterfaces.Mutable

interface MutableGraph <Vertex> :
    GraphBase<Vertex, Vertex>,
    Mutable<Vertex, Vertex>
