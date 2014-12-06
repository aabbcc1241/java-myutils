package myutils.gui;

import javax.swing.*;
import java.util.Vector;

public class RadioButtonGroup {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Vector<JRadioButton> buttons = new Vector<JRadioButton>();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public void addButton(JRadioButton button) {
        buttons.add(button);
        buttonGroup.add(button);
    }
}
