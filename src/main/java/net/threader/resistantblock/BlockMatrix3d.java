package net.threader.resistantblock;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Set;

public class BlockMatrix3d {

    private int radius;
    private Location center;

    public BlockMatrix3d(int radius, Location center) {
        this.radius = radius;
        this.center = center;
    }

    public Block[][][] expand() {
        Block[][][] intersected = new Block[radius*2+1][radius*2+1][radius*2+1];
        for(int i = 0; i<radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                for(int k = 0; k<radius*2+1; k++) {
                    int x = center.getBlockX() + (j - radius);
                    int y = center.getBlockY() + (i - radius);
                    int z = center.getBlockZ() + (k - radius);
                    intersected[i][j][k] = new Location(center.getWorld(), x, y, z).getBlock();
                }
            }
        }
        return intersected;
    }

    public Block[] vertex() {
        Block[][][] expand = expand();
        Block[] vertex = new Block[8];
        vertex[0] = expand[0][0][0];
        vertex[1] = expand[0][radius*2][0];
        vertex[2] = expand[0][0][radius*2];
        vertex[3] = expand[0][radius*2][radius*2];
        vertex[4] = expand[radius*2][0][0];
        vertex[5] = expand[radius*2][radius*2][0];
        vertex[6] = expand[radius*2][0][radius*2];
        vertex[7] = expand[radius*2][radius*2][radius*2];
        return vertex;
    }

}
