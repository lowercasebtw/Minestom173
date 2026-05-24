package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class TreeFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        int var6 = random.nextInt(3) + 4;
        boolean var7 = true;
        if (i2 >= 1 && i2 + var6 + 1 <= 128) {
            for (int var8 = i2; var8 <= i2 + 1 + var6; var8++) {
                byte var9 = 1;
                if (var8 == i2) {
                    var9 = 0;
                }

                if (var8 >= i2 + 1 + var6 - 2) {
                    var9 = 2;
                }

                for (int var10 = i - var9; var10 <= i + var9 && var7; var10++) {
                    for (int var11 = i3 - var9; var11 <= i3 + var9 && var7; var11++) {
                        if (var8 >= 0 && var8 < 128) {
                            int var12 = level.getTile(var10, var8, var11);
                            if (var12 != 0 && var12 != Tile.LEAVES.id) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7) {
                return false;
            } else {
                int var16 = level.getTile(i, i2 - 1, i3);
                if ((var16 == Tile.GRASS.id || var16 == Tile.DIRT.id) && i2 < 128 - var6 - 1) {
                    level.setTileNoUpdate(i, i2 - 1, i3, Tile.DIRT.id);

                    for (int var17 = i2 - 3 + var6; var17 <= i2 + var6; var17++) {
                        int var19 = var17 - (i2 + var6);
                        int var21 = 1 - var19 / 2;

                        for (int var22 = i - var21; var22 <= i + var21; var22++) {
                            int var13 = var22 - i;

                            for (int var14 = i3 - var21; var14 <= i3 + var21; var14++) {
                                int var15 = var14 - i3;
                                if ((Math.abs(var13) != var21 || Math.abs(var15) != var21 || random.nextInt(2) != 0 && var19 != 0)
                                        && !Tile.solid[level.getTile(var22, var17, var14)]) {
                                    level.setTileNoUpdate(var22, var17, var14, Tile.LEAVES.id);
                                }
                            }
                        }
                    }

                    for (int var18 = 0; var18 < var6; var18++) {
                        int var20 = level.getTile(i, i2 + var18, i3);
                        if (var20 == 0 || var20 == Tile.LEAVES.id) {
                            level.setTileNoUpdate(i, i2 + var18, i3, Tile.LOG.id);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
