package myutils.gui.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by beenotung on 1/2/15.
 */

public abstract class AbstractSimpleOpenGLApplication extends AbstractOpenGLApplication {
    @Override
    protected GLFWKeyCallback getGLFWKeyCallback() {
        return new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scanCode, int action, int mode) {
                switch (key) {
                    case GLFW_KEY_ESCAPE:
                        key_escape();
                        break;
                    default:
                        myKeyInvoke(window, key, scanCode, action, mode);
                        break;
                }
            }
        };
    }

    protected void key_escape() {
        GLFW.glfwSetWindowShouldClose(window, GL_TRUE);
    }

    protected abstract void myKeyInvoke(long window, int key, int scanCode, int action, int mode);
}