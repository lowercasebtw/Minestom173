package dev.emortal.minestom173;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.generator.dimension.nether.ChunkProviderHell;
import dev.emortal.minestom173.generator.dimension.overworld.ChunkProviderOverworld;
import dev.emortal.minestom173.generator.dimension.sky.ChunkProviderSky;
import net.minestom.server.instance.ChunkLoader;

import java.util.Random;

public interface OldChunkGenerator extends ChunkLoader {
    static ChunkProviderOverworld getOverworldGenerator(final long seed) {
        return new ChunkProviderOverworld(0, 127, seed);
    }

    static ChunkProviderHell getNetherGenerator(final long seed) {
        return new ChunkProviderHell(0, 127, seed);
    }

    static ChunkProviderSky getSkyGenerator(final long seed) {
        return new ChunkProviderSky(0, 127, seed);
    }

    void populateChunk(final WorldContext worldContext, final int chunkX, final int chunkZ);

    Random getRandom();
}
