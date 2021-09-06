package net.threader.resistantblock;

import net.threader.resistantblock.command.ResistantBlockCommand;
import net.threader.resistantblock.listener.BlockListeners;
import net.threader.resistantblock.listener.EntityExplodeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ResistantBlocks extends JavaPlugin {

    private static ResistantBlocks instance;
    public static File WORLD_DIR;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        WORLD_DIR = new File(ResistantBlocks.instance().getDataFolder(), "worlds");
        if(!WORLD_DIR.exists()) {
            WORLD_DIR.mkdirs();
        }
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListeners(), this);

        getCommand("resistantblocks").setExecutor(new ResistantBlockCommand());
    }

    public static ResistantBlocks instance() {
        return instance;
    }
}
