package dev.emortal.legacygeneration.b1_8_1.dimension.overworld;

import dev.emortal.legacygeneration.b1_8_1.ChunkGenerator181;
import dev.emortal.legacygeneration.b1_8_1.noise.PerlinNoise;
import dev.emortal.legacygeneration.b1_8_1.populator.LargeCaveFeature;
import dev.emortal.legacygeneration.b1_8_1.populator.LargeFeature;
import net.minestom.server.instance.ChunkLoader;

import java.util.Random;

public class RandomLevelSource implements ChunkLoader, ChunkGenerator181 {
    private final boolean f_24778720;
    private final Random random;
    private final PerlinNoise lperlinNoise1;
    private final PerlinNoise lperlinNoise2;
    private final PerlinNoise perlinNoise1;
    private final PerlinNoise perlinNoise3;
    private final Level level;
    private final LargeFeature caveFeature = new LargeCaveFeature();
    private final LargeFeature f_87978136 = new C_66958069();
    public PerlinNoise scaleNoise;
    public PerlinNoise depthNoise;
    public PerlinNoise forestNoise;
    public C_20744923 f_46997617 = new C_20744923();
    public C_06705682 f_70462830 = new C_06705682();
    public C_10007912 f_86759975 = new C_10007912();
    double[] pnr;
    double[] ar;
    double[] br;
    double[] sr;
    double[] dr;
    float[] f_61387144;
    int[][] waterDepths = new int[32][32];
    private double[] buffer;
    private double[] depthBuffer = new double[256];
    private Biome[] biomes;

    public RandomLevelSource(Level level, long j, boolean z) {
        this.level = level;
        this.f_24778720 = z;
        this.random = new Random(j);
        this.lperlinNoise1 = new PerlinNoise(this.random, 16);
        this.lperlinNoise2 = new PerlinNoise(this.random, 16);
        this.perlinNoise1 = new PerlinNoise(this.random, 8);
        this.perlinNoise3 = new PerlinNoise(this.random, 4);
        this.scaleNoise = new PerlinNoise(this.random, 10);
        this.depthNoise = new PerlinNoise(this.random, 16);
        this.forestNoise = new PerlinNoise(this.random, 8);
    }

    public void prepareHeights(int i, int i2, byte[] byteArray) {
        byte var4 = 4;
        int var5 = 128 / 8;
        byte var6 = 63;
        int var7 = var4 + 1;
        int var8 = 128 / 8 + 1;
        int var9 = var4 + 1;
        this.biomes = this.level.getBiomeSource().m_07201083(this.biomes, i * 4 - 2, i2 * 4 - 2, var7 + 5, var9 + 5);
        this.buffer = this.getHeights(this.buffer, i * var4, 0, i2 * var4, var7, var8, var9);

        for (int var10 = 0; var10 < var4; var10++) {
            for (int var11 = 0; var11 < var4; var11++) {
                for (int var12 = 0; var12 < var5; var12++) {
                    double var13 = 0.125;
                    double var15 = this.buffer[((var10) * var9 + var11) * var8 + var12];
                    double var17 = this.buffer[((var10) * var9 + var11 + 1) * var8 + var12];
                    double var19 = this.buffer[((var10 + 1) * var9 + var11) * var8 + var12];
                    double var21 = this.buffer[((var10 + 1) * var9 + var11 + 1) * var8 + var12];
                    double var23 = (this.buffer[((var10) * var9 + var11) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.buffer[((var10) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.buffer[((var10 + 1) * var9 + var11) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.buffer[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for (int var31 = 0; var31 < 8; var31++) {
                        double var32 = 0.25;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for (int var42 = 0; var42 < 4; var42++) {
                            int var43 = var42 + var10 * 4 << 11 | var11 * 4 << 7 | var12 * 8 + var31;
                            int var44 = 1 << 7;
                            double var45 = 0.25;
                            double var47 = var34;
                            double var49 = (var36 - var34) * var45;

                            for (int var51 = 0; var51 < 4; var51++) {
                                int var52 = 0;
                                if (var12 * 8 + var31 < var6) {
                                    var52 = Tile.WATER.id;
                                }

                                if (var47 > 0.0) {
                                    var52 = Tile.STONE.id;
                                }

                                byteArray[var43] = (byte) var52;
                                var43 += var44;
                                var47 += var49;
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }
    }

    public void buildSurfaces(int x, int z, byte[] tiles, Biome[] biomes) {
        byte var5 = 63;
        double var6 = 0.03125;
        this.depthBuffer = this.perlinNoise3.getRegion(this.depthBuffer, x * 16, z * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);

        for (int var8 = 0; var8 < 16; var8++) {
            for (int var9 = 0; var9 < 16; var9++) {
                Biome var10 = biomes[var9 + var8 * 16];
                int var11 = (int) (this.depthBuffer[var8 + var9 * 16] / 3.0 + 3.0 + this.random.nextDouble() * 0.25);
                int var12 = -1;
                byte var13 = var10.topMaterial;
                byte var14 = var10.material;

                for (int var15 = 127; var15 >= 0; var15--) {
                    int var16 = (var9 * 16 + var8) * 128 + var15;
                    if (var15 <= this.random.nextInt(5)) {
                        tiles[var16] = (byte) Tile.BEDROCK.id;
                    } else {
                        byte var17 = tiles[var16];
                        if (var17 == 0) {
                            var12 = -1;
                        } else if (var17 == Tile.STONE.id) {
                            if (var12 == -1) {
                                if (var11 <= 0) {
                                    var13 = 0;
                                    var14 = (byte) Tile.STONE.id;
                                } else if (var15 >= var5 - 4 && var15 <= var5 + 1) {
                                    var13 = var10.topMaterial;
                                    var14 = var10.material;
                                }

                                if (var15 < var5 && var13 == 0) {
                                    var13 = (byte) Tile.WATER.id;
                                }

                                var12 = var11;
                                if (var15 >= var5 - 1) {
                                    tiles[var16] = var13;
                                } else {
                                    tiles[var16] = var14;
                                }
                            } else if (var12 > 0) {
                                var12--;
                                tiles[var16] = var14;
                                if (var12 == 0 && var14 == Tile.SAND.id) {
                                    var12 = this.random.nextInt(4);
                                    var14 = (byte) Tile.SANDSTONE.id;
                                }
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
        LevelChunk var4 = new LevelChunk(this.level, var3, i, i2);
        this.prepareHeights(i, i2, var3);
        this.biomes = this.level.getBiomeSource().getBiomeBlock(this.biomes, i * 16, i2 * 16, 16, 16);
        this.buildSurfaces(i, i2, var3, this.biomes);
        this.caveFeature.apply(this, this.level, i, i2, var3);
        if (this.f_24778720) {
            this.f_46997617.apply(this, this.level, i, i2, var3);
            this.f_86759975.apply(this, this.level, i, i2, var3);
            this.f_70462830.apply(this, this.level, i, i2, var3);
        }

        this.f_87978136.apply(this, this.level, i, i2, var3);
        var4.recalcHeightmap();
        return var4;
    }

    private double[] getHeights(double[] doubleArray, int i, int i2, int i3, int i4, int i5, int i6) {
        if (doubleArray == null) {
            doubleArray = new double[i4 * i5 * i6];
        }

        if (this.f_61387144 == null) {
            this.f_61387144 = new float[25];

            for (int var8 = -2; var8 <= 2; var8++) {
                for (int var9 = -2; var9 <= 2; var9++) {
                    float var10 = 10.0F / MathHelper.sqrt(var8 * var8 + var9 * var9 + 0.2F);
                    this.f_61387144[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 684.412;
        double var45 = 684.412;
        this.sr = this.scaleNoise.getRegion(this.sr, i, i3, i4, i6, 1.121, 1.121, 0.5);
        this.dr = this.depthNoise.getRegion(this.dr, i, i3, i4, i6, 200.0, 200.0, 0.5);
        this.pnr = this.perlinNoise1.getRegion(this.pnr, i, i2, i3, i4, i5, i6, var44 / 80.0, var45 / 160.0, var44 / 80.0);
        this.ar = this.lperlinNoise1.getRegion(this.ar, i, i2, i3, i4, i5, i6, var44, var45, var44);
        this.br = this.lperlinNoise2.getRegion(this.br, i, i2, i3, i4, i5, i6, var44, var45, var44);
        int var43 = false;
        int var42 = false;
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < i4; var14++) {
            for (int var15 = 0; var15 < i6; var15++) {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;
                Biome var20 = this.biomes[var14 + 2 + (var15 + 2) * (i4 + 5)];

                for (int var21 = -var19; var21 <= var19; var21++) {
                    for (int var22 = -var19; var22 <= var19; var22++) {
                        Biome var23 = this.biomes[var14 + var21 + 2 + (var15 + var22 + 2) * (i4 + 5)];
                        float var24 = this.f_61387144[var21 + 2 + (var22 + 2) * 5] / (var23.f_37907379 + 2.0F);
                        if (var23.f_37907379 > var20.f_37907379) {
                            var24 /= 2.0F;
                        }

                        var16 += var23.f_53999728 * var24;
                        var17 += var23.f_37907379 * var24;
                        var18 += var24;
                    }
                }

                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var50 = this.dr[var13] / 8000.0;
                if (var50 < 0.0) {
                    var50 = -var50 * 0.3;
                }

                var50 = var50 * 3.0 - 2.0;
                if (var50 < 0.0) {
                    var50 /= 2.0;
                    if (var50 < -1.0) {
                        var50 = -1.0;
                    }

                    var50 /= 1.4;
                    var50 /= 2.0;
                } else {
                    if (var50 > 1.0) {
                        var50 = 1.0;
                    }

                    var50 /= 8.0;
                }

                var13++;

                for (int var55 = 0; var55 < i5; var55++) {
                    double var56 = var17;
                    double var26 = var16;
                    var56 += var50 * 0.2;
                    var56 = var56 * i5 / 16.0;
                    double var28 = i5 / 2.0 + var56 * 4.0;
                    double var30 = 0.0;
                    double var32 = (var55 - var28) * 12.0 * 128.0 / 128.0 / var26;
                    if (var32 < 0.0) {
                        var32 *= 4.0;
                    }

                    double var34 = this.ar[var12] / 512.0;
                    double var36 = this.br[var12] / 512.0;
                    double var38 = (this.pnr[var12] / 10.0 + 1.0) / 2.0;
                    if (var38 < 0.0) {
                        var30 = var34;
                    } else if (var38 > 1.0) {
                        var30 = var36;
                    } else {
                        var30 = var34 + (var36 - var34) * var38;
                    }

                    var30 -= var32;
                    if (var55 > i5 - 4) {
                        double var40 = (var55 - (i5 - 4)) / 3.0F;
                        var30 = var30 * (1.0 - var40) + -10.0 * var40;
                    }

                    doubleArray[var12] = var30;
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
        Biome var6 = this.level.getBiomeSource().getBiome(var4 + 16, var5 + 16);
        this.random.setSeed(this.level.getSeed());
        long var7 = this.random.nextLong() / 2L * 2L + 1L;
        long var9 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed(i * var7 + i2 * var9 ^ this.level.getSeed());
        boolean var11 = false;
        if (this.f_24778720) {
            this.f_46997617.m_44391494(this.level, this.random, i, i2);
            this.f_86759975.m_44391494(this.level, this.random, i, i2);
            var11 = this.f_70462830.m_44391494(this.level, this.random, i, i2);
        }

        if (!var11 && this.random.nextInt(4) == 0) {
            int var12 = var4 + this.random.nextInt(16) + 8;
            int var13 = this.random.nextInt(128);
            int var14 = var5 + this.random.nextInt(16) + 8;
            new LakeFeature(Tile.WATER.id).place(this.level, this.random, var12, var13, var14);
        }

        if (!var11 && this.random.nextInt(8) == 0) {
            int var16 = var4 + this.random.nextInt(16) + 8;
            int var18 = this.random.nextInt(this.random.nextInt(128 - 8) + 8);
            int var20 = var5 + this.random.nextInt(16) + 8;
            if (var18 < 63 || this.random.nextInt(10) == 0) {
                new LakeFeature(Tile.LAVA.id).place(this.level, this.random, var16, var18, var20);
            }
        }

        for (int var17 = 0; var17 < 8; var17++) {
            int var19 = var4 + this.random.nextInt(16) + 8;
            int var21 = this.random.nextInt(128);
            int var15 = var5 + this.random.nextInt(16) + 8;
            if (new MonsterRoomFeature().place(this.level, this.random, var19, var21, var15)) {
            }
        }

        var6.m_46305163(this.level, this.random, var4, var5);
        MobSpawner.m_47652425(this.level, var6, var4 + 8, var5 + 8, 16, 16, this.random);
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
        return "RandomLevelSource";
    }
}
