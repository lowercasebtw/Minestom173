package dev.emortal.legacygeneration.b1_7_3;

import dev.emortal.legacygeneration.ChunkGenerator;
import dev.emortal.legacygeneration.b1_7_3.dimension.nether.ChunkProviderHell;
import dev.emortal.legacygeneration.b1_7_3.dimension.overworld.ChunkProviderOverworld;
import dev.emortal.legacygeneration.b1_7_3.dimension.sky.ChunkProviderSky;

public interface ChunkGenerator173 extends ChunkGenerator {
    static ChunkProviderOverworld getOverworldGenerator(final long seed) {
        return new ChunkProviderOverworld(0, 127, seed);
    }

    static ChunkProviderHell getNetherGenerator(final long seed) {
        return new ChunkProviderHell(0, 127, seed);
    }

    static ChunkProviderSky getSkyGenerator(final long seed) {
        return new ChunkProviderSky(0, 127, seed);
    }
}
