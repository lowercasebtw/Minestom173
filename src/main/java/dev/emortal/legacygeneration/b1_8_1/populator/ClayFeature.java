package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class ClayFeature implements WorldGenerator {
    private final int tile = Tile.CLAY.id;
    private final int radius;

    public ClayFeature(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        if (level.getMaterial(i, i2, i3) != Material.WATER) {
            return false;
        } else {
            int var6 = random.nextInt(this.radius - 2) + 2;
            byte var7 = 1;

            for (int var8 = i - var6; var8 <= i + var6; var8++) {
                for (int var9 = i3 - var6; var9 <= i3 + var6; var9++) {
                    int var10 = var8 - i;
                    int var11 = var9 - i3;
                    if (var10 * var10 + var11 * var11 <= var6 * var6) {
                        for (int var12 = i2 - var7; var12 <= i2 + var7; var12++) {
                            int var13 = level.getTile(var8, var12, var9);
                            if (var13 == Tile.DIRT.id || var13 == Tile.CLAY.id) {
                                level.setTileNoUpdate(var8, var12, var9, this.tile);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
