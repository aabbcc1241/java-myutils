package gui;

import myutils.gui.CanvasJFrame;

import java.awt.*;

/**
 * Created by beenotung on 12/5/16.
 */
public class GUITest {
    public static void main(String[] args) {
        double rate = 0.8;
        MainFrame mainFrame = new MainFrame(rate, rate, 1, "Image demo");
        mainFrame.start();
    }

    public static class MainFrame extends CanvasJFrame {
        public MainFrame(double widthRate, double heightRate, int scale, String APP_NAME) {
            super(widthRate, heightRate, scale, APP_NAME);
        }

        @Override
        protected void init() {

        }

        @Override
        protected void myTick() {

        }

        @Override
        protected void myRender() {

        }

        @Override
        protected void myDebugInfo() {

        }

        @Override
        protected void myKeyHandling() {

        }

        @Override
        protected void myMouseHandling() {

        }
    }
}
