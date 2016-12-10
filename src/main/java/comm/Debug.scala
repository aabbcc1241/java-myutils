package comm

import javafx.application.Platform

import comm.gui.AlertUtils

/**
  * Created by beenotung on 10/23/15.
  */
object Debug {
  lazy val skipDebug = false
  lazy val skipError = false
  lazy val FILE_LOG = "log.txt"
  lazy val skipProgress = false
  lazy val process_step = 5f / 100f

  def exception(e: Exception) = {
    e.printStackTrace()
    loge(e)
  }

  def loge(x: Any, detail: String = null) = {
    log("Error: " + x, mainStatus = true)
    if (detail != null) log(detail)
    try {
      if (detail == null)
        AlertUtils.error_(x.toString)
      else
        AlertUtils.error(headerText = x.toString, contentText = detail)
    } catch {
      case e: Exception =>
        log("GUI is not supported, error message not prompted")
    }
  }

  def loge(msg: String, e: Exception) = {
    log_(msg)
    log_(e)
    try {
      e.printStackTrace()
      AlertUtils.error(contentText = msg, exception = e)
    } catch {
      case e: Exception =>
        log("GUI is not supported, error message not prompted")
    }
  }

  def loge_(x: Any) = loge(x)

  def logBothStatus(main: String, minor: String) = {
    log_(main)
    log_(minor)
    setMainStatus(main)
    setMinorStatus(minor)
  }

  var setMainStatus = (s: String) => {}
  var setMinorStatus = (s: String) => {}
  var getMainStatus = () => ""
  var getMinorStatus = () => ""


  def logDone(x: Any = {
    try {
      getMainStatus
    } catch {
      case e: IllegalStateException => ""
    }
  }) = {
    log_("Done " + x)
    setMainStatus(x.toString)
    setMinorStatus("done")
  }

  def log_(x: Any) = log(x, mainStatus = false, minorStatus = false)

  def log(x: Any, mainStatus: Boolean = false, minorStatus: Boolean = false) = {
    println(x)
    Console.out.flush()
    FileUtils.appendToFile(x.toString :: Nil, FILE_LOG)
    if (mainStatus) setMainStatus(x.toString)
    if (minorStatus) setMinorStatus(x.toString)
  }

  def logMainStatus(x: Any) = {
    log_(x)
    setMainStatus(x.toString)
    setMinorStatus("")
  }

  def logp(x: Any) = {
    setMinorStatus(x.toString)
    if (!skipProgress) {
      println(x)
      Console.out.flush()
    }
  }

  def logd(x: Any) = if (!skipDebug) log(x)
}
