package myutils;

public class Pixels {
	public int[] pixels;
	public int xOffset = 0;
	public int yOffset = 0;

	Pixels(int[] p) {
		this.pixels = p;
	}

	public void clear(int c) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = c;
	}

	public static boolean inside(int x, int y, int xMin, int yMin, int xMax, int yMax) {
		return (x >= xMin) && (x <= xMax) && (y >= yMin) && (y <= yMax);
	}
}
