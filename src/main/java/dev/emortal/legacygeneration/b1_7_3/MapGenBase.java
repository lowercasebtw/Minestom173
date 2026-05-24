package dev.emortal.legacygeneration.b1_7_3;

import dev.emortal.legacygeneration.util.WorldContext;

import java.util.Random;

public class MapGenBase {
    protected final int offset = 8;
    protected final Random random = new Random();

    public MapGenBase() {
    }

    public void generate(final WorldContext worldContext, final int minHeight, final int maxHeight, final long seed, final int chunkX, final int chunkZ) {
        this.random.setSeed(seed);
        long l = this.random.nextLong() / 2L * 2L + 1L;
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        for (int j1 = chunkX - this.offset; j1 <= chunkX + this.offset; ++j1) {
            for (int k1 = chunkZ - this.offset; k1 <= chunkZ + this.offset; ++k1) {
                this.random.setSeed((long) j1 * l + (long) k1 * i1 ^ seed);
                this.generate(worldContext, minHeight, maxHeight, j1, k1, chunkX, chunkZ);
            }
        }
    }

    protected void generate(final WorldContext worldContext, final int minHeight, final int maxHeight, final int i, final int j, final int k, final int l) {
    }
}
