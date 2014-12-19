package myutils.maths;

import myutils.Utils;

import java.util.Vector;

@SuppressWarnings("UnusedDeclaration")
public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public static Vector2D getRandom() {
        double d = Utils.random.nextDouble() * 2 * Math.PI;
        float x = (float) Math.cos(d);
        float y = (float) Math.sin(d);
        return new Vector2D(x, y);
    }

    public static Vector2D add(Vector2D p1, Vector2D p2) {
        return new Vector2D(p1.x + p2.x, p1.y + p2.y);
    }

    public static Vector2D subtract(Vector2D p1, Vector2D p2) {
        return new Vector2D(p1.x - p2.x, p1.y - p2.y);
    }

    public static double Distance(Vector2D v1, Vector2D v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        Vector2D other = (Vector2D) obj;
        return (this.x == other.x) && (this.y == other.y);
    }

    void setRandom() {
        double d = Utils.random.nextDouble() * 2 * Math.PI;
        x = (float) Math.cos(d);
        y = (float) Math.sin(d);
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDoesntDeclareCloneNotSupportedException"})
    public Vector2D clone() {
        return new Vector2D(x, y);
    }

    public float getMagnitude() {
        return (float) (Math.sqrt(x * x + y * y));
    }

    public void setMagnitude(float d) {
        if (getMagnitude() == 0) {
            setRandom();
            x *= d;
            y *= d;
            return;
        }
        float r = 1f / getMagnitude() * d;
        x *= r;
        y *= r;
    }

    public void min(float d) {
        if (getMagnitude() < d)
            setMagnitude(d);
    }

    public void max(float d) {
        if (getMagnitude() > d)
            setMagnitude(d);
    }

    public Vector2D normalize() {
        Vector2D result = this.clone();
        result.setMagnitude(1);
        return result;
    }

    public void plus(Vector2D pv) {
        x += pv.x;
        y += pv.y;
    }

    public void minus(Vector2D pv) {
        x -= pv.x;
        y -= pv.y;
    }

    public void multiply(Vector2D pv) {
        x *= pv.x;
        y *= pv.y;
    }

    public void multiply(float r) {
        x *= r;
        y *= r;
    }

    @Override
    public String toString() {
        Vector coordination = new Vector();
        coordination.add(x);
        coordination.add(y);
        return coordination.toString();
    }
}
