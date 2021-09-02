package net.threader.resistantblock;

import net.threader.resistantblock.listener.BlockListeners;
import net.threader.resistantblock.listener.EntityExplodeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ResistantBlocks extends JavaPlugin {

    private static ResistantBlocks instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListeners(), this);
    }

    public static ResistantBlocks instance() {
        return instance;
    }
}
