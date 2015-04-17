package mnm.mods.util.gui;

import java.awt.Rectangle;

import mnm.mods.util.SettingValue;

public abstract class GuiSetting<T> extends GuiComponent implements IGuiInput<T> {

    private final SettingValue<T> setting;
    private final IGuiInput<T> input;

    public GuiSetting(SettingValue<T> setting2) {
        this(setting2, null);
    }

    public GuiSetting(SettingValue<T> setting, IGuiInput<T> input) {
        this.setting = setting;
        this.input = input;
        this.setValue(setting.getValue());
    }

    @Override
    public void drawComponent(int mouseX, int mouseY) {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).drawComponent(mouseX, mouseY);
        }
    }

    @Override
    void setParent(GuiPanel guiPanel) {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).setParent(guiPanel);
        }
        super.setParent(guiPanel);
    }

    @Override
    public void setBounds(Rectangle bounds) {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).setBounds(bounds);
        }
        super.setBounds(bounds);
    }

    @Override
    public void setSize(int width, int height) {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).setSize(width, height);
        }
        super.setSize(width, height);
    }

    @Override
    public void setPosition(int xPos, int yPos) {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).setPosition(xPos, yPos);
        }
        super.setPosition(xPos, yPos);
    }

    @Override
    public void updateComponent() {
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).updateComponent();
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).handleMouseInput();
        }
    }

    @Override
    public void handleKeyboardInput() {
        super.handleKeyboardInput();
        if (input instanceof GuiComponent) {
            ((GuiComponent) input).handleKeyboardInput();
        }
    }

    public IGuiInput<T> getInput() {
        return input;
    }

    public SettingValue<T> getSetting() {
        return this.setting;
    }

    public void reset() {
        this.setValue(this.setting.getValue());
    }

    public void setDefault() {
        this.setValue(this.setting.getDefaultValue());
    }

    public void saveValue() {
        this.setting.setValue(this.getValue());
    }
}
