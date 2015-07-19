package myutils.gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberJTextField extends JTextField {
	public final int MIN, MAX;
	public AtomicInteger value = new AtomicInteger();

	public NumberJTextField(int min, int max, boolean restoreOnLostFocus) {
		MIN = min;
		MAX = max;

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				checkKeyCode(e.getKeyCode());
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (restoreOnLostFocus)
					restore();
				else
					check();
			}
		});
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
	}

	void check() {
		if (getText().length() > 0)
			try {
				int val = Integer.parseInt(getText());
				if (val >= MIN && val <= MAX) {
					value.set(val);
				} else {
					if (val < MIN)
						value.set(MIN);
					else
						value.set(MAX);
					restore();
				}
			} catch (NumberFormatException e) {
				restore();
			}
		else {
			value.set(MIN);
			restore();
		}
	}

	void restore() {
		setText(String.valueOf(value.get()));
	}

	void checkKeyCode(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			check();
			setText(String.valueOf(value.addAndGet(1)));
			check();
			break;
		case KeyEvent.VK_DOWN:
			check();
			setText(String.valueOf(value.addAndGet(-1)));
			check();
			break;
		default:
			break;
		}
	}
}
