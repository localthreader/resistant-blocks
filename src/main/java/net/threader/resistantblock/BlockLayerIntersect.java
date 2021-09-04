package net.threader.resistantblock;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

public class BlockLayerIntersect {

    public static Location[][][] intersect(Location source, int radius) {
        Location[][][] intersected = new Location[radius*2+1][radius*2+1][radius*2+1];
        for(int i = 0; i<radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                for(int k = 0; k<radius*2+1; k++) {
                    int x = source.getBlockX() + (j - radius);
                    int y = source.getBlockY() + (i - radius);
                    int z = source.getBlockZ() + (k - radius);
                    intersected[j][k][i] = new Location(source.getWorld(), x, y, z);
                }
            }
        }
        return intersected;
    }

}
