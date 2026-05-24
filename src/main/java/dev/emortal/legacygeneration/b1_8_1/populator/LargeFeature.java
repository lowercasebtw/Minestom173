package dev.emortal.legacygeneration.b1_8_1.populator;

import java.util.Random;

public class LargeFeature {
    protected int radius = 8;
    protected Random random = new Random();
    protected Level f_98063768;

    public void apply(ChunkSource source, Level level, int x, int z, byte[] tiles) {
        int var6 = this.radius;
        this.f_98063768 = level;
        this.random.setSeed(level.getSeed());
        long var7 = this.random.nextLong();
        long var9 = this.random.nextLong();

        for (int var11 = x - var6; var11 <= x + var6; var11++) {
            for (int var12 = z - var6; var12 <= z + var6; var12++) {
                long var13 = var11 * var7;
                long var15 = var12 * var9;
                this.random.setSeed(var13 ^ var15 ^ level.getSeed());
                this.addFeature(level, var11, var12, x, z, tiles);
            }
        }
    }

    protected void addFeature(Level level, int i, int i2, int x, int z, byte[] tiles) {
    }
}
