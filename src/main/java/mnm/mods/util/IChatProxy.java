package mnm.mods.util;

import net.minecraft.util.IChatComponent;

public interface IChatProxy {

    void addToChat(String channel, IChatComponent message);
}
