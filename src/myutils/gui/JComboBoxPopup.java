package myutils.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
class JComboBoxPopup extends JComboBox {
    private boolean showing = false;

    public JComboBoxPopup(Object[] objects) {
        super(objects);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switchPopup();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showPopup(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                showPopup(false);
            }
        });
        setEditable(true);
        // setKeySelectionManager(aManager);
    }

    @Override
    public void setModel(ComboBoxModel aModel) {
        super.setModel(aModel);
        for (Component component : getComponents())
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
    }

    void showPopup(boolean toshow) {
        if (toshow)
            showPopup();
        else
            hidePopup();
        showing = toshow;
        System.out.println("showing: " + showing);
    }

    void switchPopup() {
        if (showing)
            hidePopup();
        else
            showPopup();
        showing = !showing;
        System.out.println("showing: " + showing);
    }

    @Override
    public void showPopup() {
        super.showPopup();
        setPopupVisible(true);
    }

    @Override
    public void hidePopup() {
        super.hidePopup();
        setPopupVisible(false);
    }
}
