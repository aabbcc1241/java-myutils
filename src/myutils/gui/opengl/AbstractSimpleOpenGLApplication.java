package myutils.gui.opengl;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * Created by beenotung on 1/2/15.
 */

public abstract class AbstractSimpleOpenGLApplication extends AbstractOpenGLApplication {

    protected void key_escape() {
        GLFW.glfwSetWindowShouldClose(window, GL_TRUE);
    }

    protected abstract void myKeyInvoke(long window, int key, int scanCode, int action, int mode);

    @Override
    protected void keyInvoke(long window, int key, int scanCode, int action, int mode) {
        switch (key) {
            case GLFW_KEY_ESCAPE:
                key_escape();
                break;
            default:
                myKeyInvoke(window, key, scanCode, action, mode);
                break;
        }
    }
}