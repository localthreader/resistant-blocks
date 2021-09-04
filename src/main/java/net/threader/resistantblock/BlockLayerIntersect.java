package net.threader.resistantblock;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

public class BlockLayerIntersect {

    private Location source;
    private int radius;

    private Location[][][] intersect() {
        Location[][][] intersected = new Location[radius*2+1][radius*2+1][radius*2+1];
        for(int i = 0; i<radius + 1; i++) {
            Location[][] layer = new Location[radius*2+1][radius*2+1];
            for(int j = 0; j<radius + 1; j++) {
                for(int k = 0; k<radius + 1; k++) {
                    int x = source.getBlockX() + (j - radius);
                    int y = source.getBlockY() + (i - radius);
                    int z = source.getBlockZ() + (k - radius);
                    layer[j][k] = new Location(source.getWorld(), x, y, z);
                }
            }
            intersected[i] = layer;
        }
        return intersected;
    }

}
