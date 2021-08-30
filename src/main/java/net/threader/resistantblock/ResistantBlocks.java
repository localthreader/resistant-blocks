package net.threader.resistantblock;

import net.threader.resistantblock.listener.EntityExplodeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ResistantBlocks extends JavaPlugin {

    private static ResistantBlocks instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
    }

    public static ResistantBlocks instance() {
        return instance;
    }
}
