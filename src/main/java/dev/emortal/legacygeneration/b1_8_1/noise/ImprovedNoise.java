package dev.emortal.legacygeneration.b1_8_1.noise;

import java.util.Random;

public class ImprovedNoise extends Synth {
    private final int[] p = new int[512];
    public double xo;
    public double yo;
    public double zo;

    public ImprovedNoise() {
        this(new Random());
    }

    public ImprovedNoise(Random random) {
        this.xo = random.nextDouble() * 256.0;
        this.yo = random.nextDouble() * 256.0;
        this.zo = random.nextDouble() * 256.0;
        int var2 = 0;

        while (var2 < 256) {
            this.p[var2] = var2++;
        }

        for (int var5 = 0; var5 < 256; var5++) {
            int var3 = random.nextInt(256 - var5) + var5;
            int var4 = this.p[var5];
            this.p[var5] = this.p[var3];
            this.p[var3] = var4;
            this.p[var5 + 256] = this.p[var5];
        }
    }

    public double noise(double x, double y, double z) {
        double var7 = x + this.xo;
        double var9 = y + this.yo;
        double var11 = z + this.zo;
        int var13 = (int) var7;
        int var14 = (int) var9;
        int var15 = (int) var11;
        if (var7 < var13) {
            var13--;
        }

        if (var9 < var14) {
            var14--;
        }

        if (var11 < var15) {
            var15--;
        }

        int var16 = var13 & 0xFF;
        int var17 = var14 & 0xFF;
        int var18 = var15 & 0xFF;
        var7 -= var13;
        var9 -= var14;
        var11 -= var15;
        double var19 = var7 * var7 * var7 * (var7 * (var7 * 6.0 - 15.0) + 10.0);
        double var21 = var9 * var9 * var9 * (var9 * (var9 * 6.0 - 15.0) + 10.0);
        double var23 = var11 * var11 * var11 * (var11 * (var11 * 6.0 - 15.0) + 10.0);
        int var25 = this.p[var16] + var17;
        int var26 = this.p[var25] + var18;
        int var27 = this.p[var25 + 1] + var18;
        int var28 = this.p[var16 + 1] + var17;
        int var29 = this.p[var28] + var18;
        int var30 = this.p[var28 + 1] + var18;
        return this.lerp(
                var23,
                this.lerp(
                        var21,
                        this.lerp(var19, this.grad(this.p[var26], var7, var9, var11), this.grad(this.p[var29], var7 - 1.0, var9, var11)),
                        this.lerp(var19, this.grad(this.p[var27], var7, var9 - 1.0, var11), this.grad(this.p[var30], var7 - 1.0, var9 - 1.0, var11))
                ),
                this.lerp(
                        var21,
                        this.lerp(var19, this.grad(this.p[var26 + 1], var7, var9, var11 - 1.0), this.grad(this.p[var29 + 1], var7 - 1.0, var9, var11 - 1.0)),
                        this.lerp(var19, this.grad(this.p[var27 + 1], var7, var9 - 1.0, var11 - 1.0), this.grad(this.p[var30 + 1], var7 - 1.0, var9 - 1.0, var11 - 1.0))
                )
        );
    }

    public final double lerp(double value, double start, double end) {
        return start + value * (end - start);
    }

    public final double grad2(int i, double d, double d2) {
        int var6 = i & 15;
        double var7 = (1 - ((var6 & 8) >> 3)) * d;
        double var9 = var6 < 4 ? 0.0 : (var6 != 12 && var6 != 14 ? d2 : d);
        return ((var6 & 1) == 0 ? var7 : -var7) + ((var6 & 2) == 0 ? var9 : -var9);
    }

    public final double grad(int i, double d, double d2, double d3) {
        int var8 = i & 15;
        double var9 = var8 < 8 ? d : d2;
        double var11 = var8 < 4 ? d2 : (var8 != 12 && var8 != 14 ? d3 : d);
        return ((var8 & 1) == 0 ? var9 : -var9) + ((var8 & 2) == 0 ? var11 : -var11);
    }

    public double getValue(double x, double y) {
        return this.noise(x, y, 0.0);
    }

    public void add(double[] doubleArray, double d, double d2, double d3, int i, int i2, int i3, double d4, double d5, double d6, double d7) {
        if (i2 == 1) {
            int var64 = 0;
            int var66 = 0;
            int var21 = 0;
            int var69 = 0;
            double var72 = 0.0;
            double var76 = 0.0;
            int var80 = 0;
            double var82 = 1.0 / d7;

            for (int var30 = 0; var30 < i; var30++) {
                double var83 = d + var30 * d4 + this.xo;
                int var85 = (int) var83;
                if (var83 < var85) {
                    var85--;
                }

                int var34 = var85 & 0xFF;
                var83 -= var85;
                double var86 = var83 * var83 * var83 * (var83 * (var83 * 6.0 - 15.0) + 10.0);

                for (int var87 = 0; var87 < i3; var87++) {
                    double var89 = d3 + var87 * d6 + this.zo;
                    int var91 = (int) var89;
                    if (var89 < var91) {
                        var91--;
                    }

                    int var92 = var91 & 0xFF;
                    var89 -= var91;
                    double var93 = var89 * var89 * var89 * (var89 * (var89 * 6.0 - 15.0) + 10.0);
                    var64 = this.p[var34];
                    var66 = this.p[var64] + var92;
                    var21 = this.p[var34 + 1];
                    var69 = this.p[var21] + var92;
                    var72 = this.lerp(var86, this.grad2(this.p[var66], var83, var89), this.grad(this.p[var69], var83 - 1.0, 0.0, var89));
                    var76 = this.lerp(var86, this.grad(this.p[var66 + 1], var83, 0.0, var89 - 1.0), this.grad(this.p[var69 + 1], var83 - 1.0, 0.0, var89 - 1.0));
                    double var94 = this.lerp(var93, var72, var76);
                    doubleArray[var80++] += var94 * var82;
                }
            }
        } else {
            int var19 = 0;
            double var20 = 1.0 / d7;
            int var22 = -1;
            int var23 = 0;
            int var24 = 0;
            int var25 = 0;
            int var26 = 0;
            int var27 = 0;
            int var28 = 0;
            double var29 = 0.0;
            double var31 = 0.0;
            double var33 = 0.0;
            double var35 = 0.0;

            for (int var37 = 0; var37 < i; var37++) {
                double var38 = d + var37 * d4 + this.xo;
                int var40 = (int) var38;
                if (var38 < var40) {
                    var40--;
                }

                int var41 = var40 & 0xFF;
                var38 -= var40;
                double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0 - 15.0) + 10.0);

                for (int var44 = 0; var44 < i3; var44++) {
                    double var45 = d3 + var44 * d6 + this.zo;
                    int var47 = (int) var45;
                    if (var45 < var47) {
                        var47--;
                    }

                    int var48 = var47 & 0xFF;
                    var45 -= var47;
                    double var49 = var45 * var45 * var45 * (var45 * (var45 * 6.0 - 15.0) + 10.0);

                    for (int var51 = 0; var51 < i2; var51++) {
                        double var52 = d2 + var51 * d5 + this.yo;
                        int var54 = (int) var52;
                        if (var52 < var54) {
                            var54--;
                        }

                        int var55 = var54 & 0xFF;
                        var52 -= var54;
                        double var56 = var52 * var52 * var52 * (var52 * (var52 * 6.0 - 15.0) + 10.0);
                        if (var51 == 0 || var55 != var22) {
                            var22 = var55;
                            var23 = this.p[var41] + var55;
                            var24 = this.p[var23] + var48;
                            var25 = this.p[var23 + 1] + var48;
                            var26 = this.p[var41 + 1] + var55;
                            var27 = this.p[var26] + var48;
                            var28 = this.p[var26 + 1] + var48;
                            var29 = this.lerp(var42, this.grad(this.p[var24], var38, var52, var45), this.grad(this.p[var27], var38 - 1.0, var52, var45));
                            var31 = this.lerp(var42, this.grad(this.p[var25], var38, var52 - 1.0, var45), this.grad(this.p[var28], var38 - 1.0, var52 - 1.0, var45));
                            var33 = this.lerp(
                                    var42, this.grad(this.p[var24 + 1], var38, var52, var45 - 1.0), this.grad(this.p[var27 + 1], var38 - 1.0, var52, var45 - 1.0)
                            );
                            var35 = this.lerp(
                                    var42,
                                    this.grad(this.p[var25 + 1], var38, var52 - 1.0, var45 - 1.0),
                                    this.grad(this.p[var28 + 1], var38 - 1.0, var52 - 1.0, var45 - 1.0)
                            );
                        }

                        double var58 = this.lerp(var56, var29, var31);
                        double var60 = this.lerp(var56, var33, var35);
                        double var62 = this.lerp(var49, var58, var60);
                        doubleArray[var19++] += var62 * var20;
                    }
                }
            }
        }
    }
}
