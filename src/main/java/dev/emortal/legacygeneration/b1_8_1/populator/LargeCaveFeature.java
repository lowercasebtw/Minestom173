package dev.emortal.legacygeneration.b1_8_1.populator;

import java.util.Random;

public class LargeCaveFeature extends LargeFeature {
    protected void addRoom(long j, int i, int i2, byte[] byteArray, double d, double d2, double d3) {
        this.addTunnel(j, i, i2, byteArray, d, d2, d3, 1.0F + this.random.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5);
    }

    protected void addTunnel(long j, int i, int i2, byte[] byteArray, double d, double d2, double d3, float f, float f2, float f3, int i3, int i4, double d4) {
        double var19 = i * 16 + 8;
        double var21 = i2 * 16 + 8;
        float var23 = 0.0F;
        float var24 = 0.0F;
        Random var25 = new Random(j);
        if (i4 <= 0) {
            int var26 = this.radius * 16 - 16;
            i4 = var26 - var25.nextInt(var26 / 4);
        }

        boolean var57 = false;
        if (i3 == -1) {
            i3 = i4 / 2;
            var57 = true;
        }

        int var27 = var25.nextInt(i4 / 2) + i4 / 4;

        for (boolean var28 = var25.nextInt(6) == 0; i3 < i4; i3++) {
            double var29 = 1.5 + MathHelper.sin(i3 * (float) Math.PI / i4) * f * 1.0F;
            double var31 = var29 * d4;
            float var33 = MathHelper.cos(f3);
            float var34 = MathHelper.sin(f3);
            d += MathHelper.cos(f2) * var33;
            d2 += var34;
            d3 += MathHelper.sin(f2) * var33;
            if (var28) {
                f3 *= 0.92F;
            } else {
                f3 *= 0.7F;
            }

            f3 += var24 * 0.1F;
            f2 += var23 * 0.1F;
            var24 *= 0.9F;
            var23 *= 0.75F;
            var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
            var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
            if (!var57 && i3 == var27 && f > 1.0F && i4 > 0) {
                this.addTunnel(var25.nextLong(), i, i2, byteArray, d, d2, d3, var25.nextFloat() * 0.5F + 0.5F, f2 - (float) (Math.PI / 2), f3 / 3.0F, i3, i4, 1.0);
                this.addTunnel(var25.nextLong(), i, i2, byteArray, d, d2, d3, var25.nextFloat() * 0.5F + 0.5F, f2 + (float) (Math.PI / 2), f3 / 3.0F, i3, i4, 1.0);
                return;
            }

            if (var57 || var25.nextInt(4) != 0) {
                double var35 = d - var19;
                double var37 = d3 - var21;
                double var39 = i4 - i3;
                double var41 = f + 2.0F + 16.0F;
                if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
                    return;
                }

                if (!(d < var19 - 16.0 - var29 * 2.0)
                        && !(d3 < var21 - 16.0 - var29 * 2.0)
                        && !(d > var19 + 16.0 + var29 * 2.0)
                        && !(d3 > var21 + 16.0 + var29 * 2.0)) {
                    int var58 = MathHelper.floor(d - var29) - i * 16 - 1;
                    int var36 = MathHelper.floor(d + var29) - i * 16 + 1;
                    int var59 = MathHelper.floor(d2 - var31) - 1;
                    int var38 = MathHelper.floor(d2 + var31) + 1;
                    int var60 = MathHelper.floor(d3 - var29) - i2 * 16 - 1;
                    int var40 = MathHelper.floor(d3 + var29) - i2 * 16 + 1;
                    if (var58 < 0) {
                        var58 = 0;
                    }

                    if (var36 > 16) {
                        var36 = 16;
                    }

                    if (var59 < 1) {
                        var59 = 1;
                    }

                    if (var38 > 128 - 8) {
                        var38 = 128 - 8;
                    }

                    if (var60 < 0) {
                        var60 = 0;
                    }

                    if (var40 > 16) {
                        var40 = 16;
                    }

                    boolean var61 = false;

                    for (int var42 = var58; !var61 && var42 < var36; var42++) {
                        for (int var43 = var60; !var61 && var43 < var40; var43++) {
                            for (int var44 = var38 + 1; !var61 && var44 >= var59 - 1; var44--) {
                                int var45 = (var42 * 16 + var43) * 128 + var44;
                                if (var44 >= 0 && var44 < 128) {
                                    if (byteArray[var45] == Tile.FLOWING_WATER.id || byteArray[var45] == Tile.WATER.id) {
                                        var61 = true;
                                    }

                                    if (var44 != var59 - 1 && var42 != var58 && var42 != var36 - 1 && var43 != var60 && var43 != var40 - 1) {
                                        var44 = var59;
                                    }
                                }
                            }
                        }
                    }

                    if (!var61) {
                        for (int var62 = var58; var62 < var36; var62++) {
                            double var63 = (var62 + i * 16 + 0.5 - d) / var29;

                            for (int var64 = var60; var64 < var40; var64++) {
                                double var46 = (var64 + i2 * 16 + 0.5 - d3) / var29;
                                int var48 = (var62 * 16 + var64) * 128 + var38;
                                boolean var49 = false;
                                if (var63 * var63 + var46 * var46 < 1.0) {
                                    for (int var50 = var38 - 1; var50 >= var59; var50--) {
                                        double var51 = (var50 + 0.5 - d2) / var31;
                                        if (var51 > -0.7 && var63 * var63 + var51 * var51 + var46 * var46 < 1.0) {
                                            byte var53 = byteArray[var48];
                                            if (var53 == Tile.GRASS.id) {
                                                var49 = true;
                                            }

                                            if (var53 == Tile.STONE.id || var53 == Tile.DIRT.id || var53 == Tile.GRASS.id) {
                                                if (var50 < 10) {
                                                    byteArray[var48] = (byte) Tile.FLOWING_LAVA.id;
                                                } else {
                                                    byteArray[var48] = 0;
                                                    if (var49 && byteArray[var48 - 1] == Tile.DIRT.id) {
                                                        byteArray[var48 - 1] = (byte) Tile.GRASS.id;
                                                    }
                                                }
                                            }
                                        }

                                        var48--;
                                    }
                                }
                            }
                        }

                        if (var57) {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void addFeature(Level level, int i, int i2, int i3, int i4, byte[] byteArray) {
        int var7 = this.random.nextInt(this.random.nextInt(this.random.nextInt(40) + 1) + 1);
        if (this.random.nextInt(15) != 0) {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; var8++) {
            double var9 = i * 16 + this.random.nextInt(16);
            double var11 = this.random.nextInt(this.random.nextInt(128 - 8) + 8);
            double var13 = i2 * 16 + this.random.nextInt(16);
            int var15 = 1;
            if (this.random.nextInt(4) == 0) {
                this.addRoom(this.random.nextLong(), i3, i4, byteArray, var9, var11, var13);
                var15 += this.random.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; var16++) {
                float var17 = this.random.nextFloat() * (float) Math.PI * 2.0F;
                float var18 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.random.nextFloat() * 2.0F + this.random.nextFloat();
                if (this.random.nextInt(10) == 0) {
                    var19 *= this.random.nextFloat() * this.random.nextFloat() * 3.0F + 1.0F;
                }

                this.addTunnel(this.random.nextLong(), i3, i4, byteArray, var9, var11, var13, var19, var17, var18, 0, 0, 1.0);
            }
        }
    }
}
