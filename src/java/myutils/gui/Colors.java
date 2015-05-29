package myutils.gui;

import myutils.CollectionUtils;

import java.awt.*;

@SuppressWarnings("UnusedDeclaration")
public class Colors {
    private static int get(double d) {
        return (int) Math.round(d * 255) & 255;
    }

    @SuppressWarnings("SameParameterValue")
    public static int getRawCode(double r, double g, double b) {
        return get(r) << 16 | get(g) << 8 | get(b);
    }

    public static int getRawCode(int r, int g, int b) {
        return r << 16 | g << 8 | b;
    }

    public static int getRawCode(RGB rgb) {
        return getRawCode(rgb.r, rgb.g, rgb.b);
    }

    public static RGB getRGB(int rawCode) {
        int r = (rawCode >> 16) & 0xFF;
        int g = (rawCode >> 8) & 0xFF;
        int b = rawCode & 0xFF;
        return new RGB(r, g, b);
    }

    public static Color getColor(RGB rgb) {
        return new java.awt.Color(rgb.r, rgb.g, rgb.b);
    }

    public static Color getColor() {
        RGB rgb = new RGB();
        return new Color(rgb.r, rgb.g, rgb.b);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class RGB {
        public int r, g, b;

        public RGB() {
            r = CollectionUtils.random.nextInt(256);
            g = CollectionUtils.random.nextInt(256);
            b = CollectionUtils.random.nextInt(256);
        }

        public RGB(int rgb) {
            r = g = b = rgb;
        }

        public RGB(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public void add(RGB rgb) {
            r += rgb.r;
            g += rgb.g;
            b += rgb.b;
        }

        public void subtract(RGB rgb) {
            r -= rgb.r;
            g -= rgb.g;
            b -= rgb.b;
        }
    }
}
