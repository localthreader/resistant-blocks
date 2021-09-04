package net.threader.resistantblock.listener;

import net.threader.resistantblock.ResistantBlocks;
import net.threader.resistantblock.Util;
import net.threader.resistantblock.io.BlockIOManager;
import org.bukkit.Material;
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
            System.out.println("TNT");
        } else if (event.getEntity() instanceof EnderCrystal) {
            damage = damages.getInt("end_crystal");
            System.out.println("ENDER");
        } else if (event.getEntity() instanceof Creeper) {
            damage = damages.getInt("creeper");
            System.out.println("CREE");
        }

        while(blocks.hasNext()) {
            Block block = blocks.next();
            System.out.println(block.getType());
            if(block.getType() == Material.AIR || !Util.isResistantBlock(block.getType())) {
                continue;
            }

            int currentResistance = BlockIOManager.getCurrentResistance(block);
            int newResistance = currentResistance - damage;

            System.out.println("------------------");
            System.out.println("Bloco: " + block.getType().getKey().getKey());
            System.out.println("Resistencia atual: " + currentResistance);
            System.out.println("Resistencia nova: " + newResistance);
            if(newResistance <= 0) {
                System.out.println("QUEBROU");
                block.breakNaturally();
                BlockIOManager.remove(block);
                continue;
            }
            BlockIOManager.writeAndSave(block, newResistance);
        }
    }

}
