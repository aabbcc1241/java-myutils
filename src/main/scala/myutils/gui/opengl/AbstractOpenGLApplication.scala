package myutils.gui.opengl

import java.nio.ByteBuffer
import java.util.concurrent.Semaphore

import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.{GLFWErrorCallback, GLFWKeyCallback, _}
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GLContext
import org.lwjgl.system.MemoryUtil

/**
  * Created by beenotung on 1/2/15.
  */

/**
  *
  * @param window_width
  * @param window_height
  * @param window_title
  * @param nsPerTick
  * @param nsPerRender
  * @param backgroundColors
  * Array[Float]=r,g,b,a
  * range 0..1
  */
abstract class AbstractOpenGLApplication(protected var window_width: Int,
                                         protected var window_height: Int,
                                         protected var window_title: String,
                                         protected var nsPerTick: Float,
                                         protected var nsPerRender: Float,
                                         protected var debug_interval: Long,
                                         protected var backgroundColors: Array[Float])
  extends Runnable {
  protected var glfwKeyCallback: GLFWKeyCallback = getGLFWKeyCallback
  protected var glfwErrorCallback: GLFWErrorCallback = Callbacks.errorCallbackPrint(System.err)
  protected var WINDOW_CX: Int = 0
  protected var WINDOW_CY: Int = 0
  protected var window: Long = 0L
  protected var ticking: Boolean = false
  protected var rendering: Boolean = false
  private var deltaTick: Float = 0
  private var deltaRender: Float = 0

  def init {
    glfwSetErrorCallback(glfwErrorCallback)
    if (glfwInit != GL_TRUE) throw new IllegalStateException("Failed to int GLFW")
    glfwWindowHint(GLFW_VISIBLE, GL_FALSE)
    windowConfigure
    window = glfwCreateWindow(window_width, window_height, window_title, MemoryUtil.NULL, MemoryUtil.NULL)
    if (window == MemoryUtil.NULL) throw new RuntimeException("Failed to create GLFW window")
    glfwSetKeyCallback(window, glfwKeyCallback)
    val videoMode: ByteBuffer = glfwGetVideoMode(glfwGetPrimaryMonitor)
    WINDOW_CX = (GLFWvidmode.width(videoMode) - window_width) / 2
    WINDOW_CY = (GLFWvidmode.height(videoMode) - window_height) / 2
    glfwSetWindowPos(window, WINDOW_CX, WINDOW_CY)
    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)
    glfwShowWindow(window)
    myInit
  }

  protected def windowConfigure {
    glfwDefaultWindowHints
    glfwWindowHint(GLFW_RESIZABLE, GL_FALSE)
  }

  protected def myInit

  protected def getGLFWKeyCallback: GLFWKeyCallback = {
    new GLFWKeyCallback {
      override def invoke(window: Long, key: Int, scanCode: Int, action: Int, mode: Int): Unit = {
        keyInvoke(window, key, scanCode, action, mode)
      }
    }
  }

  protected def getDefaultGLFWKeyCallback: GLFWKeyCallback = {
    new GLFWKeyCallback {
      override def invoke(window: Long, key: Int, scanCode: Int, action: Int, mode: Int): Unit = {
        key match {
          case GLFW_KEY_ESCAPE =>
            if (action == GLFW_RELEASE) glfwSetWindowShouldClose(window, GL_TRUE)
        }
      }
    }
  }

  protected def keyInvoke(window: Long, key: Int, scanCode: Int, action: Int, mode: Int)

  def loop = {
    GLContext.createFromCurrent
    glClearColor(backgroundColors(0), backgroundColors(1), backgroundColors(2), backgroundColors(3))
    val lastTime: Long = System.nanoTime
    var debugTime: Long = System.currentTimeMillis
    var currentTime: Long = 0L
    deltaTick = 0
    deltaRender = 0
    while (glfwWindowShouldClose(window) == GL_FALSE) {
      currentTime = System.nanoTime
      deltaTick += (currentTime - lastTime) / nsPerTick
      deltaRender += (currentTime - lastTime) / nsPerRender
      if (deltaTick > 0) {
        deltaTick = 0
        tick
      }
      if (deltaRender > 0) {
        deltaRender = 0
        render
      }
      if (System.currentTimeMillis - debugTime >= debug_interval) {
        debugTime += debug_interval
        debugInfo
      }
    }
  }

  protected def debugInfo

  protected def tick {
    semaphore.acquire
    glfwPollEvents
    myTick
    semaphore.release
  }

  protected def myTick

  val semaphore = new Semaphore(1)

  protected def render {
    semaphore.acquire
    reshape
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    myRender
    glfwSwapBuffers(window)
    semaphore.release
  }

  protected def reshape

  protected def myRender

  def end {
    glfwTerminate
    glfwErrorCallback.release
  }

  def run {
    try {
      init
      loop
    } finally {
      end
    }
  }
}