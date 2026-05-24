package dev.emortal.legacygeneration;

import dev.emortal.legacygeneration.util.WorldContext;
import net.minestom.server.instance.ChunkLoader;

import java.util.Random;

public interface ChunkGenerator extends ChunkLoader {
    void populateChunk(final WorldContext worldContext, final int chunkX, final int chunkZ);

    Random getRandom();
}
