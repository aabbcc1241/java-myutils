package myutils.maths.graph;

import myutils.Utils;

/**
 * Created by beenotung on 12/12/2014.
 */
public class Graph {
    public Range2D range2D;

    public EdgeManager edgeManager;
    public VertexManager vertexManager;

    public Graph(Range2D range2D) {
        this.range2D = range2D;
        vertexManager = new VertexManager();
        edgeManager = new EdgeManager();
    }

    public void newVertex() {
        vertexManager.addVertex(new Vertex(new Vector2D(Utils.random.nextFloat(range2D.xMin, range2D.xMax), Utils.random.nextFloat(range2D.yMin, range2D.yMax))));
    }

    public void newEdge() {
        Vertex v1 = vertexManager.getRandomVertex();
        Vertex v2;
        do {
            v2 = vertexManager.getRandomVertex();
        }
        while (v1.equals(v2));
        edgeManager.addEdge(v1, v2);
    }
}
