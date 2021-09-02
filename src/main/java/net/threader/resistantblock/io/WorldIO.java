package net.threader.resistantblock.io;

import net.threader.resistantblock.ResistantBlocks;
import net.threader.resistantblock.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WorldIO {

    private final File file;
    private final Map<String, Integer> cached = new HashMap<>();
    private JSONObject object;

    public WorldIO(File file) {
        this.file = file;
        boolean first = false;
        try {
            if(!file.exists()) {
                first = true;
                file.createNewFile();
                this.object = new JSONObject();
                this.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!first) {
            this.object = BlockIOManager.extractJSON(file);
        }
    }

    public int getCurrentResistance(Block block) {
        if(cached.containsKey(blockToString(block))) {
            return cached.get(blockToString(block));
        }
        if(!object.containsKey(blockToString(block))) {
            if(Util.isResistantBlock(block.getType())) {
                return Util.getInitialResistance(block.getType());
            }
            return 0;
        }
        return (int) object.get(blockToString(block));
    }

    public void write(Block block, int quantity) {
        cached.remove(blockToString(block));
        cached.put(blockToString(block), quantity);
        object.put(blockToString(block), quantity);
        save();
    }

    public void save() {
        Bukkit.getScheduler().runTaskAsynchronously(ResistantBlocks.instance(), () -> {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(object.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void remove(Block block) {
        cached.remove(blockToString(block));
        object.remove(blockToString(block));
        save();
    }

    private String blockToString(Block block) {
        return block.getX() + ":" + block.getY() + ":" + block.getZ();
    }

}
