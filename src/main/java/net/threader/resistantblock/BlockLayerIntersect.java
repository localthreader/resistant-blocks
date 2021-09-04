package net.threader.resistantblock;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

public class BlockLayerIntersect {

    private Location source;
    private int radius;

    private Location[][][] intersect() {
        Location[][][] intersected = new Location[radius*2+1][radius*2+1][radius*2+1];
        int index = 0;
        for(int i = -radius; i<radius + 1; i++) {
            Location[][] layer = new Location[radius*2+1][radius*2+1];
            for(int j = -radius; j<radius + 1; j++) {
                for(int k = -radius; k<radius + 1; k++) {
                    int x = source.getBlockX() + j;
                    int y = source.getBlockY() + i;
                    int z = source.getBlockZ() + k;
                    layer[j][k] = new Location(source.getWorld(), x, y, z);
                }
            }
            intersected[index] = layer;
            index++;
        }
        return intersected;
    }

}
