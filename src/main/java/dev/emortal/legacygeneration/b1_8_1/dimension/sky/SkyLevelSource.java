package dev.emortal.legacygeneration.b1_8_1.dimension.sky;

import dev.emortal.legacygeneration.b1_8_1.BiomeBase181;
import dev.emortal.legacygeneration.b1_8_1.ChunkGenerator181;
import dev.emortal.legacygeneration.b1_8_1.noise.PerlinNoise;
import dev.emortal.legacygeneration.b1_8_1.populator.*;
import dev.emortal.legacygeneration.util.WorldContext;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.ChunkLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SkyLevelSource implements ChunkLoader, ChunkGenerator181 {
    private final Random random;
    private final PerlinNoise f_20419278;
    private final PerlinNoise f_02101154;
    private final PerlinNoise f_61600697;
    private final PerlinNoise f_60386758;
    private final PerlinNoise f_45973730;
    private final WorldContext worldContext;
    private final LargeFeature carver = new LargeCaveFeature();
    public PerlinNoise f_43364593;
    public PerlinNoise f_30721079;
    public PerlinNoise f_28278332;
    double[] f_65571301;
    double[] f_47747913;
    double[] f_89433342;
    double[] f_46457714;
    double[] f_80477127;
    int[][] f_09463446 = new int[32][32];
    private double[] heights;
    private double[] f_91111496 = new double[256];
    private double[] f_27149069 = new double[256];
    private double[] f_86952411 = new double[256];
    private BiomeBase181[] biomes;

    public SkyLevelSource(WorldContext worldContext, long seed) {
        this.worldContext = worldContext;
        this.random = new Random(seed);
        this.f_20419278 = new PerlinNoise(this.random, 16);
        this.f_02101154 = new PerlinNoise(this.random, 16);
        this.f_61600697 = new PerlinNoise(this.random, 8);
        this.f_60386758 = new PerlinNoise(this.random, 4);
        this.f_45973730 = new PerlinNoise(this.random, 4);
        this.f_43364593 = new PerlinNoise(this.random, 10);
        this.f_30721079 = new PerlinNoise(this.random, 16);
        this.f_28278332 = new PerlinNoise(this.random, 8);
    }

    public void prepareHeights(int i, int i2, byte[] byteArray, BiomeBase181[] biomeArray) {
        byte var5 = 2;
        int var6 = var5 + 1;
        int var7 = 128 / 4 + 1;
        int var8 = var5 + 1;
        this.heights = this.getHeights(this.heights, i * var5, 0, i2 * var5, var6, var7, var8);

        for (int var9 = 0; var9 < var5; var9++) {
            for (int var10 = 0; var10 < var5; var10++) {
                for (int var11 = 0; var11 < 128 / 4; var11++) {
                    double var12 = 0.25;
                    double var14 = this.heights[((var9) * var8 + var10) * var7 + var11];
                    double var16 = this.heights[((var9) * var8 + var10 + 1) * var7 + var11];
                    double var18 = this.heights[((var9 + 1) * var8 + var10) * var7 + var11];
                    double var20 = this.heights[((var9 + 1) * var8 + var10 + 1) * var7 + var11];
                    double var22 = (this.heights[((var9) * var8 + var10) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.heights[((var9) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.heights[((var9 + 1) * var8 + var10) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.heights[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 4; var30++) {
                        double var31 = 0.125;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 8; var41++) {
                            int var42 = var41 + var9 * 8 << 11 | var10 * 8 << 7 | var11 * 4 + var30;
                            int var43 = 1 << 7;
                            double var44 = 0.125;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 8; var50++) {
                                int var51 = 0;
                                if (var46 > 0.0) {
                                    var51 = Block.STONE.id();
                                }

                                byteArray[var42] = (byte) var51;
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

    public void buildSurfaces(int i, int i2, byte[] byteArray, BiomeBase181[] biomeArray) {
        double var5 = 0.03125;
        this.f_91111496 = this.f_60386758.getRegion(this.f_91111496, i * 16, i2 * 16, 0, 16, 16, 1, var5, var5, 1.0);
        this.f_27149069 = this.f_60386758.getRegion(this.f_27149069, i * 16, 109, i2 * 16, 16, 1, 16, var5, 1.0, var5);
        this.f_86952411 = this.f_45973730.getRegion(this.f_86952411, i * 16, i2 * 16, 0, 16, 16, 1, var5 * 2.0, var5 * 2.0, var5 * 2.0);

        for (int var7 = 0; var7 < 16; var7++) {
            for (int var8 = 0; var8 < 16; var8++) {
                BiomeBase181 var9 = biomeArray[var7 + var8 * 16];
                int var10 = (int) (this.f_86952411[var7 + var8 * 16] / 3.0 + 3.0 + this.random.nextDouble() * 0.25);
                int var11 = -1;
                byte var12 = var9.topMaterial;
                byte var13 = var9.material;

                for (int var14 = 127; var14 >= 0; var14--) {
                    int var15 = (var8 * 16 + var7) * 128 + var14;
                    byte var16 = byteArray[var15];
                    if (var16 == 0) {
                        var11 = -1;
                    } else if (var16 == Block.STONE.id) {
                        if (var11 == -1) {
                            if (var10 <= 0) {
                                var12 = 0;
                                var13 = (byte) Block.STONE.id;
                            }

                            var11 = var10;
                            if (var14 >= 0) {
                                byteArray[var15] = var12;
                            } else {
                                byteArray[var15] = var13;
                            }
                        } else if (var11 > 0) {
                            var11--;
                            byteArray[var15] = var13;
                            if (var11 == 0 && var13 == Block.SAND.id) {
                                var11 = this.random.nextInt(4);
                                var13 = (byte) Block.SANDSTONE.id;
                            }
                        }
                    }
                }
            }
        }
    }


    private double[] getHeights(double[] doubleArray, int i, int i2, int i3, int i4, int i5, int i6) {
        if (doubleArray == null) {
            doubleArray = new double[i4 * i5 * i6];
        }

        double var8 = 684.412;
        double var10 = 684.412;
        this.f_46457714 = this.f_43364593.getRegion(this.f_46457714, i, i3, i4, i6, 1.121, 1.121, 0.5);
        this.f_80477127 = this.f_30721079.getRegion(this.f_80477127, i, i3, i4, i6, 200.0, 200.0, 0.5);
        var8 *= 2.0;
        this.f_65571301 = this.f_61600697.getRegion(this.f_65571301, i, i2, i3, i4, i5, i6, var8 / 80.0, var10 / 160.0, var8 / 80.0);
        this.f_47747913 = this.f_20419278.getRegion(this.f_47747913, i, i2, i3, i4, i5, i6, var8, var10, var8);
        this.f_89433342 = this.f_02101154.getRegion(this.f_89433342, i, i2, i3, i4, i5, i6, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < i4; var14++) {
            for (int var15 = 0; var15 < i6; var15++) {
                double var16 = (this.f_46457714[var13] + 256.0) / 512.0;
                if (var16 > 1.0) {
                    var16 = 1.0;
                }

                double var18 = this.f_80477127[var13] / 8000.0;
                if (var18 < 0.0) {
                    var18 = -var18 * 0.3;
                }

                var18 = var18 * 3.0 - 2.0;
                if (var18 > 1.0) {
                    var18 = 1.0;
                }

                var18 /= 8.0;
                var18 = 0.0;
                if (var16 < 0.0) {
                    var16 = 0.0;
                }

                var16 += 0.5;
                var18 = var18 * i5 / 16.0;
                var13++;
                double var20 = i5 / 2.0;

                for (int var22 = 0; var22 < i5; var22++) {
                    double var23 = 0.0;
                    double var25 = (var22 - var20) * 8.0 / var16;
                    if (var25 < 0.0) {
                        var25 *= -1.0;
                    }

                    double var27 = this.f_47747913[var12] / 512.0;
                    double var29 = this.f_89433342[var12] / 512.0;
                    double var31 = (this.f_65571301[var12] / 10.0 + 1.0) / 2.0;
                    if (var31 < 0.0) {
                        var23 = var27;
                    } else if (var31 > 1.0) {
                        var23 = var29;
                    } else {
                        var23 = var27 + (var29 - var27) * var31;
                    }

                    var23 -= 8.0;
                    byte var33 = 32;
                    if (var22 > i5 - var33) {
                        double var34 = (var22 - (i5 - var33)) / (var33 - 1.0F);
                        var23 = var23 * (1.0 - var34) + -30.0 * var34;
                    }

                    var33 = 8;
                    if (var22 < var33) {
                        double var46 = (var33 - var22) / (var33 - 1.0F);
                        var23 = var23 * (1.0 - var46) + -30.0 * var46;
                    }

                    doubleArray[var12] = var23;
                    var12++;
                }
            }
        }

        return doubleArray;
    }

    @Override
    public void populateChunk(final WorldContext worldContext, final int chunkX, final int chunkZ) {
        SandBlock.instaFall = true;
        int var4 = chunkX * 16;
        int var5 = chunkZ * 16;
        BiomeBase181 var6 = this.worldContext.getBiome(var4 + 16, var5 + 16);
        this.random.setSeed(this.level.getSeed());
        long var7 = this.random.nextLong() / 2L * 2L + 1L;
        long var9 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed(chunkX * var7 + chunkZ * var9 ^ this.level.getSeed());
        double var11 = 0.25;
        if (this.random.nextInt(4) == 0) {
            int var13 = var4 + this.random.nextInt(16) + 8;
            int var14 = this.random.nextInt(128);
            int var15 = var5 + this.random.nextInt(16) + 8;
            new LakeFeature(Block.WATER).populate(this.worldContext, var13, var14, var15);
        }

        if (this.random.nextInt(8) == 0) {
            int var21 = var4 + this.random.nextInt(16) + 8;
            int var33 = this.random.nextInt(this.random.nextInt(128 - 8) + 8);
            int var45 = var5 + this.random.nextInt(16) + 8;
            if (var33 < 64 || this.random.nextInt(10) == 0) {
                new LakeFeature(Block.LAVA).populate(this.worldContext, var21, var33, var45);
            }
        }

        for (int var22 = 0; var22 < 8; var22++) {
            int var34 = var4 + this.random.nextInt(16) + 8;
            int var46 = this.random.nextInt(128);
            int var16 = var5 + this.random.nextInt(16) + 8;
            new MonsterRoomFeature().populate(this.worldContext, var34, var46, var16);
        }

        for (int var23 = 0; var23 < 10; var23++) {
            int var35 = var4 + this.random.nextInt(16);
            int var47 = this.random.nextInt(128);
            int var64 = var5 + this.random.nextInt(16);
            new ClayFeature(32).populate(this.worldContext, var35, var47, var64);
        }

        for (int var24 = 0; var24 < 20; var24++) {
            int var36 = var4 + this.random.nextInt(16);
            int var48 = this.random.nextInt(128);
            int var65 = var5 + this.random.nextInt(16);
            new OreFeature(Block.DIRT, 32).populate(this.worldContext, var36, var48, var65);
        }

        for (int var25 = 0; var25 < 10; var25++) {
            int var37 = var4 + this.random.nextInt(16);
            int var49 = this.random.nextInt(128);
            int var66 = var5 + this.random.nextInt(16);
            new OreFeature(Block.GRAVEL, 32).populate(this.worldContext, var37, var49, var66);
        }

        for (int var26 = 0; var26 < 20; var26++) {
            int var38 = var4 + this.random.nextInt(16);
            int var50 = this.random.nextInt(128);
            int var67 = var5 + this.random.nextInt(16);
            new OreFeature(Block.COAL_ORE, 16).populate(this.worldContext, var38, var50, var67);
        }

        for (int var27 = 0; var27 < 20; var27++) {
            int var39 = var4 + this.random.nextInt(16);
            int var51 = this.random.nextInt(128 / 2);
            int var68 = var5 + this.random.nextInt(16);
            new OreFeature(Block.IRON_ORE, 8).populate(this.worldContext, var39, var51, var68);
        }

        for (int var28 = 0; var28 < 2; var28++) {
            int var40 = var4 + this.random.nextInt(16);
            int var52 = this.random.nextInt(128 / 4);
            int var69 = var5 + this.random.nextInt(16);
            new OreFeature(Block.GOLD_ORE, 8).populate(this.worldContext, var40, var52, var69);
        }

        for (int var29 = 0; var29 < 8; var29++) {
            int var41 = var4 + this.random.nextInt(16);
            int var53 = this.random.nextInt(128 / 8);
            int var70 = var5 + this.random.nextInt(16);
            new OreFeature(Block.REDSTONE_ORE, 7).populate(this.worldContext, var41, var53, var70);
        }

        for (int var30 = 0; var30 < 1; var30++) {
            int var42 = var4 + this.random.nextInt(16);
            int var54 = this.random.nextInt(128 / 8);
            int var71 = var5 + this.random.nextInt(16);
            new OreFeature(Block.DIAMOND_ORE, 7).populate(this.worldContext, var42, var54, var71);
        }

        for (int var31 = 0; var31 < 1; var31++) {
            int var43 = var4 + this.random.nextInt(16);
            int var55 = this.random.nextInt(128 / 8) + this.random.nextInt(128 / 8);
            int var72 = var5 + this.random.nextInt(16);
            new OreFeature(Block.LAPIS_ORE, 6).populate(this.worldContext, var43, var55, var72);
        }

        var11 = 0.5;
        int var32 = (int) ((this.f_28278332.getValue(var4 * var11, var5 * var11) / 8.0 + this.random.nextDouble() * 4.0 + 4.0) / 3.0);
        int var44 = 0;
        if (this.random.nextInt(10) == 0) {
            var44++;
        }

        if (var6 == BiomeBase181.FOREST) {
            var44 += var32 + 5;
        }

        if (var6 == BiomeBase181.DESERT) {
            var44 -= 20;
        }

        if (var6 == BiomeBase181.PLAINS) {
            var44 -= 20;
        }

        for (int var56 = 0; var56 < var44; var56++) {
            int var73 = var4 + this.random.nextInt(16) + 8;
            int var17 = var5 + this.random.nextInt(16) + 8;
            Feature var18 = var6.getTreeFeature(this.random);
            var18.init(1.0, 1.0, 1.0);
            var18.populate(this.worldContext, var73, this.level.getHeightmap(var73, var17), var17);
        }

        for (int var57 = 0; var57 < 2; var57++) {
            int var74 = var4 + this.random.nextInt(16) + 8;
            int var83 = this.random.nextInt(128);
            int var92 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Block.FLOWER).populate(this.worldContext, var74, var83, var92);
        }

        if (this.random.nextInt(2) == 0) {
            int var58 = var4 + this.random.nextInt(16) + 8;
            int var75 = this.random.nextInt(128);
            int var84 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Block.POPPY).populate(this.worldContext, var58, var75, var84);
        }

        if (this.random.nextInt(4) == 0) {
            int var59 = var4 + this.random.nextInt(16) + 8;
            int var76 = this.random.nextInt(128);
            int var85 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Block.BROWN_MUSHROOM).populate(this.worldContext, var59, var76, var85);
        }

        if (this.random.nextInt(8) == 0) {
            int var60 = var4 + this.random.nextInt(16) + 8;
            int var77 = this.random.nextInt(128);
            int var86 = var5 + this.random.nextInt(16) + 8;
            new FlowerFeature(Block.RED_MUSHROOM).populate(this.worldContext, var60, var77, var86);
        }

        for (int var61 = 0; var61 < 10; var61++) {
            int var78 = var4 + this.random.nextInt(16) + 8;
            int var87 = this.random.nextInt(128);
            int var93 = var5 + this.random.nextInt(16) + 8;
            new ReedsFeature().populate(this.worldContext, var78, var87, var93);
        }

        if (this.random.nextInt(32) == 0) {
            int var62 = var4 + this.random.nextInt(16) + 8;
            int var79 = this.random.nextInt(128);
            int var88 = var5 + this.random.nextInt(16) + 8;
            new PumpkinFeature().populate(this.worldContext, var62, var79, var88);
        }

        byte var63 = 0;
        if (var6 == BiomeBase181.DESERT) {
            var63 += 10;
        }

        for (int var80 = 0; var80 < var63; var80++) {
            int var89 = var4 + this.random.nextInt(16) + 8;
            int var94 = this.random.nextInt(128);
            int var19 = var5 + this.random.nextInt(16) + 8;
            new CactusFeature().populate(this.worldContext, var89, var94, var19);
        }

        for (int var81 = 0; var81 < 50; var81++) {
            int var90 = var4 + this.random.nextInt(16) + 8;
            int var95 = this.random.nextInt(this.random.nextInt(128 - 8) + 8);
            int var97 = var5 + this.random.nextInt(16) + 8;
            new SpringFeature(Block.FLOWING_WATER).populate(this.worldContext, var90, var95, var97);
        }

        for (int var82 = 0; var82 < 20; var82++) {
            int var91 = var4 + this.random.nextInt(16) + 8;
            int var96 = this.random.nextInt(this.random.nextInt(this.random.nextInt(128 - 16) + 8) + 8);
            int var98 = var5 + this.random.nextInt(16) + 8;
            new SpringFeature(Block.FLOWING_LAVA).populate(this.worldContext, var91, var96, var98);
        }

        SandBlock.instaFall = false;
    }

    @Override
    public Random getRandom() {
        return random;
    }

    @Override
    public @Nullable Chunk loadChunk(final Instance instance, final int chunkX, final int chunkZ) {
        this.random.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        byte[] var3 = new byte[16 * 128 * 16];
        Chunk var4 = instance.getChunkSupplier().createChunk(instance, chunkX, chunkZ);
        this.biomes = instance.level.getBiomeSource().getBiomeBlock(this.biomes, chunkX * 16, chunkZ * 16, 16, 16);
        this.prepareHeights(chunkX, chunkZ, var3, this.biomes);
        this.buildSurfaces(chunkX, chunkZ, var3, this.biomes);
        this.carver.apply(this, this.level, chunkX, chunkZ, var3);
        var4.recalcHeightmap();
        return var4;
    }

    @Override
    public void saveChunk(final Chunk chunk) {
    }

    @Override
    public boolean supportsParallelSaving() {
        return true;
    }
}
