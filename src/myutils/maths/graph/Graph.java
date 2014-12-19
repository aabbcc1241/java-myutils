package myutils.maths.graph;

import myutils.Utils;
import myutils.Vector2D;

/**
 * Created by beenotung on 12/12/2014.
 */
public class Graph {
    public float xMin;
    public float yMin;
    public float xMax;
    public float yMax;

    private EdgeManager edgeManager;
    private VertexManager vertexManager;

    public Graph(float xMin, float yMin, float xMax, float yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        vertexManager = new VertexManager();
        edgeManager = new EdgeManager();
    }

    public void newVertex() {
        vertexManager.addVertex(new Vertex(new Vector2D(Utils.random.nextFloat(xMin, xMax), Utils.random.nextFloat(yMin, yMax))));
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
