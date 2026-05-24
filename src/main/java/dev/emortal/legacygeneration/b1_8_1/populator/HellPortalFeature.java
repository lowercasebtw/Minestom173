package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class HellPortalFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        if (!level.m_64558657(i, i2, i3)) {
            return false;
        } else if (level.getTile(i, i2 + 1, i3) != Tile.NETHERRACK.id) {
            return false;
        } else {
            level.setTile(i, i2, i3, Tile.GLOWSTONE.id);

            for (int var6 = 0; var6 < 1500; var6++) {
                int var7 = i + random.nextInt(8) - random.nextInt(8);
                int var8 = i2 - random.nextInt(12);
                int var9 = i3 + random.nextInt(8) - random.nextInt(8);
                if (level.getTile(var7, var8, var9) == 0) {
                    int var10 = 0;

                    for (int var11 = 0; var11 < 6; var11++) {
                        int var12 = 0;
                        if (var11 == 0) {
                            var12 = level.getTile(var7 - 1, var8, var9);
                        }

                        if (var11 == 1) {
                            var12 = level.getTile(var7 + 1, var8, var9);
                        }

                        if (var11 == 2) {
                            var12 = level.getTile(var7, var8 - 1, var9);
                        }

                        if (var11 == 3) {
                            var12 = level.getTile(var7, var8 + 1, var9);
                        }

                        if (var11 == 4) {
                            var12 = level.getTile(var7, var8, var9 - 1);
                        }

                        if (var11 == 5) {
                            var12 = level.getTile(var7, var8, var9 + 1);
                        }

                        if (var12 == Tile.GLOWSTONE.id) {
                            var10++;
                        }
                    }

                    if (var10 == 1) {
                        level.setTile(var7, var8, var9, Tile.GLOWSTONE.id);
                    }
                }
            }

            return true;
        }
    }
}
