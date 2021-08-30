package net.threader.resistentblock;

import org.bukkit.plugin.java.JavaPlugin;

public class ResistentBlocks extends JavaPlugin {

    private static ResistentBlocks instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static ResistentBlocks instance() {
        return instance;
    }
}
