package myutils.gui;

import myutils.Vector2D;

public class Pixels {
    protected static float DEFAULT_ZOOM_RATE = 1.05f;
    public float scale, xOffset, yOffset;
    public CanvasJFrame canvasJFrame;
    protected int[] pixels;

    Pixels(int[] p, CanvasJFrame canvasJFrame) {
        this.pixels = p;
        this.canvasJFrame = canvasJFrame;
        resetOffsetScale();
    }

    public static boolean inside(int x, int y, int xMin, int yMin, int xMax, int yMax) {
        return (x >= xMin) && (x <= xMax) && (y >= yMin) && (y <= yMax);
    }

    public void add(Vector2D l, int color) {
        int ix = (int) Math.round((l.x + -xOffset) * scale + canvasJFrame.cx);
        int iy = (int) Math.round((l.y - yOffset) * scale + canvasJFrame.cy);
        if (inside(ix, iy, 0, 0, canvasJFrame.WIDTH - 1, canvasJFrame.HEIGHT - 1))
            pixels[ix + iy * canvasJFrame.WIDTH] = color;
    }

    public void add(float x, float y, int color) {
        int ix = (int) Math.round((x + -xOffset) * scale + canvasJFrame.cx);
        int iy = (int) Math.round((y - yOffset) * scale + canvasJFrame.cy);
        if (inside(ix, iy, 0, 0, canvasJFrame.WIDTH - 1, canvasJFrame.HEIGHT - 1))
            pixels[ix + iy * canvasJFrame.WIDTH] = color;
    }

    public void clear(int c) {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = c;
    }

    public void scrollX(int numTimesPressed) {
        xOffset += numTimesPressed / scale * Math.PI;
    }

    public void scrollY(int numTimesPressed) {
        yOffset += numTimesPressed / scale * Math.PI;
    }

    public void zoom(int r) {
        scale *= Math.pow(DEFAULT_ZOOM_RATE, r);
    }

    protected void resetOffsetScale() {
        xOffset = 0;
        yOffset = 0;
        scale = 1;
    }

    public void setOffset(Vector2D locationRelative) {
        xOffset = locationRelative.x;// * Math.PI;// scale;//Math.PI;
        yOffset = locationRelative.y;// * Math.PI;// scale;//Math.PI;
    }

    public void convertOnScreenScaled(Vector2D v, int x, int y) {
        v.x = x / scale - canvasJFrame.cx;
        v.y = y / scale - canvasJFrame.cy;
    }

    public void convertRelativeScaled(Vector2D v, int x, int y) {
        v.x = (x - canvasJFrame.cx) / scale + xOffset;
        v.y = (y - canvasJFrame.cy) / scale + yOffset;
    }

    public void convertOnScreenAbsolute(Vector2D v, int x, int y) {
        v.x = x - canvasJFrame.cx;
        v.y = y - canvasJFrame.cy;
    }

    public void convertRelativeAbsolute(Vector2D v, int x, int y) {
        v.x = x / canvasJFrame.SCALE - canvasJFrame.cx + xOffset;
        v.y = y / canvasJFrame.SCALE - canvasJFrame.cy + yOffset;
    }

}
