package myutils.gui.opengl;

import myutils.Utils;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by beenotung on 1/2/15.
 */

public abstract class AbstractSimpleOpenGLApplication extends AbstractOpenGLApplication {
    protected final float DEFAULT_SCROLL_SPEED = 0.2f;
    protected float scrollSpeed;
    protected float xRange, yRange, zRange;
    protected float cx, cy, cz;
    protected float vx, vy, vz;
    protected float zMin, zMax;
    protected boolean zEquilateral;

    @Override
    protected void myInit() {
        cx = cy = cz = vx = vy = vz = 0f;
        xRange = yRange = zRange = 10f;
        zMin = -1;
        zMax = 10;
        scrollSpeed = DEFAULT_SCROLL_SPEED;
        zEquilateral = false;
    }

    protected void renderSphere(float x, float y, float z, float r, float step, float min) {
        float[] floatsBuffer = new float[3];
        float sumBuffer = r * r;
        glBegin(GL_POINTS);
        //glBegin(GL_TRIANGLE_FAN);
        for (floatsBuffer[0] = x - r; floatsBuffer[0] <= x + r; floatsBuffer[0] += step)
            for (floatsBuffer[1] = y - r; floatsBuffer[1] <= y + r; floatsBuffer[1] += step)
                for (floatsBuffer[2] = z - r; floatsBuffer[2] <= z + r; floatsBuffer[2] += step)
                    if (Math.abs(floatsBuffer[0] * floatsBuffer[0]
                                    + floatsBuffer[1] * floatsBuffer[1]
                                    + floatsBuffer[2] * floatsBuffer[2] - sumBuffer
                    ) <= min)
                        glVertex3f(floatsBuffer[0], floatsBuffer[1], floatsBuffer[2]);
        glEnd();
    }


    protected void renderSphere1(float x, float y, float z, float r, float step, float min) {
        float[] floatsBuffer = new float[3];
        float sumBuffer = r * r;
        float s = 0.1f;
        glBegin(GL_TRIANGLES);
        while (step > 0) {
            step--;
            floatsBuffer[0] = x + r * Utils.random.nextFloat(-1, 1);
            floatsBuffer[1] = y + r * Utils.random.nextFloat(-1, 1);
            floatsBuffer[2] = z + r * Utils.random.nextFloat(-1, 1);
            if (Math.abs(floatsBuffer[0] * floatsBuffer[0]
                            + floatsBuffer[1] * floatsBuffer[1]
                            + floatsBuffer[2] * floatsBuffer[2] - sumBuffer
            ) <= min) {
                glVertex3f(floatsBuffer[0] + s, floatsBuffer[1], floatsBuffer[2]);
                glVertex3f(floatsBuffer[0], floatsBuffer[1] + s, floatsBuffer[2]);
                glVertex3f(floatsBuffer[0], floatsBuffer[1], floatsBuffer[2] + s);
            }
        }
        glEnd();
    }

    protected float getZRangeMin() {
        if (zEquilateral)
            return cz - zRange;
        else
            return cz - zMin;
    }

    protected float getZRangeMax() {
        if (zEquilateral)
            return cz + zRange;
        else
            return cz + zMax;
    }

    protected void keyInvoke(long window, int key, int scanCode, int action, int mode) {
        switch (key) {
            case GLFW_KEY_ESCAPE:
                key_escape();
                break;
            case GLFW_KEY_W:
                switch (action) {
                    case GLFW_PRESS:
                        vz = scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vz = 0;
                        break;
                }
                break;
            case GLFW_KEY_S:
                switch (action) {
                    case GLFW_PRESS:
                        vz = -scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vz = 0;
                        break;
                }
                break;
            case GLFW_KEY_Q:
                switch (action) {
                    case GLFW_PRESS:
                        vy = scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vy = 0;
                        break;
                }
                break;
            case GLFW_KEY_Z:
                switch (action) {
                    case GLFW_PRESS:
                        vy = -scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vy = 0;
                        break;
                }
                break;
            case GLFW_KEY_A:
                switch (action) {
                    case GLFW_PRESS:
                        vx = -scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vx = 0;
                        break;
                }
                break;
            case GLFW_KEY_D:
                switch (action) {
                    case GLFW_PRESS:
                        vx = scrollSpeed;
                        break;
                    case GLFW_RELEASE:
                        vx = 0;
                        break;
                }
                break;
            default:
                myKeyInvoke(window, key, scanCode, action, mode);
                break;
        }
    }

    protected void key_escape() {
        GLFW.glfwSetWindowShouldClose(window, GL_TRUE);
    }

    protected abstract void myKeyInvoke(long window, int key, int scanCode, int action, int mode);

    @Override
    protected void myTick() {
        cx += vx;
        cy += vy;
        cz += vz;
    }

    @Override
    protected void reshape() {
        glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        if (WINDOW_WIDTH > WINDOW_HEIGHT)
            glOrtho(
                    (cx - xRange) * WINDOW_WIDTH / WINDOW_HEIGHT,
                    (cx + xRange) * WINDOW_WIDTH / WINDOW_HEIGHT,
                    cy - yRange, cy + yRange,
                    getZRangeMin(), getZRangeMax()
            );
        else
            glOrtho(
                    cx - xRange, cx + xRange,
                    (cy - yRange) * WINDOW_HEIGHT / WINDOW_WIDTH,
                    (cy + yRange) * WINDOW_HEIGHT / WINDOW_WIDTH,
                    getZRangeMin(), getZRangeMax()
            );
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

}