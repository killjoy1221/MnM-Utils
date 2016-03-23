package mnm.mods.util.gui;

import mnm.mods.util.text.FancyFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

/**
 * Gui component label used to show text on the screen.
 *
 * @author Matthew
 */
public class GuiLabel extends GuiComponent {

    private FancyFontRenderer fr;
    private IChatComponent text;
    private float angle;

    public GuiLabel() {
        this.fr = new FancyFontRenderer(Minecraft.getMinecraft().fontRendererObj);
    }

    /**
     * Creates a label from a chat component.
     *
     * @param chat The text
     */
    public GuiLabel(IChatComponent chat) {
        this();
        this.setText(chat);
    }

    /**
     * Creates a label from a string
     *
     * @param string The string
     * @deprecated Use {@link #GuiLabel(IChatComponent)}
     */
    @Deprecated
    public GuiLabel(String string) {
        this(string, 0);
    }

    /**
     * Creates a label from a string and angle.
     *
     * @param string The string
     * @param angle The angle
     */
    @Deprecated
    public GuiLabel(String string, float angle) {
        this(new ChatComponentText(string));
        setAngle(angle);
    }

    @Override
    public void drawComponent(int mouseX, int mouseY) {
        if (getText() == null)
            return;
        GlStateManager.pushMatrix();
        GlStateManager.rotate(angle, 0, 0, angle);
        if (angle < 180) {
            GlStateManager.translate(-angle / 1.5, -angle / 4, 0);
        } else {
            GlStateManager.translate(-angle / 15, angle / 40, 0);
        }

        fr.drawChat(getText(), 0, 1, getForeColor().getHex(), true);

        GlStateManager.popMatrix();
        super.drawComponent(mouseX, mouseY);
    }

    public void setText(String text) {
        setText(new ChatComponentText(text));
    }

    /**
     * Sets the string of this label
     *
     * @param text The string
     */
    public void setText(IChatComponent text) {
        this.text = text;
    }

    /**
     * Gets the string of this label
     *
     * @return The string
     */
    public IChatComponent getText() {
        return text;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

}
