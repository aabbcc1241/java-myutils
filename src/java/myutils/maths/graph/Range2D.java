package myutils.maths.graph;

/**
 * Created by beenotung on 12/19/14.
 */
public class Range2D<E> {
  public E xMin, yMin, xMax, yMax;

  public Range2D(E xMin, E yMin, E xMax, E yMax) {
    this.xMin = xMin;
    this.yMin = yMin;
    this.xMax = xMax;
    this.yMax = yMax;
  }
}
