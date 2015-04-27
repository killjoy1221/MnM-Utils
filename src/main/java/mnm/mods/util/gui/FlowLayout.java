package mnm.mods.util.gui;

import java.awt.Dimension;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * A layout that puts items side-by-side and left-to-right.
 * 
 * @author Matthew
 */
public class FlowLayout implements ILayout {

    protected List<GuiComponent> components = Lists.newArrayList();

    @Override
    public void addComponent(GuiComponent comp, Object constraints) {
        components.add(comp);
    }

    @Override
    public void removeComponent(GuiComponent comp) {
        components.remove(comp);
    }

    @Override
    public void layoutComponents(GuiPanel parent) {
        int xPos = 0;
        int yPos = 0;
        for (GuiComponent comp : components) {
            comp.setSize(comp.getMinimumSize().width, comp.getMinimumSize().height);
            comp.setPosition(xPos, yPos);
            xPos += comp.getBounds().width;
        }
    }

    @Override
    public Dimension getLayoutSize() {
        int width = 0;
        int height = 0;

        for (GuiComponent comp : components) {
            Dimension size = comp.getBounds().getSize();
            width += size.width;
            height = Math.max(height, size.height);
        }
        return new Dimension(width, height);
    }

}
