package mnm.mods.util.gui;

import com.google.common.collect.Maps;
import mnm.mods.util.ILocation;
import mnm.mods.util.Location;

import java.awt.Dimension;
import java.util.EnumMap;
import java.util.Map.Entry;

/**
 * A recreation of Border Layout.
 *
 * @author Matthew Matthew
 * @see java.awt.BorderLayout
 */
public class BorderLayout implements ILayout {

    private EnumMap<Position, GuiComponent> components = Maps.newEnumMap(Position.class);

    @Override
    public void addComponent(GuiComponent comp, Object constraints) {
        if (constraints == null || constraints instanceof Position) {
            addComponent((Position) constraints, comp);
        } else {
            throw new IllegalArgumentException(
                    "cannot add to layout: constraint must be a Position enum");
        }
    }

    private synchronized void addComponent(Position constraint, GuiComponent comp) {
        if (constraint == null) {
            constraint = Position.CENTER;
        }

        components.put(constraint, comp);
    }

    @Override
    public synchronized void removeComponent(GuiComponent comp) {
        components.entrySet().stream()
                .filter(c -> c.getValue().equals(comp))
                .findFirst()
                .ifPresent(c -> components.remove(c.getKey()));
    }

    @Override
    public void layoutComponents(GuiPanel parent) {
        ILocation pbounds = parent.getLocation();
        GuiComponent center = components.get(Position.CENTER);
        GuiComponent north = components.get(Position.NORTH);
        GuiComponent south = components.get(Position.SOUTH);
        GuiComponent west = components.get(Position.WEST);
        GuiComponent east = components.get(Position.EAST);

        if (north != null) {
            north.setLocation(new Location(0, 0, pbounds.getWidth(), north.getMinimumSize().height));
        }

        if (west != null) {
            Location bounds = new Location();
            bounds.setWidth(west.getMinimumSize().width);

            if (north != null) {
                bounds.setYPos(north.getLocation().getHeight());
            }

            if (south == null) {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight());
                } else {
                    bounds.setHeight(pbounds.getHeight() - north.getLocation().getHeight());
                }
            } else {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight() - south.getLocation().getHeight());
                } else {
                    bounds.setHeight(pbounds.getHeight() - south.getLocation().getHeight() - north.getLocation().getHeight());
                }
            }
            west.setLocation(bounds);
        }

        if (center != null) {
            Location bounds = new Location();

            if (north != null) {
                bounds.setYPos(north.getLocation().getHeight() + 1);
            }

            if (west != null) {
                bounds.setXPos(west.getLocation().getWidth());
            }

            if (east == null) {
                if (west == null) {
                    bounds.setWidth(pbounds.getWidth());
                } else {
                    bounds.setWidth(pbounds.getWidth() - west.getLocation().getWidth());
                }
            } else {
                if (west == null) {
                    bounds.setWidth(pbounds.getWidth() - east.getLocation().getWidth());
                } else {
                    bounds.setWidth(pbounds.getWidth() - east.getLocation().getWidth() - west.getLocation().getWidth());
                }
            }

            if (south == null) {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight());
                } else {
                    bounds.setHeight(pbounds.getHeight() - north.getLocation().getHeight());
                }
            } else {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight() - south.getLocation().getHeight() - 1);
                } else {
                    bounds.setHeight(pbounds.getHeight() - south.getLocation().getHeight() - north.getLocation().getHeight() - 2);
                }
            }
            center.setLocation(bounds);
        }

        if (east != null) {
            Location bounds = new Location();

            bounds.setXPos(pbounds.getWidth() - east.getMinimumSize().width);
            bounds.setWidth(east.getMinimumSize().width);
            if (north != null) {
                bounds.setYPos(north.getLocation().getHeight());
            }
            if (south == null) {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight());
                } else {
                    bounds.setHeight(pbounds.getHeight() - north.getMinimumSize().height);
                }
            } else {
                if (north == null) {
                    bounds.setHeight(pbounds.getHeight() - south.getMinimumSize().height);
                } else {
                    bounds.setHeight(pbounds.getHeight() - south.getMinimumSize().height - north.getMinimumSize().height);
                }
            }
            east.setLocation(bounds);
        }

        if (south != null) {

            int x = 0;
            int y = pbounds.getHeight() - south.getLocation().getHeight();
            int width = pbounds.getWidth();
            int height = south.getMinimumSize().height;

            south.setLocation(new Location(x, y, width, height));
        }
    }

    @Override
    public Dimension getLayoutSize() {
        int width = 0;
        int height = 0;
        for (Entry<Position, GuiComponent> comp : components.entrySet()) {
            Dimension size = comp.getValue().getMinimumSize();
            switch (comp.getKey()) {
            case CENTER:
                width += size.width;
                height += size.height;
                break;
            case EAST:
            case WEST:
                width += size.width;
                break;
            case NORTH:
            case SOUTH:
                height += size.height;
                break;
            }
        }
        return new Dimension(width, height);
    }

    public enum Position {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        CENTER
    }
}
