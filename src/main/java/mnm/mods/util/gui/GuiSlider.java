package mnm.mods.util.gui;

import mnm.mods.util.gui.events.GuiMouseAdapter;
import mnm.mods.util.gui.events.GuiMouseEvent;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;

public class GuiSlider extends GuiComponent implements GuiMouseAdapter {

    private static final ResourceLocation TRANSPARENCY = new ResourceLocation("mnmutils",
            "textures/transparency.png");

    private boolean vertical;
    private double value;

    public GuiSlider(double value, boolean vertical) {
        this.vertical = vertical;
        this.setValue(value);
    }

    @Override
    public void drawComponent(int mouseX, int mouseY) {
        GlStateManager.enableBlend();
        Gui.drawRect(0, 0, getBounds().width, getBounds().height, -1);
        mc.getTextureManager().bindTexture(TRANSPARENCY);
        Gui.drawModalRectWithCustomSizedTexture(1, 1, 0, 0, getBounds().width - 2,
                getBounds().height - 2, 6, 6);
        drawMid();
        if (vertical) {
            int nook = Math.abs((int) (getBounds().height * getValue()) - getBounds().height);
            Gui.drawRect(-1, nook - 1, getBounds().width + 1, nook + 2, 0xffffffff);
            Gui.drawRect(0, nook, getBounds().width, nook + 1, 0xff000000);
        } else {
            int nook = (int) (getBounds().width * getValue());
            Gui.drawRect(nook, 0, nook + 1, getBounds().height, 0xff000000);
        }
        int midX = getBounds().width / 2;
        int midY = getBounds().height / 2;
        drawCenteredString(mc.fontRendererObj, getFormattedValue(), midX, midY, -1);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    protected void drawMid() {
        Gui.drawRect(1, 1, getBounds().width - 1, getBounds().height - 1, getForeColor());
    }

    public String getFormattedValue() {
        return String.format("%%%.0f", getValue() * 100);
    }

    @Override
    public void accept(GuiMouseEvent event) {
        if (event.position.x < getActualPosition().x
                || event.position.y < getActualPosition().y
                || event.position.x > getActualPosition().x + getBounds().width
                || event.position.y > getActualPosition().y + getBounds().height) {
            return;
        }
        if ((event.event == GuiMouseEvent.CLICKED || event.event == GuiMouseEvent.DRAGGED)
                && Mouse.isButtonDown(0)) {
            double val;
            if (vertical) {
                int y = event.position.y - getActualPosition().y;
                val = Math.abs((double) y / (double) getBounds().height - 1);
            } else {
                int x = event.position.x - getActualPosition().x;
                val = (double) x / (double) getBounds().width;
            }
            setValue(val);
        }
        if (event.event == GuiMouseEvent.SCROLLED) {
            setValue(getValue() + event.scroll / 7360D);
        }
    }

    public void setValue(double value) {
        if (value < 0) {
            value = 0;
        }
        if (value > 1) {
            value = 1;
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public boolean isVertical() {
        return vertical;
    }
}