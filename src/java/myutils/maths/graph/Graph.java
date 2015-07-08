package myutils.maths.graph;

import myutils.CollectionUtils;
import myutils.maths.Vector2D;

/**
 * Created by beenotung on 12/12/2014.
 */
public class Graph {
    public Range2D range2D;

    private EdgeManager edgeManager;
    private VertexManager vertexManager;

    public Graph(Range2D range2D) {
        this.range2D = range2D;
        vertexManager = new VertexManager();
        edgeManager = new EdgeManager();
    }

    public void newVertex() {
        vertexManager.addVertex(
                new Vertex(new Vector2D(CollectionUtils.random.nextFloat((int) range2D.xMin, (int) range2D.xMax), CollectionUtils.random.nextFloat((int) range2D.yMin, (int) range2D.yMax))));
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

    public void init() {
        edgeManager.init();
        vertexManager.init();
    }
}
