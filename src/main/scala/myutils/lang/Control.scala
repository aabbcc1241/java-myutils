package myutils.lang

/**
  * Created by beenotung on 12/10/16.
  */
class Control {

  /* source : http://stackoverflow.com/questions/2842540/is-there-a-brief-syntax-for-executing-a-block-n-times-in-scala/2842640#2842640 */
  implicit def intTimes(n: Int): Unit = new {
    def times(f: => Unit) = 1 to n foreach { _ => f }
  }
}
