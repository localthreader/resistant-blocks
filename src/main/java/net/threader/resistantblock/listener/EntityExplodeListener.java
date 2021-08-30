package net.threader.resistantblock.listener;

import net.threader.resistantblock.ResistantBlocks;
import net.threader.resistantblock.Util;
import net.threader.resistantblock.io.BlockIOManager;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        ConfigurationSection damages = ResistantBlocks.instance().getConfig().getConfigurationSection("explosion_damages");
        Iterator<Block> blocks = event.blockList().iterator();

        int damage = 0;

        if(event.getEntity() instanceof TNTPrimed) {
            damage = damages.getInt("tnt");
        } else if (event.getEntity() instanceof EnderCrystal) {
            damage = damages.getInt("end_crystal");
        } else if (event.getEntity() instanceof Creeper) {
            damage = damages.getInt("creeper");
        }

        while(blocks.hasNext()) {
            Block block = blocks.next();
            if(!Util.isResistantBlock(block.getType())) {
                continue;
            }
            int newResistance = BlockIOManager.getCurrentResistance(block) - damage;
            if(newResistance <= 0) {
                block.breakNaturally();
                BlockIOManager.remove(block);
                continue;
            }
            BlockIOManager.writeAndSave(block, newResistance);
        }
    }

}
