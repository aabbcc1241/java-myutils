package myutils.gui.cardlayout;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;

public abstract class AbstractCardJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1690280875210999260L;
	public CardLayout cardLayout;

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
