package myutils.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

@SuppressWarnings("CanBeFinal")
public abstract class CanvasShell extends Canvas implements Runnable {
    private final double DEFAULTnsPerTick;
    private final double DEFAULTnsPerRender;
    public int WIDTH, HEIGHT, SCALE;
    public float cx, cy;
    public Pixels screen;
    protected int background = Colors.get(0, 0, 0);
    protected int x, y, xPos, yPos;
    private boolean running = false;
    private long tickCount = 0;
    private int ticks = 0;
    private int renders = 0;
    private String TITLE;
    private double nsPerTick;
    private double nsPerRender;
    private double deltaTick = 0;
    private double deltaRender = 0;
    @SuppressWarnings("FieldCanBeLocal")
    private JFrame frame;
    private Graphics graphics;
    private BufferStrategy bufferStrategy;
    private BufferedImage image;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public CanvasShell(int width, int height, int scale, String title, double nsPerTick, double nsPerRender) {
        WIDTH = width / scale;
        HEIGHT = height / scale;
        cx = WIDTH / 2f;
        cy = HEIGHT / 2f;
        SCALE = scale;
        TITLE = title;
        this.DEFAULTnsPerTick = nsPerTick;
        this.DEFAULTnsPerRender = nsPerRender;
        this.nsPerTick = DEFAULTnsPerTick;
        this.nsPerRender = DEFAULTnsPerRender;

        setMinimumSize(new Dimension(WIDTH * SCALE / 2, HEIGHT * SCALE / 2));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE * 2, HEIGHT * SCALE * 2));

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        screen = new Pixels(((DataBufferInt) image.getRaster().getDataBuffer()).getData(), this);

        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

        keyHandler = new KeyHandler(this);
        mouseHandler = new MouseHandler(this);
    }

    @Override
    public void run() {
        init();
        long last = System.nanoTime();
        long current;
        long debugTimer = System.currentTimeMillis();
        boolean shouldRender = false;
        while (running) {
            current = System.nanoTime();
            deltaTick += (current - last) / nsPerTick;
            deltaRender += (current - last) / nsPerRender;
            last = current;
            if (deltaTick > 1) {
                tick();
                ticks++;
                deltaTick -= 1;
                shouldRender = true;
            }
            if ((deltaRender > 1) && (shouldRender)) {
                render();
                renders++;
                deltaRender -= 1;
                shouldRender = false;
            }
            if (System.currentTimeMillis() - debugTimer >= 1000) {
                debugInfo();
                debugTimer += 1000;
            }
        }
        System.exit(0);
    }

    protected abstract void init();

    void tick() {
        tickCount++;
        defaultKeyHandling();
        defaultMouseHandling();
        myTick();
    }

    void render() {
        myRender();

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        bufferStrategy.show();
    }

    void debugInfo() {
        System.out.println(ticks + " TPS, " + renders + "FPS");
        myDebugInfo();
        ticks = renders = 0;
    }

    protected abstract void myTick();

    protected abstract void myRender();

    protected abstract void myDebugInfo();

    private void defaultKeyHandling() {
        if (keyHandler.esc.pressed) {
            stop();
        }
        if (keyHandler.up.pressed) {
            screen.scrollY(-1);
        }
        if (keyHandler.down.pressed) {
            screen.scrollY(1);
        }
        if (keyHandler.left.pressed) {
            screen.scrollX(-1);
        }
        if (keyHandler.right.pressed) {
            screen.scrollX(1);
        }
        if (keyHandler.pageup.pressed) {
            screen.zoom(1);
        }
        if (keyHandler.pagedown.pressed) {
            screen.zoom(-1);
        }
        if (keyHandler.equal.pressed) {
            screen.resetOffsetScale();
            resetNsPerTickRender();
        }
        myKeyHandling();
    }

    void resetNsPerTickRender() {
        nsPerTick = DEFAULTnsPerTick;
        nsPerRender = DEFAULTnsPerRender;
    }

    private void defaultMouseHandling() {
        if (mouseHandler.right.clicked) {
            screen.setOffset(mouseHandler.right.locationRelativeScaled);
            mouseHandler.right.clicked = false;
        }
        if (mouseHandler.amountScrolled != 0) {
            screen.zoom(mouseHandler.amountScrolled);
            mouseHandler.amountScrolled = 0;
        }
        myMouseHandling();
    }

    protected abstract void myKeyHandling();

    protected abstract void myMouseHandling();

    public synchronized void start() {
        System.out.println("CanvasShell start");
        running = true;
        new Thread(this, TITLE + "-Thread").start();
    }

    void stop() {
        System.out.println("CanvasShell stop");
        running = false;
    }

}
