package myutils.gui;

import java.awt.event.*;

@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
public class MouseHandler implements MouseListener, MouseMotionListener,
        MouseWheelListener {
    public final Mouse right = new Mouse();
    public final IterativeCanvas iterativeCanvas;
    public final Mouse mouseMoved = new Mouse();
    public final Mouse left = new Mouse();
    public final Mouse middle = new Mouse();
    public int amountScrolled = 0;

    public MouseHandler(IterativeCanvas iterativeCanvas) {
        this.iterativeCanvas = iterativeCanvas;
        iterativeCanvas.addMouseListener(this);
        iterativeCanvas.addMouseMotionListener(this);
        iterativeCanvas.addMouseWheelListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        toggle(e.getButton(), e.getX(), e.getY(), true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMoved.toggle(e.getX(), e.getY(), true, iterativeCanvas.screen);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll(e.getUnitsToScroll());
    }

    @SuppressWarnings("SameParameterValue")
    private void toggle(int button, int x, int y, boolean isClicked) {
        switch (button) {
            case 1:
                left.toggle(x, y, isClicked, iterativeCanvas.screen);
                break;
            case 2:
                middle.toggle(x, y, isClicked, iterativeCanvas.screen);
                break;
            case 3:
                right.toggle(x, y, isClicked, iterativeCanvas.screen);
                break;
            default:
        }
    }

    private void scroll(int unitsToScroll) {
        amountScrolled -= unitsToScroll;
    }
}
