package myutils.maths.graph;

public class Vertex implements Cloneable {
    public Vector2D location;

    public Vertex(Vector2D location) {
        this.location = location;
    }

    public Vertex clone() {
        return new Vertex(location);
    }
}
