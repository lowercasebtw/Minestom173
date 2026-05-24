package dev.emortal.legacygeneration.b1_7_3.noise;

import java.util.Arrays;
import java.util.Random;

public class NoiseGeneratorOctaves2 {
    private final NoiseGenerator2[] noiseGenerators;
    private final int totalNoiseGenerators;

    public NoiseGeneratorOctaves2(final Random random, final int totalGenerators) {
        this.totalNoiseGenerators = totalGenerators;
        this.noiseGenerators = new NoiseGenerator2[totalGenerators];
        for (int j = 0; j < totalGenerators; ++j) {
            this.noiseGenerators[j] = new NoiseGenerator2(random);
        }
    }

    public double[] generateNoise(double[] adouble, double d0, double d1, int i, int j, double d2, double d3, double d4, double d5) {
        d2 /= 1.5D;
        d3 /= 1.5D;
        if (adouble != null && adouble.length >= i * j) {
            Arrays.fill(adouble, 0.0D);
        } else {
            adouble = new double[i * j];
        }

        double d6 = 1.0D;
        double d7 = 1.0D;
        for (int l = 0; l < this.totalNoiseGenerators; ++l) {
            this.noiseGenerators[l].a(adouble, d0, d1, i, j, d2 * d7, d3 * d7, 0.55D / d6);
            d7 *= d4;
            d6 *= d5;
        }

        return adouble;
    }

    public double[] generateNoise(double[] adouble, double d0, double d1, int i, int j, double d2, double d3, double d4) {
        return this.generateNoise(adouble, d0, d1, i, j, d2, d3, d4, 0.5D);
    }
}
