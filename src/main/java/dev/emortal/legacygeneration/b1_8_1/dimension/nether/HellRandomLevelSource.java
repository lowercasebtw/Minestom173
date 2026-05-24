package dev.emortal.legacygeneration.b1_8_1.dimension.nether;

import dev.emortal.legacygeneration.b1_8_1.ChunkGenerator181;
import dev.emortal.legacygeneration.b1_8_1.noise.PerlinNoise;
import dev.emortal.legacygeneration.b1_8_1.populator.LargeFeature;
import dev.emortal.legacygeneration.b1_8_1.populator.LargeHellCaveFeature;
import net.minestom.server.instance.ChunkLoader;

import java.util.Random;

public class HellRandomLevelSource implements ChunkLoader, ChunkGenerator181 {
    private final Random random;
    private final PerlinNoise lperlinNoise1;
    private final PerlinNoise lperlinNoise2;
    private final PerlinNoise perlinNoise1;
    private final PerlinNoise perlinNoise2;
    private final PerlinNoise perlinNoise3;
    private final Level level;
    private final LargeFeature caveFeature = new LargeHellCaveFeature();
    public PerlinNoise scaleNoise;
    public PerlinNoise depthNoise;
    double[] pnr;
    double[] ar;
    double[] br;
    double[] sr;
    double[] dr;
    private double[] buffer;
    private double[] sandBuffer = new double[256];
    private double[] gravelBuffer = new double[256];
    private double[] depthBuffer = new double[256];

    public HellRandomLevelSource(Level level, long seed) {
        this.level = level;
        this.random = new Random(seed);
        this.lperlinNoise1 = new PerlinNoise(this.random, 16);
        this.lperlinNoise2 = new PerlinNoise(this.random, 16);
        this.perlinNoise1 = new PerlinNoise(this.random, 8);
        this.perlinNoise2 = new PerlinNoise(this.random, 4);
        this.perlinNoise3 = new PerlinNoise(this.random, 4);
        this.scaleNoise = new PerlinNoise(this.random, 10);
        this.depthNoise = new PerlinNoise(this.random, 16);
    }

    public void prepareHeights(int x, int z, byte[] tiles) {
        byte var4 = 4;
        byte var5 = 32;
        int var6 = var4 + 1;
        int var7 = 128 / 8 + 1;
        int var8 = var4 + 1;
        this.buffer = this.getHeights(this.buffer, x * var4, 0, z * var4, var6, var7, var8);

        for (int var9 = 0; var9 < var4; var9++) {
            for (int var10 = 0; var10 < var4; var10++) {
                for (int var11 = 0; var11 < 128 / 8; var11++) {
                    double var12 = 0.125;
                    double var14 = this.buffer[((var9) * var8 + var10) * var7 + var11];
                    double var16 = this.buffer[((var9) * var8 + var10 + 1) * var7 + var11];
                    double var18 = this.buffer[((var9 + 1) * var8 + var10) * var7 + var11];
                    double var20 = this.buffer[((var9 + 1) * var8 + var10 + 1) * var7 + var11];
                    double var22 = (this.buffer[((var9) * var8 + var10) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.buffer[((var9) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.buffer[((var9 + 1) * var8 + var10) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.buffer[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 8; var30++) {
                        double var31 = 0.25;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 4; var41++) {
                            int var42 = var41 + var9 * 4 << 11 | var10 * 4 << 7 | var11 * 8 + var30;
                            int var43 = 1 << 7;
                            double var44 = 0.25;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 4; var50++) {
                                int var51 = 0;
                                if (var11 * 8 + var30 < var5) {
                                    var51 = Tile.LAVA.id;
                                }

                                if (var46 > 0.0) {
                                    var51 = Tile.NETHERRACK.id;
                                }

                                tiles[var42] = (byte) var51;
                                var42 += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }
                }
            }
        }
    }

    public void buildSurfaces(int i, int i2, byte[] byteArray) {
        int var4 = 128 - 64;
        double var5 = 0.03125;
        this.sandBuffer = this.perlinNoise2.getRegion(this.sandBuffer, i * 16, i2 * 16, 0, 16, 16, 1, var5, var5, 1.0);
        this.gravelBuffer = this.perlinNoise2.getRegion(this.gravelBuffer, i * 16, 109, i2 * 16, 16, 1, 16, var5, 1.0, var5);
        this.depthBuffer = this.perlinNoise3.getRegion(this.depthBuffer, i * 16, i2 * 16, 0, 16, 16, 1, var5 * 2.0, var5 * 2.0, var5 * 2.0);

        for (int var7 = 0; var7 < 16; var7++) {
            for (int var8 = 0; var8 < 16; var8++) {
                boolean var9 = this.sandBuffer[var7 + var8 * 16] + this.random.nextDouble() * 0.2 > 0.0;
                boolean var10 = this.gravelBuffer[var7 + var8 * 16] + this.random.nextDouble() * 0.2 > 0.0;
                int var11 = (int) (this.depthBuffer[var7 + var8 * 16] / 3.0 + 3.0 + this.random.nextDouble() * 0.25);
                int var12 = -1;
                byte var13 = (byte) Tile.NETHERRACK.id;
                byte var14 = (byte) Tile.NETHERRACK.id;

                for (int var15 = 127; var15 >= 0; var15--) {
                    int var16 = (var8 * 16 + var7) * 128 + var15;
                    if (var15 >= 127 - this.random.nextInt(5)) {
                        byteArray[var16] = (byte) Tile.BEDROCK.id;
                    } else if (var15 <= this.random.nextInt(5)) {
                        byteArray[var16] = (byte) Tile.BEDROCK.id;
                    } else {
                        byte var17 = byteArray[var16];
                        if (var17 == 0) {
                            var12 = -1;
                        } else if (var17 == Tile.NETHERRACK.id) {
                            if (var12 == -1) {
                                if (var11 <= 0) {
                                    var13 = 0;
                                    var14 = (byte) Tile.NETHERRACK.id;
                                } else if (var15 >= var4 - 4 && var15 <= var4 + 1) {
                                    var13 = (byte) Tile.NETHERRACK.id;
                                    var14 = (byte) Tile.NETHERRACK.id;
                                    if (var10) {
                                        var13 = (byte) Tile.GRAVEL.id;
                                    }

                                    if (var10) {
                                        var14 = (byte) Tile.NETHERRACK.id;
                                    }

                                    if (var9) {
                                        var13 = (byte) Tile.SOUL_SAND.id;
                                    }

                                    if (var9) {
                                        var14 = (byte) Tile.SOUL_SAND.id;
                                    }
                                }

                                if (var15 < var4 && var13 == 0) {
                                    var13 = (byte) Tile.LAVA.id;
                                }

                                var12 = var11;
                                if (var15 >= var4 - 1) {
                                    byteArray[var16] = var13;
                                } else {
                                    byteArray[var16] = var14;
                                }
                            } else if (var12 > 0) {
                                var12--;
                                byteArray[var16] = var14;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public LevelChunk loadChunk(int i, int i2) {
        return this.getChunk(i, i2);
    }

    @Override
    public LevelChunk getChunk(int i, int i2) {
        this.random.setSeed(i * 341873128712L + i2 * 132897987541L);
        byte[] var3 = new byte[16 * 128 * 16];
        this.prepareHeights(i, i2, var3);
        this.buildSurfaces(i, i2, var3);
        this.caveFeature.apply(this, this.level, i, i2, var3);
        return new LevelChunk(this.level, var3, i, i2);
    }

    private double[] getHeights(double[] doubleArray, int i, int i2, int i3, int i4, int i5, int i6) {
        if (doubleArray == null) {
            doubleArray = new double[i4 * i5 * i6];
        }

        double var8 = 684.412;
        double var10 = 2053.236;
        this.sr = this.scaleNoise.getRegion(this.sr, i, i2, i3, i4, 1, i6, 1.0, 0.0, 1.0);
        this.dr = this.depthNoise.getRegion(this.dr, i, i2, i3, i4, 1, i6, 100.0, 0.0, 100.0);
        this.pnr = this.perlinNoise1.getRegion(this.pnr, i, i2, i3, i4, i5, i6, var8 / 80.0, var10 / 60.0, var8 / 80.0);
        this.ar = this.lperlinNoise1.getRegion(this.ar, i, i2, i3, i4, i5, i6, var8, var10, var8);
        this.br = this.lperlinNoise2.getRegion(this.br, i, i2, i3, i4, i5, i6, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;
        double[] var14 = new double[i5];

        for (int var15 = 0; var15 < i5; var15++) {
            var14[var15] = Math.cos(var15 * Math.PI * 6.0 / i5) * 2.0;
            double var16 = var15;
            if (var15 > i5 / 2) {
                var16 = i5 - 1 - var15;
            }

            if (var16 < 4.0) {
                var16 = 4.0 - var16;
                var14[var15] -= var16 * var16 * var16 * 10.0;
            }
        }

        for (int var36 = 0; var36 < i4; var36++) {
            for (int var38 = 0; var38 < i6; var38++) {
                double var17 = (this.sr[var13] + 256.0) / 512.0;
                if (var17 > 1.0) {
                    var17 = 1.0;
                }

                double var19 = 0.0;
                double var21 = this.dr[var13] / 8000.0;
                if (var21 < 0.0) {
                    var21 = -var21;
                }

                var21 = var21 * 3.0 - 3.0;
                if (var21 < 0.0) {
                    var21 /= 2.0;
                    if (var21 < -1.0) {
                        var21 = -1.0;
                    }

                    var21 /= 1.4;
                    var21 /= 2.0;
                    var17 = 0.0;
                } else {
                    if (var21 > 1.0) {
                        var21 = 1.0;
                    }

                    var21 /= 6.0;
                }

                var17 += 0.5;
                var21 = var21 * i5 / 16.0;
                var13++;

                for (int var23 = 0; var23 < i5; var23++) {
                    double var24 = 0.0;
                    double var26 = var14[var23];
                    double var28 = this.ar[var12] / 512.0;
                    double var30 = this.br[var12] / 512.0;
                    double var32 = (this.pnr[var12] / 10.0 + 1.0) / 2.0;
                    if (var32 < 0.0) {
                        var24 = var28;
                    } else if (var32 > 1.0) {
                        var24 = var30;
                    } else {
                        var24 = var28 + (var30 - var28) * var32;
                    }

                    var24 -= var26;
                    if (var23 > i5 - 4) {
                        double var34 = (var23 - (i5 - 4)) / 3.0F;
                        var24 = var24 * (1.0 - var34) + -10.0 * var34;
                    }

                    if (var23 < var19) {
                        double var47 = (var19 - var23) / 4.0;
                        if (var47 < 0.0) {
                            var47 = 0.0;
                        }

                        if (var47 > 1.0) {
                            var47 = 1.0;
                        }

                        var24 = var24 * (1.0 - var47) + -10.0 * var47;
                    }

                    doubleArray[var12] = var24;
                    var12++;
                }
            }
        }

        return doubleArray;
    }

    @Override
    public boolean hasChunk(int i, int i2) {
        return true;
    }

    @Override
    public void postProcess(ChunkSource chunkSource, int i, int i2) {
        SandTile.instaFall = true;
        int var4 = i * 16;
        int var5 = i2 * 16;

        for (int var6 = 0; var6 < 8; var6++) {
            int var7 = var4 + this.random.nextInt(16) + 8;
            int var8 = this.random.nextInt(128 - 8) + 4;
            int var9 = var5 + this.random.nextInt(16) + 8;
            new HellSpringFeature(Tile.FLOWING_LAVA.id).place(this.level, this.random, var7, var8, var9);
        }

        int var11 = this.random.nextInt(this.random.nextInt(10) + 1) + 1;

        for (int var13 = 0; var13 < var11; var13++) {
            int var18 = var4 + this.random.nextInt(16) + 8;
            int var23 = this.random.nextInt(128 - 8) + 4;
            int var10 = var5 + this.random.nextInt(16) + 8;
            new HellFireFeature().place(this.level, this.random, var18, var23, var10);
        }

        var11 = this.random.nextInt(this.random.nextInt(10) + 1);

        for (int var14 = 0; var14 < var11; var14++) {
            int var19 = var4 + this.random.nextInt(16) + 8;
            int var24 = this.random.nextInt(128 - 8) + 4;
            int var28 = var5 + this.random.nextInt(16) + 8;
            new LightGemFeature().place(this.level, this.random, var19, var24, var28);
        }

        for (int var15 = 0; var15 < 10; var15++) {
            int var20 = var4 + this.random.nextInt(16) + 8;
            int var25 = this.random.nextInt(128);
            int var29 = var5 + this.random.nextInt(16) + 8;
            new HellPortalFeature().place(this.level, this.random, var20, var25, var29);
        }

        if (this.random.nextInt(1) == 0) {
            int var16 = var4 + this.random.nextInt(16) + 8;
            int var21 = this.random.nextInt(128);
            int var26 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Tile.BROWN_MUSHROOM.id).place(this.level, this.random, var16, var21, var26);
        }

        if (this.random.nextInt(1) == 0) {
            int var17 = var4 + this.random.nextInt(16) + 8;
            int var22 = this.random.nextInt(128);
            int var27 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Tile.RED_MUSHROOM.id).place(this.level, this.random, var17, var22, var27);
        }

        SandTile.instaFall = false;
    }

    @Override
    public boolean save(boolean z, ProgressListener progressListener) {
        return true;
    }

    @Override
    public boolean tick() {
        return false;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    @Override
    public String gatherStats() {
        return "HellRandomLevelSource";
    }
}
