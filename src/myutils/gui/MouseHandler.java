package myutils.gui;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener,
        MouseWheelListener {

    public Mouse mouseMoved = new Mouse();
    public Mouse left = new Mouse();
    public Mouse middle = new Mouse();
    public Mouse right = new Mouse();
    public int amountScrolled = 0;
    CanvasJFrame canvasJFrame;

    public MouseHandler(CanvasJFrame canvasJFrame) {
        this.canvasJFrame = canvasJFrame;
        canvasJFrame.addMouseListener(this);
        canvasJFrame.addMouseMotionListener(this);
        canvasJFrame.addMouseWheelListener(this);
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
        mouseMoved.toggle(e.getX(), e.getY(), true, canvasJFrame.screen);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll(e.getUnitsToScroll());
    }

    private void toggle(int button, int x, int y, boolean isClicked) {
        switch (button) {
            case 1:
                left.toggle(x, y, isClicked, canvasJFrame.screen);
                break;
            case 2:
                middle.toggle(x, y, isClicked, canvasJFrame.screen);
                break;
            case 3:
                right.toggle(x, y, isClicked, canvasJFrame.screen);
                break;
            default:

        }
    }

    private void scroll(int unitsToScroll) {
        amountScrolled -= unitsToScroll;
    }
}
