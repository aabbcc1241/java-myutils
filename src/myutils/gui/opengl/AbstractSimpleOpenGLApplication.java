package myutils.gui.opengl;

import org.lwjgl.glfw.GLFW;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by beenotung on 1/2/15.
 */

public abstract class AbstractSimpleOpenGLApplication extends AbstractOpenGLApplication {
    protected final float DEFAULT_SCROLL_SPEED = 0.2f;
    protected final float DEFAULT_ROLL_SPEED = 0.5f;
    protected float scrollSpeed, rollSpeed;
    protected float xRange, yRange, zRange;
    protected float cx, cy, cz;
    protected float cxr, cyr, czr;
    protected float vx, vy, vz;
    protected float rvx, rvy, rvz;
    protected float zMin, zMax;
    protected boolean zEquilateral;
    protected boolean isCameraOrtho;


    @Override
    protected void myInit() {
        cx = cy = cz = vx = vy = vz = 0f;
        xRange = yRange = zRange = 10f;
        zMin = -1;
        zMax = 10;
        scrollSpeed = DEFAULT_SCROLL_SPEED;
        rollSpeed = DEFAULT_ROLL_SPEED;
        zEquilateral = false;
        isCameraOrtho = true;
    }

    protected void renderSphere(float x, float y, float z, float r, float step) {
        float alpha, beta, nx, ny, nz;
        double a, b;
        //glBegin(GL_POINTS);
        glBegin(GL_POINTS);
        for (alpha = 0; alpha < 360; alpha += step) {
            a = (alpha / 180 * Math.PI);
            for (beta = -90; beta < 90; beta += step) {
                b = (beta / 180 * Math.PI);
                nx = (float) (x + cos(a) * cos(b) * r);
                ny = (float) (x + cos(a) * sin(b) * r);
                nz = (float) (x + sin(a) * r);
                //glColor3f(nx,ny,nz);
                glVertex3f(nx, ny, nz);
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
            case GLFW_KEY_RIGHT:
                switch (action) {
                    case GLFW_PRESS:
                        rvy = rollSpeed;
                        break;
                    case GLFW_RELEASE:
                        rvy = 0;
                        break;
                }
                break;
            case GLFW_KEY_LEFT:
                switch (action) {
                    case GLFW_PRESS:
                        rvy = -rollSpeed;
                        break;
                    case GLFW_RELEASE:
                        rvy = 0;
                        break;
                }
                break;
            case GLFW_KEY_DOWN:
                switch (action) {
                    case GLFW_PRESS:
                        rvx = rollSpeed;
                        break;
                    case GLFW_RELEASE:
                        rvx = 0;
                        break;
                }
                break;
            case GLFW_KEY_UP:
                switch (action) {
                    case GLFW_PRESS:
                        rvx = -rollSpeed;
                        break;
                    case GLFW_RELEASE:
                        rvx = 0;
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
        cxr += rvx;
        cyr += rvy;
        czr += rvz;
    }

    @Override
    protected void reshape() {
        //TODO update width and height
        glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        if (isCameraOrtho)
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
        else if (WINDOW_WIDTH > WINDOW_HEIGHT)
            glFrustum(
                    (cx - xRange) * WINDOW_WIDTH / WINDOW_HEIGHT,
                    (cx + xRange) * WINDOW_WIDTH / WINDOW_HEIGHT,
                    cy - yRange, cy + yRange,
                    getZRangeMin(), getZRangeMax()
            );
        else
            glFrustum(
                    cx - xRange, cx + xRange,
                    (cy - yRange) * WINDOW_HEIGHT / WINDOW_WIDTH,
                    (cy + yRange) * WINDOW_HEIGHT / WINDOW_WIDTH,
                    getZRangeMin(), getZRangeMax()
            );
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        //glTranslatef(cx,cy,cz);
        glRotatef(cxr, 1, 0, 0);
        glRotatef(cyr, 0, 1, 0);
        glRotatef(czr, 0, 0, 1);
    }

}