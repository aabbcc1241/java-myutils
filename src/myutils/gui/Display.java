package myutils.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
class Display extends OutputStream {
    private final JFrame frame;
    @SuppressWarnings("FieldCanBeLocal")
    private final Container container;
    private final JTextArea textArea;
    private StringBuilder bufferString;
    private long interval;
    private long lastUpdate;

    /**
     * constructor *
     */
    @SuppressWarnings("SameParameterValue")
    public Display(JTextArea textArea, double interval) {
        this.textArea = textArea;
        this.interval = Math.round(interval / 1000);
        frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                hide();
            }
        });
        frame.setPreferredSize(new Dimension(800, 600));
        container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        textArea = new JTextArea(25, 80);
        textArea.setEditable(false);
        container.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        frame.pack();
        // frame.setSize(textArea.getWidth(), textArea.getHeight());
        // frame.setVisible(true);
        bufferString = new StringBuilder();
        clear();
        update();
    }

    public Display(JTextArea textArea) {
        this(textArea, 0);
    }

    /**
     * implementing *
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        if (buffer == null) return;
        final String text = new String(buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.append(text);
            }
        });
    }

    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }

    public void write(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.append(text);
            }
        });
    }

    /**
     * instance method *
     */
    public void show() {
        frame.setVisible(true);
    }

    void hide() {
        frame.setVisible(false);
    }

    public boolean isShown() {
        return frame.isVisible();
    }

    void clear() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.setText("");
            }
        });
    }

    public void setFPS(double fps) {
        interval = Math.round(1000 / fps);
    }

    ;

    public void clearBuffer() {
        bufferString = new StringBuilder();
    }

    public void setBuffer(String str) {
        bufferString = new StringBuilder(str);
    }

    public void writeBuffer(String str) {
        if (bufferString == null)
            bufferString = new StringBuilder();
        bufferString.append(str);
    }

    void update() {
        textArea.update(textArea.getGraphics());
        lastUpdate = System.currentTimeMillis();
    }

    void updateBuffer() {
        textArea.setText(bufferString.toString());
        lastUpdate = System.currentTimeMillis();
    }

    public void checkUpdate() {
        if (System.currentTimeMillis() - lastUpdate >= interval)
            update();
    }

    public void checkUpdateBuffer() {
        if (System.currentTimeMillis() - lastUpdate >= interval)
            updateBuffer();
    }

    public void setSize(int width, int height) {
        // frame.setPreferredSize(new Dimension(width,height));
        // frame.pack();
        frame.setSize(width, height);
    }

    public void end() {
        hide();
        frame.dispose();
    }
}
