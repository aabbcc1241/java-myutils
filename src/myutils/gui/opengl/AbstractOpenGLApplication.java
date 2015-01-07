package myutils.gui.opengl; /** * Created by beenotung on 1/2/15. */

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractOpenGLApplication implements Runnable {
    protected final String WINDOW_TITLE = "My OpenGL Application";
    protected int WINDOW_WIDTH = 800;
    protected int WINDOW_HEIGHT = 600;
    protected GLFWKeyCallback glfwKeyCallback;
    protected GLFWErrorCallback glfwErrorCallback;
    protected int WINDOW_CX, WINDOW_CY;
    protected long window;
    protected float nsPerTick;
    protected float nsPerRender;
    protected boolean ticking, rendering;
    private float deltaTick = 0;
    private float deltaRender = 0;

    public AbstractOpenGLApplication() {
        nsPerRender = 1e9f / 60f;
        nsPerTick = 1e9f / 30f;
        ticking = false;
        rendering = false;
    }

    public void init() {
        glfwSetErrorCallback(glfwErrorCallback = Callbacks.errorCallbackPrint(System.err));
        if (glfwInit() != GL11.GL_TRUE)
            throw new IllegalStateException("Failed to int GLFW");

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_FALSE);

        if ((window = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE, MemoryUtil.NULL, MemoryUtil.NULL)) == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create GLFW window");

        GLFW.glfwSetKeyCallback(window, glfwKeyCallback = getGLFWKeyCallback());

        ByteBuffer videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        WINDOW_CX = (GLFWvidmode.width(videoMode) - WINDOW_WIDTH) / 2;
        WINDOW_CY = (GLFWvidmode.height(videoMode) - WINDOW_HEIGHT) / 2;
        GLFW.glfwSetWindowPos(window, WINDOW_CX, WINDOW_CY);

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);

        myInit();
    }

    protected abstract void myInit();

    protected GLFWKeyCallback getGLFWKeyCallback() {
        return new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scanCode, int action, int mode) {
                keyInvoke(window, key, scanCode, action, mode);
            }
        };
    }

    protected GLFWKeyCallback getDefaultGLFWKeyCallback() {
        return new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scanCode, int action, int mode) {
                switch (key) {
                    case GLFW_KEY_ESCAPE:
                        if (action == GLFW_RELEASE) glfwSetWindowShouldClose(window, GL_TRUE);
                }
            }
        };
    }

    protected abstract void keyInvoke(long window, int key, int scanCode, int action, int mode);

    public void loop() {
        GLContext.createFromCurrent();

        float r, g, b, a;
        r = g = b = a = 0.0f;
        GL11.glClearColor(r, g, b, a);

        long lastTime = System.nanoTime();
        long debugTime = System.currentTimeMillis();
        long currentTime;
        deltaTick = 0;
        deltaRender = 0;

        while (glfwWindowShouldClose(window) == GL11.GL_FALSE) {
            currentTime = System.nanoTime();
            deltaTick += (currentTime - lastTime) / nsPerTick;
            deltaRender += (currentTime - lastTime) / nsPerRender;
            if (deltaTick > 0) {
                deltaTick = 0;
                tick();
            }
            if (deltaRender > 0) {
                deltaRender = 0;
                render();
            }
            if (System.currentTimeMillis() - debugTime >= 1000) {
                debugTime += 1000;
                debugInfo();
            }
        }
    }

    protected abstract void debugInfo();

    protected void tick() {
        if (rendering) return;
        ticking = true;
        glfwPollEvents();
        myTick();
        ticking = false;
    }

    protected abstract void myTick();

    protected void render() {
        if (ticking) return;
        rendering = true;
        reshape();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        myRender();
        glfwSwapBuffers(window);
        rendering = false;
    }


    protected abstract void reshape();


    protected abstract void myRender();


    public void end() {
        glfwTerminate();
        glfwErrorCallback.release();
    }

    public void run() {
        try {
            init();
            loop();
        } finally {
            end();
        }
    }
}