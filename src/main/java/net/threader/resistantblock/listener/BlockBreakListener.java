package net.threader.resistantblock.listener;

import net.threader.resistantblock.io.BlockIOManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        BlockIOManager.remove(event.getBlock());
    }

}
