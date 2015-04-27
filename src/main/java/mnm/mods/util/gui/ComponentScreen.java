package mnm.mods.util.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * A panel wrapper for a screen.
 */
public class ComponentScreen extends GuiScreen {

    private final GuiPanel PANEL = new GuiPanel();

    @Override
    public void drawScreen(int mouseX, int mouseY, float tick) {
        PANEL.drawComponent(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, tick);
    }

    @Override
    public void updateScreen() {
        PANEL.updateComponent();
    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
        PANEL.handleKeyboardInput();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        PANEL.handleMouseInput();
    }

    @Override
    public void onGuiClosed() {
        PANEL.unfocusAll();
        super.onGuiClosed();
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        PANEL.setBounds(0, 0, width, height);
        PANEL.clearComponents();
        super.setWorldAndResolution(mc, width, height);
    }

    /**
     * Gets the main panel on this screen. Add things to this.
     * 
     * @return The main panel
     */
    public GuiPanel getPanel() {
        return PANEL;
    }
}
