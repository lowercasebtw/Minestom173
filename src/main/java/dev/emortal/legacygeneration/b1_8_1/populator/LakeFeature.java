package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class LakeFeature implements WorldGenerator {
    private final int tile;

    public LakeFeature(int tile) {
        this.tile = tile;
    }

    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        i -= 8;
        i3 -= 8;

        while (i2 > 0 && level.m_64558657(i, i2, i3)) {
            i2--;
        }

        i2 -= 4;
        boolean[] var6 = new boolean[2048];
        int var7 = random.nextInt(4) + 4;

        for (int var8 = 0; var8 < var7; var8++) {
            double var9 = random.nextDouble() * 6.0 + 3.0;
            double var11 = random.nextDouble() * 4.0 + 2.0;
            double var13 = random.nextDouble() * 6.0 + 3.0;
            double var15 = random.nextDouble() * (16.0 - var9 - 2.0) + 1.0 + var9 / 2.0;
            double var17 = random.nextDouble() * (8.0 - var11 - 4.0) + 2.0 + var11 / 2.0;
            double var19 = random.nextDouble() * (16.0 - var13 - 2.0) + 1.0 + var13 / 2.0;

            for (int var21 = 1; var21 < 15; var21++) {
                for (int var22 = 1; var22 < 15; var22++) {
                    for (int var23 = 1; var23 < 7; var23++) {
                        double var24 = (var21 - var15) / (var9 / 2.0);
                        double var26 = (var23 - var17) / (var11 / 2.0);
                        double var28 = (var22 - var19) / (var13 / 2.0);
                        double var30 = var24 * var24 + var26 * var26 + var28 * var28;
                        if (var30 < 1.0) {
                            var6[(var21 * 16 + var22) * 8 + var23] = true;
                        }
                    }
                }
            }
        }

        for (int var35 = 0; var35 < 16; var35++) {
            for (int var39 = 0; var39 < 16; var39++) {
                for (int var10 = 0; var10 < 8; var10++) {
                    boolean var46 = !var6[(var35 * 16 + var39) * 8 + var10]
                            && (
                            var35 < 15 && var6[((var35 + 1) * 16 + var39) * 8 + var10]
                                    || var35 > 0 && var6[((var35 - 1) * 16 + var39) * 8 + var10]
                                    || var39 < 15 && var6[(var35 * 16 + var39 + 1) * 8 + var10]
                                    || var39 > 0 && var6[(var35 * 16 + (var39 - 1)) * 8 + var10]
                                    || var10 < 7 && var6[(var35 * 16 + var39) * 8 + var10 + 1]
                                    || var10 > 0 && var6[(var35 * 16 + var39) * 8 + (var10 - 1)]
                    );
                    if (var46) {
                        Material var12 = level.getMaterial(i + var35, i2 + var10, i3 + var39);
                        if (var10 >= 4 && var12.isLiquid()) {
                            return false;
                        }

                        if (var10 < 4 && !var12.isSolid() && level.getTile(i + var35, i2 + var10, i3 + var39) != this.tile) {
                            return false;
                        }
                    }
                }
            }
        }

        for (int var36 = 0; var36 < 16; var36++) {
            for (int var40 = 0; var40 < 16; var40++) {
                for (int var43 = 0; var43 < 8; var43++) {
                    if (var6[(var36 * 16 + var40) * 8 + var43]) {
                        level.setTileNoUpdate(i + var36, i2 + var43, i3 + var40, var43 >= 4 ? 0 : this.tile);
                    }
                }
            }
        }

        for (int var37 = 0; var37 < 16; var37++) {
            for (int var41 = 0; var41 < 16; var41++) {
                for (int var44 = 4; var44 < 8; var44++) {
                    if (var6[(var37 * 16 + var41) * 8 + var44]
                            && level.getTile(i + var37, i2 + var44 - 1, i3 + var41) == Tile.DIRT.id
                            && level.getBrightness(LightLayer.SKY, i + var37, i2 + var44, i3 + var41) > 0) {
                        level.setTileNoUpdate(i + var37, i2 + var44 - 1, i3 + var41, Tile.GRASS.id);
                    }
                }
            }
        }

        if (Tile.tiles[this.tile].material == Material.LAVA) {
            for (int var38 = 0; var38 < 16; var38++) {
                for (int var42 = 0; var42 < 16; var42++) {
                    for (int var45 = 0; var45 < 8; var45++) {
                        boolean var47 = !var6[(var38 * 16 + var42) * 8 + var45]
                                && (
                                var38 < 15 && var6[((var38 + 1) * 16 + var42) * 8 + var45]
                                        || var38 > 0 && var6[((var38 - 1) * 16 + var42) * 8 + var45]
                                        || var42 < 15 && var6[(var38 * 16 + var42 + 1) * 8 + var45]
                                        || var42 > 0 && var6[(var38 * 16 + (var42 - 1)) * 8 + var45]
                                        || var45 < 7 && var6[(var38 * 16 + var42) * 8 + var45 + 1]
                                        || var45 > 0 && var6[(var38 * 16 + var42) * 8 + (var45 - 1)]
                        );
                        if (var47 && (var45 < 4 || random.nextInt(2) != 0) && level.getMaterial(i + var38, i2 + var45, i3 + var42).isSolid()) {
                            level.setTileNoUpdate(i + var38, i2 + var45, i3 + var42, Tile.STONE.id);
                        }
                    }
                }
            }
        }

        return true;
    }
}
