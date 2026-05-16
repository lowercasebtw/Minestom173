package dev.emortal.minestom173.generator.dimension.nether;

import dev.emortal.minestom173.OldChunkGenerator;
import dev.emortal.minestom173.generator.MapGenBase;
import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.generator.noise.NoiseGeneratorOctaves;
import dev.emortal.minestom173.generator.populator.WorldGenFire;
import dev.emortal.minestom173.generator.populator.WorldGenFlowers;
import dev.emortal.minestom173.generator.populator.WorldGenHellLava;
import dev.emortal.minestom173.generator.populator.WorldGenLightStone1;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.ChunkLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ChunkProviderHell implements ChunkLoader, OldChunkGenerator {
    private final Random random;
    private final NoiseGeneratorOctaves terrainNoise2Generator;
    private final NoiseGeneratorOctaves terrainNoise3Generator;
    private final NoiseGeneratorOctaves terrainNoise1Generator;
    private final NoiseGeneratorOctaves sandAndGravelNoiseGenerator;
    private final NoiseGeneratorOctaves heightNoiseGenerator;
    private final NoiseGeneratorOctaves terrainNoise4Generator;
    private final NoiseGeneratorOctaves terrainNoise5Generator;

    private final int minHeight;
    private final int maxHeight;
    private final long seed;
    private final MapGenBase caveGenerator = new MapGenCavesHell();
    private double[] terrainNoise;
    private double[] soulSandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] heightNoise = new double[256];
    private double[] terrainNoise1;
    private double[] terrainNoise2;
    private double[] terrainNoise3;
    private double[] terrainNoise4;
    private double[] terrainNoise5;

    public ChunkProviderHell(int minHeight, int maxHeight, long seed) {
        this.seed = seed;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        // inal WorldChunkManagerHell worldChunkManager = new WorldChunkManagerHell(BiomeBase.HELL, 1.0D, 0.0D);
        this.random = new Random(seed);
        this.terrainNoise2Generator = new NoiseGeneratorOctaves(this.random, 16);
        this.terrainNoise3Generator = new NoiseGeneratorOctaves(this.random, 16);
        this.terrainNoise1Generator = new NoiseGeneratorOctaves(this.random, 8);
        this.sandAndGravelNoiseGenerator = new NoiseGeneratorOctaves(this.random, 4);
        this.heightNoiseGenerator = new NoiseGeneratorOctaves(this.random, 4);
        this.terrainNoise4Generator = new NoiseGeneratorOctaves(this.random, 10);
        this.terrainNoise5Generator = new NoiseGeneratorOctaves(this.random, 16);
    }

    public void generateBareTerrain(final WorldContext worldContext, final int chunkX, final int chunkZ) {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;

        this.terrainNoise = this.generateTerrainNoise(this.terrainNoise, chunkX * b0, chunkZ * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.terrainNoise[((i1) * l + j1) * b2 + k1];
                    double d2 = this.terrainNoise[((i1) * l + j1 + 1) * b2 + k1];
                    double d3 = this.terrainNoise[((i1 + 1) * l + j1) * b2 + k1];
                    double d4 = this.terrainNoise[((i1 + 1) * l + j1 + 1) * b2 + k1];
                    double d5 = (this.terrainNoise[((i1) * l + j1) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.terrainNoise[((i1) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.terrainNoise[((i1 + 1) * l + j1) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.terrainNoise[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;
                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int k2 = 0; k2 < 4; ++k2) {
                                Block l2 = Block.AIR;

                                if (k1 * 8 + l1 < b1) {
                                    l2 = Block.LAVA;
                                }

                                if (d15 > 0.0D) {
                                    l2 = Block.NETHERRACK;
                                }

                                worldContext.setBlock(j2, l2);
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void generateBiomeTerrain(final WorldContext worldContext, final int chunkX, int chunkZ) {
        byte b0 = 64;
        double d0 = 0.03125D;

        this.soulSandNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.soulSandNoise, chunkX * 16, chunkZ * 16, 0.0D, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.sandAndGravelNoiseGenerator.generateNoise(this.gravelNoise, chunkX * 16, 109.0134D, chunkZ * 16, 16, 1, 16, d0, 1.0D, d0);
        this.heightNoise = this.heightNoiseGenerator.generateNoise(this.heightNoise, chunkX * 16, chunkZ * 16, 0.0D, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                boolean flag = this.soulSandNoise[k + l * 16] + this.random.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[k + l * 16] + this.random.nextDouble() * 0.2D > 0.0D;
                int i1 = (int) (this.heightNoise[k + l * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
                int j1 = -1;
                Block b1 = Block.NETHERRACK;
                Block b2 = Block.NETHERRACK;
                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (l * 16 + k) * 128 + k1;
                    if (k1 >= 127 - this.random.nextInt(5)) {
                        worldContext.setBlock(l1, Block.BEDROCK);
                    } else if (k1 <= this.random.nextInt(5)) {
                        worldContext.setBlock(l1, Block.BEDROCK);
                    } else {
                        Block b3 = worldContext.getBlock(l1);

                        if (b3.isAir()) {
                            j1 = -1;
                        } else if (b3.compare(Block.NETHERRACK)) {
                            if (j1 == -1) {
                                if (i1 <= 0) {
                                    b1 = Block.AIR;
                                    b2 = Block.NETHERRACK;
                                } else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
                                    b1 = Block.NETHERRACK;
                                    b2 = Block.NETHERRACK;
                                    if (flag1) {
                                        b1 = Block.GRAVEL;
                                    }

                                    if (flag) {
                                        b1 = Block.SOUL_SAND;
                                    }

                                    if (flag) {
                                        b2 = Block.SOUL_SAND;
                                    }
                                }

                                if (k1 < b0 && b1.isAir()) {
                                    b1 = Block.LAVA;
                                }

                                j1 = i1;
                                if (k1 >= b0 - 1) {
                                    worldContext.setBlock(l1, b1);
                                } else {
                                    worldContext.setBlock(l1, b2);
                                }
                            } else if (j1 > 0) {
                                --j1;
                                worldContext.setBlock(l1, b2);
                            }
                        }
                    }
                }
            }
        }
    }

    private double[] generateTerrainNoise(double[] into, int startX, int startZ, int lenX, int lenY, int lenZ) {
        if (into == null) {
            into = new double[lenX * lenY * lenZ];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.terrainNoise4 = this.terrainNoise4Generator.generateNoise(this.terrainNoise4, startX, 0, startZ, lenX, 1, lenZ, 1.0D, 0.0D, 1.0D);
        this.terrainNoise5 = this.terrainNoise5Generator.generateNoise(this.terrainNoise5, startX, 0, startZ, lenX, 1, lenZ, 100.0D, 0.0D, 100.0D);
        this.terrainNoise1 = this.terrainNoise1Generator.generateNoise(this.terrainNoise1, startX, 0, startZ, lenX, lenY, lenZ, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.terrainNoise2 = this.terrainNoise2Generator.generateNoise(this.terrainNoise2, startX, 0, startZ, lenX, lenY, lenZ, d0, d1, d0);
        this.terrainNoise3 = this.terrainNoise3Generator.generateNoise(this.terrainNoise3, startX, 0, startZ, lenX, lenY, lenZ, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[lenY];

        int i2;
        for (i2 = 0; i2 < lenY; ++i2) {
            adouble1[i2] = Math.cos((double) i2 * 3.141592653589793D * 6.0D / (double) lenY) * 2.0D;
            double d2 = i2;

            if (i2 > lenY / 2) {
                d2 = lenY - 1 - i2;
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < lenX; ++i2) {
            for (int j2 = 0; j2 < lenZ; ++j2) {
                double d3 = (this.terrainNoise4[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.terrainNoise5[l1] / 8000.0D;

                if (d5 < 0.0D) {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;
                if (d5 < 0.0D) {
                    d5 /= 2.0D;
                    if (d5 < -1.0D) {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                } else {
                    if (d5 > 1.0D) {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double) lenY / 16.0D;
                ++l1;

                for (int k2 = 0; k2 < lenY; ++k2) {
                    double d6;
                    double d7 = adouble1[k2];
                    double d8 = this.terrainNoise2[k1] / 512.0D;
                    double d9 = this.terrainNoise3[k1] / 512.0D;
                    double d10 = (this.terrainNoise1[k1] / 10.0D + 1.0D) / 2.0D;
                    if (d10 < 0.0D) {
                        d6 = d8;
                    } else if (d10 > 1.0D) {
                        d6 = d9;
                    } else {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (k2 > lenY - 4) {
                        d11 = (float) (k2 - (lenY - 4)) / 3.0F;
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    into[k1] = d6;
                    ++k1;
                }
            }
        }

        return into;
    }

    public void generateUnpopulatedChunkData(final WorldContext worldContext, final int chunkX, final int chunkZ) {
        this.random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);

        this.generateBareTerrain(worldContext, chunkX, chunkZ);
        this.generateBiomeTerrain(worldContext, chunkX, chunkZ);
        this.caveGenerator.generate(worldContext, this.minHeight, this.maxHeight, this.seed, chunkX, chunkZ);
        for (int z = 0; z <= 15; ++z) {
            for (int x = 0; x <= 15; ++x) {
                worldContext.setBiome(x, z, Biome.NETHER_WASTES);
            }
        }
    }

    @Override
    public void populateChunk(final WorldContext worldContext, final int chunkX, final int chunkZ) {
        int k = chunkX * 16;
        int l = chunkZ * 16;

        int i1;
        int j1;
        int k1;
        int l1;

        // Note: Beta 173 nether generation never actually sets the random seed when generating!
        // This code is added by us so that nether generation is consistent between runs.
        // We can never truly replicate populator randomness thanks to this bug, but at least we can
        // make it consistent now.
        this.random.setSeed(this.seed);
        this.random.setSeed((long) chunkX * (this.random.nextLong() / 2L * 2L + 1L) + (long) chunkZ * (this.random.nextLong() / 2L * 2L + 1L) ^ this.seed);

        for (i1 = 0; i1 < 8; ++i1) {
            j1 = k + this.random.nextInt(16) + 8;
            k1 = (this.random.nextInt(120 - minHeight) + minHeight) + 4;
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenHellLava(Block.LAVA)).populate(worldContext, j1, k1, l1);
        }

        i1 = this.random.nextInt(this.random.nextInt(10) + 1) + 1;

        int i2;

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = (this.random.nextInt(120 - minHeight) + minHeight) + 4;
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenFire()).populate(worldContext, k1, l1, i2);
        }

        i1 = this.random.nextInt(this.random.nextInt(10) + 1);

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = (this.random.nextInt(120 - minHeight) + minHeight) + 4;
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenLightStone1()).populate(worldContext, k1, l1, i2);
        }

        for (j1 = 0; j1 < 10; ++j1) {
            k1 = k + this.random.nextInt(16) + 8;
            l1 = this.random.nextInt(maxHeight + 1 - minHeight) + minHeight;
            i2 = l + this.random.nextInt(16) + 8;
            (new WorldGenLightStone1()).populate(worldContext, k1, l1, i2);
        }

        if (this.random.nextInt(1) == 0) { // TODO: should this be really be bound 1?
            j1 = k + this.random.nextInt(16) + 8;
            k1 = this.random.nextInt(maxHeight + 1 - minHeight) + minHeight;
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM, this.minHeight, this.maxHeight)).populate(worldContext, j1, k1, l1);
        }

        if (this.random.nextInt(1) == 0) { // TODO: should this be really be bound 1?
            j1 = k + this.random.nextInt(16) + 8;
            k1 = this.random.nextInt(maxHeight + 1 - minHeight) + minHeight;
            l1 = l + this.random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM, this.minHeight, this.maxHeight)).populate(worldContext, j1, k1, l1);
        }
    }

    @Override
    public @Nullable Chunk loadChunk(final @NotNull Instance instance, final int chunkX, final int chunkZ) {
        final Chunk chunk = instance.getChunkSupplier().createChunk(instance, chunkX, chunkZ);
        generateUnpopulatedChunkData(new WorldContext(chunk, instance, this.random), chunkX, chunkZ);
        return chunk;
    }

    @Override
    public Random getRandom() {
        return this.random;
    }

    @Override
    public void saveChunk(final @NotNull Chunk chunk) {
    }

    @Override
    public boolean supportsParallelSaving() {
        return true;
    }

    @Override
    public boolean supportsParallelLoading() {
        return true;
    }
}
