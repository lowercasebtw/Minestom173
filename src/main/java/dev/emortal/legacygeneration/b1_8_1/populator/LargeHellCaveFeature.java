package dev.emortal.legacygeneration.b1_8_1.populator;

import java.util.Random;

public class LargeHellCaveFeature extends LargeFeature {
    protected void addRoom(int x, int z, byte[] tiles, double d, double d2, double d3) {
        this.addTunnel(x, z, tiles, d, d2, d3, 1.0F + this.random.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5);
    }

    protected void addTunnel(int i, int i2, byte[] tiles, double d, double d2, double d3, float f, float f2, float f3, int i3, int i4, double d4) {
        double var17 = i * 16 + 8;
        double var19 = i2 * 16 + 8;
        float var21 = 0.0F;
        float var22 = 0.0F;
        Random var23 = new Random(this.random.nextLong());
        if (i4 <= 0) {
            int var24 = this.radius * 16 - 16;
            i4 = var24 - var23.nextInt(var24 / 4);
        }

        boolean var54 = false;
        if (i3 == -1) {
            i3 = i4 / 2;
            var54 = true;
        }

        int var25 = var23.nextInt(i4 / 2) + i4 / 4;

        for (boolean var26 = var23.nextInt(6) == 0; i3 < i4; i3++) {
            double var27 = 1.5 + MathHelper.sin(i3 * (float) Math.PI / i4) * f * 1.0F;
            double var29 = var27 * d4;
            float var31 = MathHelper.cos(f3);
            float var32 = MathHelper.sin(f3);
            d += MathHelper.cos(f2) * var31;
            d2 += var32;
            d3 += MathHelper.sin(f2) * var31;
            if (var26) {
                f3 *= 0.92F;
            } else {
                f3 *= 0.7F;
            }

            f3 += var22 * 0.1F;
            f2 += var21 * 0.1F;
            var22 *= 0.9F;
            var21 *= 0.75F;
            var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
            var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;
            if (!var54 && i3 == var25 && f > 1.0F) {
                this.addTunnel(i, i2, tiles, d, d2, d3, var23.nextFloat() * 0.5F + 0.5F, f2 - (float) (Math.PI / 2), f3 / 3.0F, i3, i4, 1.0);
                this.addTunnel(i, i2, tiles, d, d2, d3, var23.nextFloat() * 0.5F + 0.5F, f2 + (float) (Math.PI / 2), f3 / 3.0F, i3, i4, 1.0);
                return;
            }

            if (var54 || var23.nextInt(4) != 0) {
                double var33 = d - var17;
                double var35 = d3 - var19;
                double var37 = i4 - i3;
                double var39 = f + 2.0F + 16.0F;
                if (var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39) {
                    return;
                }

                if (!(d < var17 - 16.0 - var27 * 2.0)
                        && !(d3 < var19 - 16.0 - var27 * 2.0)
                        && !(d > var17 + 16.0 + var27 * 2.0)
                        && !(d3 > var19 + 16.0 + var27 * 2.0)) {
                    int var55 = MathHelper.floor(d - var27) - i * 16 - 1;
                    int var34 = MathHelper.floor(d + var27) - i * 16 + 1;
                    int var56 = MathHelper.floor(d2 - var29) - 1;
                    int var36 = MathHelper.floor(d2 + var29) + 1;
                    int var57 = MathHelper.floor(d3 - var27) - i2 * 16 - 1;
                    int var38 = MathHelper.floor(d3 + var27) - i2 * 16 + 1;
                    if (var55 < 0) {
                        var55 = 0;
                    }

                    if (var34 > 16) {
                        var34 = 16;
                    }

                    if (var56 < 1) {
                        var56 = 1;
                    }

                    if (var36 > 128 - 8) {
                        var36 = 128 - 8;
                    }

                    if (var57 < 0) {
                        var57 = 0;
                    }

                    if (var38 > 16) {
                        var38 = 16;
                    }

                    boolean var58 = false;

                    for (int var40 = var55; !var58 && var40 < var34; var40++) {
                        for (int var41 = var57; !var58 && var41 < var38; var41++) {
                            for (int var42 = var36 + 1; !var58 && var42 >= var56 - 1; var42--) {
                                int var43 = (var40 * 16 + var41) * 128 + var42;
                                if (var42 >= 0 && var42 < 128) {
                                    if (tiles[var43] == Tile.FLOWING_LAVA.id || tiles[var43] == Tile.LAVA.id) {
                                        var58 = true;
                                    }

                                    if (var42 != var56 - 1 && var40 != var55 && var40 != var34 - 1 && var41 != var57 && var41 != var38 - 1) {
                                        var42 = var56;
                                    }
                                }
                            }
                        }
                    }

                    if (!var58) {
                        for (int var59 = var55; var59 < var34; var59++) {
                            double var60 = (var59 + i * 16 + 0.5 - d) / var27;

                            for (int var61 = var57; var61 < var38; var61++) {
                                double var44 = (var61 + i2 * 16 + 0.5 - d3) / var27;
                                int var46 = (var59 * 16 + var61) * 128 + var36;

                                for (int var47 = var36 - 1; var47 >= var56; var47--) {
                                    double var48 = (var47 + 0.5 - d2) / var29;
                                    if (var48 > -0.7 && var60 * var60 + var48 * var48 + var44 * var44 < 1.0) {
                                        byte var50 = tiles[var46];
                                        if (var50 == Tile.NETHERRACK.id || var50 == Tile.DIRT.id || var50 == Tile.GRASS.id) {
                                            tiles[var46] = 0;
                                        }
                                    }

                                    var46--;
                                }
                            }
                        }

                        if (var54) {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void addFeature(Level level, int i, int i2, int i3, int i4, byte[] byteArray) {
        int var7 = this.random.nextInt(this.random.nextInt(this.random.nextInt(10) + 1) + 1);
        if (this.random.nextInt(5) != 0) {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; var8++) {
            double var9 = i * 16 + this.random.nextInt(16);
            double var11 = this.random.nextInt(128);
            double var13 = i2 * 16 + this.random.nextInt(16);
            int var15 = 1;
            if (this.random.nextInt(4) == 0) {
                this.addRoom(i3, i4, byteArray, var9, var11, var13);
                var15 += this.random.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; var16++) {
                float var17 = this.random.nextFloat() * (float) Math.PI * 2.0F;
                float var18 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.random.nextFloat() * 2.0F + this.random.nextFloat();
                this.addTunnel(i3, i4, byteArray, var9, var11, var13, var19 * 2.0F, var17, var18, 0, 0, 0.5);
            }
        }
    }
}
