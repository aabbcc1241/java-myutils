package myutils.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
public class KeyHandler implements KeyListener {

    public final Key esc = new Key();
    public final Key up = new Key();
    public final Key down = new Key();
    public final Key left = new Key();
    public final Key right = new Key();
    public final Key pageup = new Key();
    public final Key pagedown = new Key();
    public final Key equal = new Key();
    public final Key add = new Key();
    public final Key subtract = new Key();
    public final Key q = new Key();
    public final Key e = new Key();
    public final Key c = new Key();
    public final Key x = new Key();
    public final Key openBracket = new Key();
    public final Key closeBracket = new Key();
    public final Key comma = new Key();
    public final Key period = new Key();

    public final Key r = new Key();

    public KeyHandler(CanvasShell canvasShell) {
        canvasShell.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void toggle(int keyCode, boolean isPressed) {
        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up.toggle(isPressed);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down.toggle(isPressed);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left.toggle(isPressed);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right.toggle(isPressed);
                break;
            case KeyEvent.VK_PAGE_UP:
                pageup.toggle(isPressed);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                pagedown.toggle(isPressed);
                break;
            case KeyEvent.VK_EQUALS:
                equal.toggle(isPressed);
                break;
            case KeyEvent.VK_ESCAPE:
                esc.toggle(isPressed);
                break;
            case KeyEvent.VK_R:
                r.toggle(isPressed);
                break;
            case KeyEvent.VK_ADD:
                add.toggle(isPressed);
                break;
            case KeyEvent.VK_SUBTRACT:
                subtract.toggle(isPressed);
                break;
            case KeyEvent.VK_Q:
                q.toggle(isPressed);
                break;
            case KeyEvent.VK_E:
                e.toggle(isPressed);
                break;
            case KeyEvent.VK_C:
                c.toggle(isPressed);
                break;
            case KeyEvent.VK_X:
                x.toggle(isPressed);
                break;
            case KeyEvent.VK_OPEN_BRACKET:
                openBracket.toggle(isPressed);
                break;
            case KeyEvent.VK_CLOSE_BRACKET:
                closeBracket.toggle(isPressed);
                break;
            case KeyEvent.VK_COMMA:
                comma.toggle(isPressed);
                break;
            case KeyEvent.VK_PERIOD:
                period.toggle(isPressed);
                break;
            default:
                System.out.println("new keyCode" + keyCode);
        }
    }
}
