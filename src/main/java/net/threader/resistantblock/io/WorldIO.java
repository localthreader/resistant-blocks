package net.threader.resistantblock.io;

import org.bukkit.block.Block;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorldIO {

    private File file;
    private JSONObject object;

    public WorldIO(File file) {
        this.file = file;
        try {
            if(!file.exists()) {
                file.createNewFile();
                this.object = new JSONObject();
                this.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object = BlockIOManager.extractJSON(file);
    }

    private void write(Block block, int quantity) {
        object.put(blockToString(block), quantity);
    }

    public void save() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String blockToString(Block block) {
        return block.getX() + ":" + block.getY() + ":" + block.getZ();
    }

}
