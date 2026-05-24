package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class PineFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        int var6 = random.nextInt(5) + 7;
        int var7 = var6 - random.nextInt(2) - 3;
        int var8 = var6 - var7;
        int var9 = 1 + random.nextInt(var8 + 1);
        boolean var10 = true;
        if (i2 >= 1 && i2 + var6 + 1 <= 128) {
            for (int var11 = i2; var11 <= i2 + 1 + var6 && var10; var11++) {
                int var12 = 1;
                if (var11 - i2 < var7) {
                    var12 = 0;
                } else {
                    var12 = var9;
                }

                for (int var13 = i - var12; var13 <= i + var12 && var10; var13++) {
                    for (int var14 = i3 - var12; var14 <= i3 + var12 && var10; var14++) {
                        if (var11 >= 0 && var11 < 128) {
                            int var15 = level.getTile(var13, var11, var14);
                            if (var15 != 0 && var15 != Tile.LEAVES.id) {
                                var10 = false;
                            }
                        } else {
                            var10 = false;
                        }
                    }
                }
            }

            if (!var10) {
                return false;
            } else {
                int var18 = level.getTile(i, i2 - 1, i3);
                if ((var18 == Tile.GRASS.id || var18 == Tile.DIRT.id) && i2 < 128 - var6 - 1) {
                    level.setTileNoUpdate(i, i2 - 1, i3, Tile.DIRT.id);
                    int var20 = 0;

                    for (int var21 = i2 + var6; var21 >= i2 + var7; var21--) {
                        for (int var23 = i - var20; var23 <= i + var20; var23++) {
                            int var25 = var23 - i;

                            for (int var16 = i3 - var20; var16 <= i3 + var20; var16++) {
                                int var17 = var16 - i3;
                                if ((Math.abs(var25) != var20 || Math.abs(var17) != var20 || var20 <= 0) && !Tile.solid[level.getTile(var23, var21, var16)]) {
                                    level.setTileAndDataNoUpdate(var23, var21, var16, Tile.LEAVES.id, 1);
                                }
                            }
                        }

                        if (var20 >= 1 && var21 == i2 + var7 + 1) {
                            var20--;
                        } else if (var20 < var9) {
                            var20++;
                        }
                    }

                    for (int var22 = 0; var22 < var6 - 1; var22++) {
                        int var24 = level.getTile(i, i2 + var22, i3);
                        if (var24 == 0 || var24 == Tile.LEAVES.id) {
                            level.setTileAndDataNoUpdate(i, i2 + var22, i3, Tile.LOG.id, 1);
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
