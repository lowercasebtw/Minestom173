package dev.emortal.legacygeneration.b1_8_1.noise;

import dev.emortal.legacygeneration.util.MathHelper;

import java.util.Random;

public class PerlinNoise extends Synth {
    private final ImprovedNoise[] noiseLevels;
    private final int levels;

    public PerlinNoise(Random random, int levels) {
        this.levels = levels;
        this.noiseLevels = new ImprovedNoise[levels];

        for (int var3 = 0; var3 < levels; var3++) {
            this.noiseLevels[var3] = new ImprovedNoise(random);
        }
    }

    public double getValue(double d, double d2) {
        double var5 = 0.0;
        double var7 = 1.0;

        for (int var9 = 0; var9 < this.levels; var9++) {
            var5 += this.noiseLevels[var9].getValue(d * var7, d2 * var7) / var7;
            var7 /= 2.0;
        }

        return var5;
    }

    public double[] getRegion(double[] doubleArray, int i, int i2, int i3, int i4, int i5, int i6, double d, double d2, double d3) {
        if (doubleArray == null) {
            doubleArray = new double[i4 * i5 * i6];
        } else {
            for (int var14 = 0; var14 < doubleArray.length; var14++) {
                doubleArray[var14] = 0.0;
            }
        }

        double var27 = 1.0;

        for (int var16 = 0; var16 < this.levels; var16++) {
            double var17 = i * var27 * d;
            double var19 = i2 * var27 * d2;
            double var21 = i3 * var27 * d3;
            long var23 = MathHelper.floorLong(var17);
            long var25 = MathHelper.floorLong(var21);
            var17 -= var23;
            var21 -= var25;
            var23 %= 16777216L;
            var25 %= 16777216L;
            var17 += var23;
            var21 += var25;
            this.noiseLevels[var16].add(doubleArray, var17, var19, var21, i4, i5, i6, d * var27, d2 * var27, d3 * var27, var27);
            var27 /= 2.0;
        }

        return doubleArray;
    }

    public double[] getRegion(double[] doubleArray, int i, int i2, int i3, int i4, double d, double d2, double d3) {
        return this.getRegion(doubleArray, i, 10, i2, i3, 1, i4, d, 1.0, d2);
    }
}
