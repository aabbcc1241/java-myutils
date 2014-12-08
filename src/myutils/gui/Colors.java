package myutils.gui;

import myutils.Utils;

@SuppressWarnings("UnusedDeclaration")
public class Colors {
    private static int get(double d) {
        return (int) Math.round(d * 255) & 255;
    }

    @SuppressWarnings("SameParameterValue")
    public static int decode(double r, double g, double b) {
        return get(r) << 16 | get(g) << 8 | get(b);
    }

    public static int decode(int r, int g, int b) {
        return r << 16 | g << 8 | b;
    }

    public static void decode(int rawCode, RGB rgbCode) {
        rgbCode.r = (rawCode >> 16) & 0xFF;
        rgbCode.g = (rawCode >> 8) & 0xFF;
        rgbCode.b = rawCode & 0xFF;
    }

    public static RGB decode(int rawCode) {
        RGB rgbCode = new RGB();
        rgbCode.r = (rawCode >> 16) & 0xFF;
        rgbCode.g = (rawCode >> 8) & 0xFF;
        rgbCode.b = rawCode & 0xFF;
        return rgbCode;
    }

    public static java.awt.Color getColor(RGB rgb) {
        return new java.awt.Color(rgb.r, rgb.g, rgb.b);
    }

    public static java.awt.Color getColor() {
        RGB rgb = new RGB();
        return new java.awt.Color(rgb.r, rgb.g, rgb.b);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class RGB {
        public int r, g, b;

        public RGB() {
            r = Utils.random.nextInt(256);
            g = Utils.random.nextInt(256);
            b = Utils.random.nextInt(256);
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
