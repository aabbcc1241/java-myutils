package myutils;

public class Vector2D {
	public double x;
	public double y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public void setRandom() {
		x = Utils.random.nextDouble() * 2 - 1;
		y = Utils.random.nextDouble() * 2 - 1;
	}

	public void setRandom(double r) {
		x = (Utils.random.nextDouble() * 2 - 1) * r;
		y = (Utils.random.nextDouble() * 2 - 1) * r;
	}

	public void setRandom(double xMax, double yMax) {
		x = Utils.random.nextDouble() * xMax;
		y = Utils.random.nextDouble() * yMax;
	}

	public Vector2D clone() {
		return new Vector2D(x, y);
	}

	public double getMagnitude() {
		return (Math.sqrt(x * x + y * y));
	}

	public void setMagnitude(double d) {
		double r = getMagnitude() * d;
		x *= r;
		y *= r;
	}

	public void normalize() {
		setMagnitude(1);
	}

	public void add(Vector2D pv) {
		x += pv.x;
		y += pv.y;
	}
}
