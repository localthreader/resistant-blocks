package net.threader.resistantblock.io;

import net.threader.resistantblock.ResistantBlocks;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlockIOManager {

    public static final Map<UUID, WorldIO> cached = new HashMap<>();
    public static final File WORLD_DIR = new File(ResistantBlocks.instance().getDataFolder(), "worlds");

    public WorldIO getWorldIO(UUID world) {
        if (cached.containsKey(world)) {
            return cached.get(world);
        }
        WorldIO io = new WorldIO(new File(WORLD_DIR, world.toString() + ".json"));
        cached.put(world, io);
        return io;
    }

    public static JSONObject extractJSON(File file) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file))) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
