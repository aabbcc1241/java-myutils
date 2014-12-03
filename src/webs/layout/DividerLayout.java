package webs.layout;

/*
 * $Id: DividerLayout.java,v 1.1.1.1 2005/04/07 18:36:20 pocho Exp $
 */
// Revised from javautils swing;

import java.awt.*;

/**
 * <p>
 * <code>DividerLayout</code> is layout that divides two components with the
 * column of actions. It is especially usefull for components that contains two
 * lists of elements and buttons for transfering elements between these two
 * lists.
 * </p>
 * <p>
 * Components are indicated as {@link #WEST}, {@link #EAST} and {@link #CENTER}.
 * </p>
 *
 * @version $Name: $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:20 $
 *          Test
 */
public class DividerLayout implements LayoutManager2 {

    /**
     * Indicates west component
     */
    public final static String WEST = "WEST";
    /**
     * indicates east component
     */
    public final static String EAST = "EAST";
    /**
     * indicates center component
     */
    public final static String CENTER = "CENTER";

    /**
     * west component
     */
    protected Component westComponent;

    /**
     * center component
     */
    protected Component centerComponent;

    /**
     * east component
     */
    protected Component eastComponent;

    /**
     * Adds a component to specified position.
     */
    public void addLayoutComponent(Component comp, Object constraints) {
        if (WEST.equalsIgnoreCase((String) constraints)) {
            westComponent = comp;
        } else if (CENTER.equalsIgnoreCase((String) constraints)) {
            centerComponent = comp;
        } else if (EAST.equalsIgnoreCase((String) constraints)) {
            eastComponent = comp;
        }
    }

    /**
     * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
     */
    public Dimension maximumLayoutSize(Container target) {
        Dimension size;
        int width = 0;
        int height = 0;
        if ((westComponent != null) && (westComponent.isVisible())) {
            size = westComponent.getMaximumSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        if ((eastComponent != null) && (eastComponent.isVisible())) {
            size = eastComponent.getMaximumSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        width *= 2;
        if ((centerComponent != null) && (centerComponent.isVisible())) {
            size = centerComponent.getPreferredSize();
            width += size.width;
            height = Math.max(height, size.height);
        }
        return new Dimension(width, height);
    }

    /**
     * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
     */
    public float getLayoutAlignmentX(Container target) {
        return 0.0f;
    }

    /**
     * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
     */
    public float getLayoutAlignmentY(Container target) {
        return 0.0f;
    }

    /**
     * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
     */
    public void invalidateLayout(Container target) {
    }

    /**
     * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
     * java.awt.Component)
     */
    public void addLayoutComponent(String name, Component comp) {
        addLayoutComponent(comp, name);
    }

    /**
     * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
     */
    public void removeLayoutComponent(Component comp) {
        if (comp == westComponent) {
            westComponent = null;
        } else if (comp == centerComponent) {
            centerComponent = null;
        } else if (comp == eastComponent) {
            centerComponent = null;
        }
    }

    /**
     * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
     */
    public Dimension preferredLayoutSize(Container parent) {
        Dimension size;
        int width = 0;
        int height = 0;
        if ((westComponent != null) && (westComponent.isVisible())) {
            size = westComponent.getPreferredSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        if ((eastComponent != null) && (eastComponent.isVisible())) {
            size = eastComponent.getPreferredSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        width *= 2;
        if ((centerComponent != null) && (centerComponent.isVisible())) {
            size = centerComponent.getPreferredSize();
            width += size.width;
            height = Math.max(height, size.height);
        }
        return new Dimension(width, height);
    }

    /**
     * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
     */
    public Dimension minimumLayoutSize(Container parent) {
        Dimension size;
        int width = 0;
        int height = 0;
        if ((westComponent != null) && (westComponent.isVisible())) {
            size = westComponent.getMinimumSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        if ((eastComponent != null) && (eastComponent.isVisible())) {
            size = eastComponent.getMinimumSize();
            width = Math.max(width, size.width);
            height = Math.max(height, size.height);
        }
        width *= 2;
        if ((centerComponent != null) && (centerComponent.isVisible())) {
            size = centerComponent.getPreferredSize();
            width += size.width;
            height += Math.max(height, size.height);
        }
        return new Dimension(width, height);
    }

    /**
     * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
     */
    @SuppressWarnings("unused")
    public void layoutContainer(Container container) {
        Insets insets = container.getInsets();
        Dimension westSize = new Dimension(0, 0);
        Dimension centerSize = new Dimension(0, 0);
        Dimension eastSize = new Dimension(0, 0);
        Rectangle centerBounds = new Rectangle(0, 0, 0, 0);
        Dimension containerSize = container.getSize();
        int centerX = containerSize.width / 2;
        int centerY = containerSize.height / 2;
        if ((centerComponent != null) && (centerComponent.isVisible())) {
            centerSize = centerComponent.getPreferredSize();
            centerSize.width = Math.min(centerSize.width, containerSize.width
                    - insets.left - insets.right);
            centerSize.height = Math.min(centerSize.height,
                    containerSize.height - insets.top - insets.bottom);
            centerComponent.setBounds(centerX - (centerSize.width / 2), centerY
                    - (centerSize.height / 2), centerSize.width,
                    centerSize.height);
            centerBounds = centerComponent.getBounds();
        }
        if ((westComponent != null) && (westComponent.isVisible())) {
            westSize = westComponent.getPreferredSize();
        }
        if ((eastComponent != null) && (eastComponent.isVisible())) {
            eastSize = eastComponent.getPreferredSize();
        }
        /*
		 * int maxWidth = Math.min(westSize.width, eastSize.width); maxWidth =
		 * Math.min(maxWidth, (containerSize.width - centerBounds.width -
		 * insets.left - insets.right) / 2);
		 */
        int maxWidth = (containerSize.width - centerBounds.width - insets.left - insets.right) / 2;

		/*
		 * int maxHeight = Math.min(westSize.height, eastSize.height); maxHeight
		 * = Math.max(maxHeight, containerSize.height - insets.top -
		 * insets.bottom);
		 */
        int maxHeight = containerSize.height - insets.top - insets.bottom;
        if (westComponent != null) {
            westComponent.setBounds(centerBounds.x - maxWidth, centerY
                    - (maxHeight / 2), maxWidth, maxHeight);
        }
        if (eastComponent != null) {
            eastComponent.setBounds(centerBounds.x + centerBounds.width,
                    centerY - (maxHeight / 2), maxWidth, maxHeight);
        }
    }

}