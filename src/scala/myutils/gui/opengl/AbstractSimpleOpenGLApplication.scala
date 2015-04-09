package myutils.gui.opengl

import java.lang.Math._

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW._
import org.lwjgl.opengl.GL11._

/**
 * Created by beenotung on 4/8/15.
 */


abstract class AbstractSimpleOpenGLApplication(
                                                window_width: Int = 800,
                                                width_height: Int = 600,
                                                window_title: String = "My OpenGL Application",
                                                nsPerTick: Float = 1e9f / 60f,
                                                nsPerRender: Float = 1e9f / 30f,
                                                debug_interval: Long = 1000L,
                                                backgroundColors: Array[Float] = Array.fill[Float](4)(0f))
  extends AbstractOpenGLApplication(window_width, width_height, window_title,
    nsPerTick, nsPerRender, debug_interval,
    backgroundColors) {
  protected val DEFAULT_SCROLL_SPEED: Float = 0.2f
  protected val DEFAULT_ROLL_SPEED: Float = 0.5f
  protected var scrollSpeed: Float = .0f

  def renderSpherePoint(x: Float, y: Float, z: Float, r: Float, step: Float) {
    var alpha: Float = .0f
    var beta: Float = .0f
    var nx: Float = .0f
    var ny: Float = .0f
    var nz: Float = .0f
    var a: Double = .0d
    var b: Double = .0d

    glBegin(GL_POINTS)
    while (alpha < 360f) {
      a = alpha / 180 * Math.PI
      beta = -90f
      while (beta <= 90f) {
        b = beta / 180 * Math.PI
        nx = (x + cos(a) * cos(b) * r).toFloat
        ny = (y + cos(a) * sin(b) * r).toFloat
        nz = (z + sin(a) * r).toFloat
        glVertex3f(nx, ny, nz)
        glVertex3f(nz, nx, ny)
        glVertex3f(ny, nz, nx)
        beta += step
      }
      alpha += step
    }
    glEnd
  }

  def renderSphereLine(x: Float, y: Float, z: Float, r: Float, step: Float) {
    var alpha: Float = .0f
    var beta: Float = .0f
    var nx: Float = .0f
    var ny: Float = .0f
    var nz: Float = .0f
    var a: Double = .0d
    var b: Double = .0d

    alpha = 0f
    while (alpha < 360f) {
      glBegin(GL_LINE_STRIP)
      a = alpha / 180 * Math.PI
      beta = -90f
      while (beta <= 90f) {
        b = beta / 180 * Math.PI
        nx = (x + cos(a) * cos(b) * r).toFloat
        ny = (y + cos(a) * sin(b) * r).toFloat
        nz = (z + sin(a) * r).toFloat
        glVertex3f(nx, ny, nz)
        beta += step
      }
      alpha += step
      glEnd
    }

    alpha = 0f
    while (alpha < 360f) {
      glBegin(GL_LINE_STRIP)
      a = alpha / 180 * Math.PI
      beta = -90f
      while (beta <= 90f) {
        b = beta / 180 * Math.PI
        nx = (x + cos(a) * cos(b) * r).toFloat
        ny = (y + cos(a) * sin(b) * r).toFloat
        nz = (z + sin(a) * r).toFloat
        glVertex3f(nz, nx, ny)
        beta += step
      }
      alpha += step
      glEnd
    }

    alpha = 0f
    while (alpha < 360f) {
      glBegin(GL_LINE_STRIP)
      a = alpha / 180 * Math.PI
      beta = -90f
      while (beta <= 90f) {
        b = beta / 180 * Math.PI
        nx = (x + cos(a) * cos(b) * r).toFloat
        ny = (y + cos(a) * sin(b) * r).toFloat
        nz = (z + sin(a) * r).toFloat
        glVertex3f(ny, nz, nx)
        beta += step
      }
      alpha += step
      glEnd
    }
  }

  protected var rollSpeed: Float = .0f
  protected var xRange: Float = .0f

  protected var yRange: Float = .0f

  protected var zRange: Float = .0f
  protected var cx: Float = .0f

  protected var cy: Float = .0f

  protected var cz: Float = .0f
  protected var cxr: Float = .0f

  protected var cyr: Float = .0f

  protected var czr: Float = .0f
  protected var vx: Float = .0f

  protected var vy: Float = .0f

  protected var vz: Float = .0f
  protected var rvx: Float = .0f

  protected var rvy: Float = .0f

  protected var rvz: Float = .0f
  protected var zMin: Float = .0f

  protected var zMax: Float = .0f
  protected var zEquilateral: Boolean = false
  protected var isCameraOrtho: Boolean = false


  protected def myInit {
    cx = 0f
    cy = 0f
    cz = 0f
    vx = 0f
    vy = 0f
    vz = 0f
    xRange = 10f
    yRange = 10f
    zRange = 10f
    zMin = -1
    zMax = 10
    scrollSpeed = DEFAULT_SCROLL_SPEED
    rollSpeed = DEFAULT_ROLL_SPEED
    zEquilateral = false
    isCameraOrtho = true
  }

  protected def getZRangeMin: Float = {
    if (zEquilateral) cz - zRange
    else cz - zMin
  }

  protected def getZRangeMax: Float = {
    if (zEquilateral) cz + zRange
    else cz + zMax
  }

  protected def keyInvoke(window: Long, key: Int, scanCode: Int, action: Int, mode: Int) {
    key match {
      case GLFW_KEY_ESCAPE =>
        key_escape
      case GLFW_KEY_W =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vz = scrollSpeed
          case GLFW_RELEASE =>
            vz = 0
        }
      case GLFW_KEY_S =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vz = -scrollSpeed
          case GLFW_RELEASE =>
            vz = 0
        }
      case GLFW_KEY_Q =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vy = scrollSpeed
          case GLFW_RELEASE =>
            vy = 0
        }
      case GLFW_KEY_Z =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vy = -scrollSpeed
          case GLFW_RELEASE =>
            vy = 0
        }
      case GLFW_KEY_A =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vx = -scrollSpeed
          case GLFW_RELEASE =>
            vx = 0
        }
      case GLFW_KEY_D =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            vx = scrollSpeed
          case GLFW_RELEASE =>
            vx = 0
        }
      case GLFW_KEY_RIGHT =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            rvy = rollSpeed
          case GLFW_RELEASE =>
            rvy = 0
        }
      case GLFW_KEY_LEFT =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            rvy = -rollSpeed
          case GLFW_RELEASE =>
            rvy = 0
        }
      case GLFW_KEY_DOWN =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            rvx = rollSpeed
          case GLFW_RELEASE =>
            rvx = 0
        }
      case GLFW_KEY_UP =>
        action match {
          case GLFW_PRESS | GLFW_REPEAT =>
            rvx = -rollSpeed
          case GLFW_RELEASE =>
            rvx = 0
        }
      case _ =>
        myKeyInvoke(window, key, scanCode, action, mode)
    }
  }

  protected def key_escape {
    GLFW.glfwSetWindowShouldClose(window, GL_TRUE)
  }

  protected def myKeyInvoke(window: Long, key: Int, scanCode: Int, action: Int, mode: Int)

  protected def myTick {
    cx += vx
    cy += vy
    cz += vz
    cxr += rvx
    cyr += rvy
    czr += rvz
  }

  protected def reshape {
    glViewport(0, 0, window_width, width_height)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity
    if (isCameraOrtho) if (window_width > width_height) glOrtho((cx - xRange) * window_width / width_height, (cx + xRange) * window_width / width_height, cy - yRange, cy + yRange, getZRangeMin, getZRangeMax)
    else glOrtho(cx - xRange, cx + xRange, (cy - yRange) * width_height / window_width, (cy + yRange) * width_height / window_width, getZRangeMin, getZRangeMax)
    else if (window_width > width_height) glFrustum((cx - xRange) * window_width / width_height, (cx + xRange) * window_width / width_height, cy - yRange, cy + yRange, getZRangeMin, getZRangeMax)
    else glFrustum(cx - xRange, cx + xRange, (cy - yRange) * width_height / window_width, (cy + yRange) * width_height / window_width, getZRangeMin, getZRangeMax)
    glMatrixMode(GL_MODELVIEW)
    glLoadIdentity
    glRotatef(cxr, 1, 0, 0)
    glRotatef(cyr, 0, 1, 0)
    glRotatef(czr, 0, 0, 1)
  }
}
