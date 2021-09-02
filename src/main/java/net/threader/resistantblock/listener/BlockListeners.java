package net.threader.resistantblock.listener;

import net.threader.resistantblock.Util;
import net.threader.resistantblock.io.BlockIOManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class BlockListeners implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        BlockIOManager.remove(event.getBlock());
    }

    @EventHandler
    public void onMove(BlockPistonRetractEvent event) {
        event.getBlocks().stream().filter(x -> Util.isResistantBlock(x.getType())).findFirst().ifPresent(x -> event.setCancelled(true));
    }

    @EventHandler
    public void onMove(BlockPistonExtendEvent event) {
        event.getBlocks().stream().filter(x -> Util.isResistantBlock(x.getType())).findFirst().ifPresent(x -> event.setCancelled(true));
    }

    @EventHandler
    public void onExplode(BlockExplodeEvent event) {
        if(Util.isResistantBlock(event.getBlock().getType())) {
            event.setCancelled(true);
        }
    }
}
