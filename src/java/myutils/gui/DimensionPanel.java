package myutils.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DimensionPanel extends JPanel {
	private NumberJTextField textField1;
	private NumberJTextField textField2;

	private final int MIN, MAX;
	public AtomicInteger value1 = new AtomicInteger();
	public AtomicInteger value2 = new AtomicInteger();

	/**
	 * Create the panel.
	 */
	public DimensionPanel(String title, int min, int max,
			boolean restoreOnLostFocus) {
		MIN = min;
		MAX = max;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setPreferredSize(new Dimension(300, 40));
		setSize(getPreferredSize());
		setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel(title);
		add(lblTitle, BorderLayout.NORTH);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField1 = new NumberJTextField(min, max, restoreOnLostFocus);
		panel.add(textField1);
		textField1.setColumns(10);

		JLabel lblX = new JLabel("X");
		panel.add(lblX);

		textField2 = new NumberJTextField(min, max, restoreOnLostFocus);
		panel.add(textField2);
		textField2.setColumns(10);
	}
}
