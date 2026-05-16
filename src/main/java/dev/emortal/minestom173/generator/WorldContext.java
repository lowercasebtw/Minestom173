package dev.emortal.minestom173.generator;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.biome.Biome;

import java.util.Random;

public interface WorldContext {
    Block getBlock(final int blockX, final int blockY, final int blockZ, final Block.Getter.Condition condition);

    Block getBlock(final int blockX, final int blockY, final int blockZ);

    Block getBlock(final int index);

    void setBlock(final int blockX, final int blockY, final int blockZ, final Block block);

    void setBlock(final int index, final Block block);

    int getSkyLight(final int blockX, final int blockY, final int blockZ);

    int getBlockLight(final int blockX, final int blockY, final int blockZ);

    void setBiome(final int chunkX, final int chunkZ, final RegistryKey<Biome> biome);

    Random random();

    final class LightGetter {
        private final Instance instance;

        public LightGetter(final Instance instance) {
            this.instance = instance;
        }

        public int getSkyLight(final int blockX, final int blockY, final int blockZ) {
            return this.instance.getSkyLight(blockX, blockY, blockZ);
        }

        public int getBlockLight(final int blockX, final int blockY, final int blockZ) {
            return this.instance.getBlockLight(blockX, blockY, blockZ);
        }
    }

    record Impl(Block.Getter blockGetter, Block.Setter blockSetter, Biome.Setter biomeSetter,
                LightGetter lightGetter, Random random) implements WorldContext {
        public Impl(final Chunk chunk, final Instance instance, final Random random) {
            this(chunk, chunk, chunk, new LightGetter(instance), random);
        }

        @Override
        public Block getBlock(final int blockX, final int blockY, final int blockZ, final Block.Getter.Condition condition) {
            return this.blockGetter.getBlock(blockX, blockY, blockZ, condition);
        }

        @Override
        public Block getBlock(final int blockX, final int blockY, final int blockZ) {
            return this.blockGetter.getBlock(blockX, blockY, blockZ);
        }

        @Override
        public Block getBlock(final int index) {
            return this.getBlock(index >>> 11, index & 127, (index >>> 7) & 15, Block.Getter.Condition.TYPE);
        }

        @Override
        public void setBlock(final int blockX, final int blockY, final int blockZ, final Block block) {
            this.blockSetter.setBlock(blockX, blockY, blockZ, block);
        }

        @Override
        public void setBlock(final int index, final Block block) {
            this.setBlock(index >>> 11, index & 127, (index >>> 7) & 15, block);
        }

        @Override
        public int getSkyLight(final int blockX, final int blockY, final int blockZ) {
            return this.lightGetter.getSkyLight(blockX, blockY, blockZ);
        }

        @Override
        public int getBlockLight(final int blockX, final int blockY, final int blockZ) {
            return this.lightGetter.getBlockLight(blockX, blockY, blockZ);
        }

        @Override
        public void setBiome(final int chunkX, final int chunkZ, final RegistryKey<Biome> biome) {
            for (int y = 0; y < 128; ++y) {
                this.biomeSetter.setBiome(chunkX, y, chunkZ, biome);
            }
        }
    }
}
