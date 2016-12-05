package gui;

import myutils.gui.CanvasJFrame;
import myutils.gui.Colors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by beenotung on 12/5/16.
 */
public class CanvasTest {

  BufferedImage photo;

  public static void main(String[] args) throws IOException {
    new CanvasTest();
  }

  CanvasTest() throws IOException {
    photo = ImageIO.read(new File("res/Mona_Lisa.jpg"));
    MainFrame mainFrame = new MainFrame(photo.getWidth(), photo.getHeight());
    mainFrame.start();
  }

  public class MainFrame extends CanvasJFrame {
    public MainFrame(int w, int h) {
      this(w * 2, h, 1, "Demo App", DEFAULT_NS_PER_TICK, DEFAULT_NS_PER_RENDER);
    }

    public MainFrame(int width, int height, int scale, String title, double nsPerTick, double nsPerRender) {
      super(width, height, scale, title, nsPerTick, nsPerRender);
    }

    public MainFrame(double widthRate, double heightRate, int scale, String APP_NAME) {
      super(widthRate, heightRate, scale, APP_NAME);
    }

    @Override
    protected void init() {
      cx = 0;
      cy = 0;
      int w = photo.getWidth();
      int h = photo.getHeight();
      System.out.println("w=" + w + ";h=" + h);
      for (int x = 0; x < WIDTH / 2; x++) {
        for (int y = 0; y < HEIGHT; y++) {
          screen.add(x, y,
            photo.getRGB(
              w * x / WIDTH * 2
              , h * y / HEIGHT
            )
          );
        }
      }
      for (int x = WIDTH / 2; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
          Colors.RGB rgb = new Colors.RGB(0);
          int size = 10;
          for (int step = 0; step < size; step++) {
            rgb.add(Colors.getRGB(
              screen.cycle_get(x - WIDTH / 2 + step, y + step)
            ));
            rgb.add(Colors.getRGB(
              screen.cycle_get(x - WIDTH / 2 + step, y - step)
            ));
            rgb.add(Colors.getRGB(
              screen.cycle_get(x - WIDTH / 2 - step, y + step)
            ));
            rgb.add(Colors.getRGB(
              screen.cycle_get(x - WIDTH / 2 - step, y - step)
            ));
          }
          rgb.r /= 4 * size;
          rgb.g /= 4 * size;
          rgb.b /= 4 * size;

          screen.add(x, y, Colors.getRawCode(rgb));
        }
      }
    }

    @Override
    protected void myTick() {

    }

    @Override
    protected void myRender() {
//            screen.clear(Color.black);
//            for (int x = 0; x < WIDTH / 2; x++) {
//                for (int y = 0; y < HEIGHT; y++) {
//                    screen.add(x, y, Color.red.getRGB());
//                }
//            }
//            for (int x = WIDTH / 2; x < WIDTH; x++) {
//                for (int y = 0; y < HEIGHT; y++) {
//                    screen.add(x, y, Color.green.getRGB());
//                }
//            }
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
