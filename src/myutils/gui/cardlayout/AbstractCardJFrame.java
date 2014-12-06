package myutils.gui.cardlayout;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractCardJFrame extends JFrame {
    private final CardLayout cardLayout;

    public AbstractCardJFrame(String title) {
        setVisible(false);
        setTitle(title);
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        myInit();
        setVisible(true);
    }

    protected abstract void myInit();

    public void addToCards(Component component, String label) {
        add(component);
        cardLayout.addLayoutComponent(component, label);
    }

    public void switchToCard(String label) {
        cardLayout.show(getContentPane(), label);
    }
}
