package myutils.gui.cardlayout;
import javax.swing.*;
import java.awt.*;
@SuppressWarnings("UnusedDeclaration")
public abstract class AbstractCardJPanel extends JPanel {
    private final CardLayout cardLayout;
    public AbstractCardJPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        myInit();
    }
    protected abstract void myInit();
    public void addToCards(Component component, String label) {
        add(component);
        cardLayout.addLayoutComponent(component, label);
    }
    public void switchToCard(String label) {
        cardLayout.show(this, label);
    }
}
