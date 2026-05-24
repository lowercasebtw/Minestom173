package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class OreFeature implements WorldGenerator {
    private final int tile;
    private final int count;

    public OreFeature(int tile, int count) {
        this.tile = tile;
        this.count = count;
    }

    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        float var6 = random.nextFloat() * (float) Math.PI;
        double var7 = i + 8 + MathHelper.sin(var6) * this.count / 8.0F;
        double var9 = i + 8 - MathHelper.sin(var6) * this.count / 8.0F;
        double var11 = i3 + 8 + MathHelper.cos(var6) * this.count / 8.0F;
        double var13 = i3 + 8 - MathHelper.cos(var6) * this.count / 8.0F;
        double var15 = i2 + random.nextInt(3) - 2;
        double var17 = i2 + random.nextInt(3) - 2;

        for (int var19 = 0; var19 <= this.count; var19++) {
            double var20 = var7 + (var9 - var7) * var19 / this.count;
            double var22 = var15 + (var17 - var15) * var19 / this.count;
            double var24 = var11 + (var13 - var11) * var19 / this.count;
            double var26 = random.nextDouble() * this.count / 16.0;
            double var28 = (MathHelper.sin(var19 * (float) Math.PI / this.count) + 1.0F) * var26 + 1.0;
            double var30 = (MathHelper.sin(var19 * (float) Math.PI / this.count) + 1.0F) * var26 + 1.0;
            int var32 = MathHelper.floor(var20 - var28 / 2.0);
            int var33 = MathHelper.floor(var22 - var30 / 2.0);
            int var34 = MathHelper.floor(var24 - var28 / 2.0);
            int var35 = MathHelper.floor(var20 + var28 / 2.0);
            int var36 = MathHelper.floor(var22 + var30 / 2.0);
            int var37 = MathHelper.floor(var24 + var28 / 2.0);

            for (int var38 = var32; var38 <= var35; var38++) {
                double var39 = (var38 + 0.5 - var20) / (var28 / 2.0);
                if (var39 * var39 < 1.0) {
                    for (int var41 = var33; var41 <= var36; var41++) {
                        double var42 = (var41 + 0.5 - var22) / (var30 / 2.0);
                        if (var39 * var39 + var42 * var42 < 1.0) {
                            for (int var44 = var34; var44 <= var37; var44++) {
                                double var45 = (var44 + 0.5 - var24) / (var28 / 2.0);
                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0 && level.getTile(var38, var41, var44) == Tile.STONE.id) {
                                    level.setTileNoUpdate(var38, var41, var44, this.tile);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
