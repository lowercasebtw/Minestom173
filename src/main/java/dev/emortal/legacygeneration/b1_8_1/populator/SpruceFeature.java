package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class SpruceFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        int var6 = random.nextInt(4) + 6;
        int var7 = 1 + random.nextInt(2);
        int var8 = var6 - var7;
        int var9 = 2 + random.nextInt(2);
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
                int var21 = level.getTile(i, i2 - 1, i3);
                if ((var21 == Tile.GRASS.id || var21 == Tile.DIRT.id) && i2 < 128 - var6 - 1) {
                    level.setTileNoUpdate(i, i2 - 1, i3, Tile.DIRT.id);
                    int var23 = random.nextInt(2);
                    int var24 = 1;
                    byte var25 = 0;

                    for (int var26 = 0; var26 <= var8; var26++) {
                        int var16 = i2 + var6 - var26;

                        for (int var17 = i - var23; var17 <= i + var23; var17++) {
                            int var18 = var17 - i;

                            for (int var19 = i3 - var23; var19 <= i3 + var23; var19++) {
                                int var20 = var19 - i3;
                                if ((Math.abs(var18) != var23 || Math.abs(var20) != var23 || var23 <= 0) && !Tile.solid[level.getTile(var17, var16, var19)]) {
                                    level.setTileAndDataNoUpdate(var17, var16, var19, Tile.LEAVES.id, 1);
                                }
                            }
                        }

                        if (var23 >= var24) {
                            var23 = var25;
                            var25 = 1;
                            if (++var24 > var9) {
                                var24 = var9;
                            }
                        } else {
                            var23++;
                        }
                    }

                    int var27 = random.nextInt(3);

                    for (int var28 = 0; var28 < var6 - var27; var28++) {
                        int var29 = level.getTile(i, i2 + var28, i3);
                        if (var29 == 0 || var29 == Tile.LEAVES.id) {
                            level.setTileAndDataNoUpdate(i, i2 + var28, i3, Tile.LOG.id, 1);
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
