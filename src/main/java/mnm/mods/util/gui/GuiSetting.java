package mnm.mods.util.gui;

import mnm.mods.util.SettingValue;

public abstract class GuiSetting<T> extends GuiComponent {

    private T value;
    private final SettingValue<T> setting;

    public GuiSetting(SettingValue<T> setting) {
        this.setting = setting;
        this.setValue(setting.getValue());
    }

    public void setValue(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
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
