package myutils.gui;

import javax.swing.*;
import java.util.Vector;

public class RadioButtonGroup {
    public Vector<JRadioButton> buttons = new Vector<JRadioButton>();
    public ButtonGroup buttonGroup = new ButtonGroup();

    public void addButton(JRadioButton button) {
        buttons.add(button);
        buttonGroup.add(button);
    }
}
