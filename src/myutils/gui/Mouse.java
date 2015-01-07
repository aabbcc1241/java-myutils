package myutils.gui;

import myutils.maths.graph.Vector2D;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "UnusedDeclaration"})
public class Mouse {
    public Vector2D locationOnScreenScaled = new Vector2D();
    public Vector2D locationRelativeScaled = new Vector2D();
    public Vector2D locationOnScreenAbsolute = new Vector2D();
    public Vector2D locationRelativeAbsolute = new Vector2D();
    public Vector2D locationRaw = new Vector2D();
    public boolean clicked = false;
    //public int x;
    //public int y;
    private int numTimesClicked = 0;

    public void toggle(int x, int y, boolean isClicked, Pixels screen) {
        locationRaw.x = x;
        locationRaw.y = y;
        screen.convertOnScreenScaled(locationOnScreenScaled, x, y);
        screen.convertRelativeScaled(locationRelativeScaled, x, y);
        screen.convertOnScreenAbsolute(locationOnScreenAbsolute, x, y);
        screen.convertRelativeAbsolute(locationRelativeAbsolute, x, y);
        if (clicked = isClicked)
            numTimesClicked++;
    }

    public void reset() {
        numTimesClicked = 0;
        clicked = false;
    }
}
