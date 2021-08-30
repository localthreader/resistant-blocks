package net.threader.resistantblock;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

public class Util {
    public static boolean isResistantBlock(Material block) {
        return ResistantBlocks.instance().getConfig().getConfigurationSection("block_resistances").contains(block.getKey().getKey());
    }

    public static int getInitialResistance(Material material) {
        ConfigurationSection section = ResistantBlocks.instance().getConfig().getConfigurationSection("block_resistances");
        return section.getInt(material.getKey().getKey());
    }

}
