package myutils;

import java.util.Random;

/**
 * Created by beenotung on 12/19/14.
 */
public class Randoms extends Random {
    public Randoms() {
        super(System.currentTimeMillis());
    }

    public int nextInt(int min, int max) {
        return super.nextInt(max - min + 1) + min;
    }

    public float nextFloat(float min, float max) {
        return super.nextFloat() * (max - min) + min;
    }

    public double nextDouble(double min, double max) {
        return super.nextDouble() * (max - min) + min;
    }
}
