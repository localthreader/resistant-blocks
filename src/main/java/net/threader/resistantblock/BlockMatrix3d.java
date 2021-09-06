package net.threader.resistantblock;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockMatrix3d {

    private int radius;
    private Location center;

    public BlockMatrix3d(int radius, Location center) {
        this.radius = radius;
        this.center = center;
    }

    public Block[][][] expand() {
        Block[][][] expanded = new Block[radius*2+1][radius*2+1][radius*2+1];
        for(int y = 0; y<radius*2+1; y++) {
            for(int x = 0; x<radius*2+1; x++) {
                for(int z = 0; z<radius*2+1; z++) {
                    int newX = center.getBlockX() + (x - radius);
                    int newY = center.getBlockY() + (y - radius);
                    int newZ = center.getBlockZ() + (z - radius);
                    expanded[y][x][z] = new Location(center.getWorld(), newX, newY, newZ).getBlock();
                }
            }
        }
        return expanded;
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

    public Block[] faceX(int index) {
        Block[] face = new Block[(radius*2+1)*(radius*2+1)];
        Block[][][] expanded = expand();
        int in = 0;
        for(int i = 0; i<radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                face[in] = expanded[i][index][j];
                in++;
            }
        }
        return face;
    }

    public Block[] faceZ(int index) {
        Block[] face = new Block[(radius*2+1)*(radius*2+1)];
        Block[][][] expanded = expand();
        int in = 0;
        for(int i = 0; i<radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                face[in] = expanded[i][j][index];
                in++;
            }
        }
        return face;
    }

    public Block[] faceY(int index) {
        Block[] face = new Block[(radius*2+1)*(radius*2+1)];
        Block[][][] expanded = expand();
        int in = 0;
        for(int i = 0; i<radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                face[in] = expanded[index][i][j];
                in++;
            }
        }
        return face;
    }

    public Block[] edge() {
        List<Block> edge = new ArrayList<>();
        Block[][][] expanded = expand();
        for(int y = 0; y<radius*2+1; y++) {
            for(int x = 0; x<radius*2+1; x++) {
                for(int z = 0; z<radius*2+1; z++) {
                    if((y == 0 || y == radius*2) && (x == 0 || x == radius*2)) {
                        edge.add(expanded[y][x][z]);
                    } else if ((z == 0 || z == radius*2) && (y == 0 || y == radius*2)) {
                        edge.add(expanded[y][x][z]);
                    } else if ((x == 0 || x == radius*2) && (z == 0 || z == radius*2)) {
                        edge.add(expanded[y][x][z]);
                    }
                }
            }
        }
        return (Block[]) edge.toArray();
    }

    public Block[][][] subtract(Block... blocks) {
        Set<Block> blockSet = new HashSet<>(new HashSet<>(Arrays.asList(blocks)));
        Block[][][] expanded = expand();
        for(int i = 0; i < radius*2+1; i++) {
            for(int j = 0; j<radius*2+1; j++) {
                for(int k = 0; k<radius*2+1; k++) {
                    if(blockSet.contains(expanded[i][j][k])) {
                        expanded[i][j][k] = null;
                    }
                }
            }
        }
        return expanded;
    }

}
