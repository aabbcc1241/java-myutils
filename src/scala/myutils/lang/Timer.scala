package myutils.lang

/**
  * Created by beenotung on 4/9/15.
  */
object Timer {
  def setTimeInterval(callback: => Unit, shouldDo: => Boolean, time: Long) {
    Worker.forkAndStart({
      while (shouldDo) {
        callback
        sleep(time)
      }
    })
  }

  def setTimeDelay(callback: => Unit, time: Long) {
    Worker.forkAndStart({
      sleep(time)
      callback
    })
  }

  def sleep(time: Long) = {
    Thread sleep time
  }
}
