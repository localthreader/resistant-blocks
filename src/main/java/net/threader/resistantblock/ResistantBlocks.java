package net.threader.resistantblock;

import org.bukkit.plugin.java.JavaPlugin;

public class ResistantBlocks extends JavaPlugin {

    private static ResistantBlocks instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    public static ResistantBlocks instance() {
        return instance;
    }
}
