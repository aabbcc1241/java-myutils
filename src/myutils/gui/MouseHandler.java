package myutils.gui;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener,
        MouseWheelListener {

    public Mouse mouseMoved = new Mouse();
    public Mouse left = new Mouse();
    public Mouse middle = new Mouse();
    public Mouse right = new Mouse();
    public int amountScrolled = 0;
    CanvasShell canvasShell;

    public MouseHandler(CanvasShell canvasShell) {
        this.canvasShell = canvasShell;
        canvasShell.addMouseListener(this);
        canvasShell.addMouseMotionListener(this);
        canvasShell.addMouseWheelListener(this);
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
        mouseMoved.toggle(e.getX(), e.getY(), true, canvasShell.screen);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll(e.getUnitsToScroll());
    }

    private void toggle(int button, int x, int y, boolean isClicked) {
        switch (button) {
            case 1:
                left.toggle(x, y, isClicked, canvasShell.screen);
                break;
            case 2:
                middle.toggle(x, y, isClicked, canvasShell.screen);
                break;
            case 3:
                right.toggle(x, y, isClicked, canvasShell.screen);
                break;
            default:

        }
    }

    private void scroll(int unitsToScroll) {
        amountScrolled -= unitsToScroll;
    }
}
