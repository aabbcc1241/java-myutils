package myutils.gui;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class RadioButtonGroup {
	public Vector<JRadioButton> buttons = new Vector<JRadioButton>();
	public ButtonGroup buttonGroup = new ButtonGroup();

	public void addButton(JRadioButton button) {
		buttons.add(button);
		buttonGroup.add(button);
	}
}
