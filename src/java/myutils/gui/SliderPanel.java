package myutils.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class SliderPanel extends JPanel {
	public NumberJTextField textField;
	public JSlider slider;

	/**
	 * Create the panel.
	 */
	public SliderPanel(String title) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel(title);
		add(lblTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField.setText(String.valueOf(slider.getValue()));
			}
		});
		panel.add(slider);

		textField = new NumberJTextField(0,100,false);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				updateText();
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				updateSlider();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				updateSlider();
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				updateSlider();
			}
		});
		panel.add(textField);
		textField.setColumns(10);

		updateText();
	}

	void updateText() {
		textField.setText(String.valueOf(slider.getValue()));
	}

	void updateSlider() {
		if(textField.getText().length()>0)
		try {
			int value = Integer.parseInt(textField.getText());
			if (value >= 0 && value <= 100)
				slider.setValue(value);
			else
				updateText();
		} catch (NumberFormatException e) {
			updateText();
		}
	}
}
