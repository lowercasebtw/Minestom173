package dev.emortal.minestom173.generator;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.biome.Biome;

import java.util.Random;

public record WorldContext(Block.Getter blockGetter, Block.Setter blockSetter, Biome.Setter biomeSetter,
                           LightGetter lightGetter, Random random) {
    public WorldContext(final Chunk chunk, final Instance instance, final Random random) {
        this(chunk, chunk, chunk, LightGetter.of(instance), random);
    }

    public Block getBlock(final int blockX, final int blockY, final int blockZ, final Block.Getter.Condition condition) {
        return this.blockGetter.getBlock(blockX, blockY, blockZ, condition);
    }

    public Block getBlock(final int blockX, final int blockY, final int blockZ) {
        return this.blockGetter.getBlock(blockX, blockY, blockZ);
    }

    public Block getBlock(final int index) {
        return this.getBlock(index >>> 11, index & 127, (index >>> 7) & 15, Block.Getter.Condition.TYPE);
    }

    public void setBlock(final int blockX, final int blockY, final int blockZ, final Block block) {
        this.blockSetter.setBlock(blockX, blockY, blockZ, block);
    }

    public void setBlock(final int index, final Block block) {
        this.setBlock(index >>> 11, index & 127, (index >>> 7) & 15, block);
    }

    public int getSkyLight(final int blockX, final int blockY, final int blockZ) {
        return this.lightGetter.getLight(LightGetter.Type.SKY, blockX, blockY, blockZ);
    }

    public int getBlockLight(final int blockX, final int blockY, final int blockZ) {
        return this.lightGetter.getLight(LightGetter.Type.BLOCK, blockX, blockY, blockZ);
    }

    public void setBiome(final int chunkX, final int chunkZ, final RegistryKey<Biome> biome) {
        for (int y = 0; y < 128; ++y) {
            this.biomeSetter.setBiome(chunkX, y, chunkZ, biome);
        }
    }

    public interface LightGetter {
        static LightGetter of(final Instance instance) {
            return (type, blockX, blockY, blockZ) -> {
                if (type == Type.SKY) {
                    return instance.getSkyLight(blockX, blockY, blockZ);
                } else {
                    return instance.getBlockLight(blockX, blockY, blockZ);
                }
            };
        }

        static LightGetter of(final InstanceContainer instance) {
            return (type, blockX, blockY, blockZ) -> {
                if (type == Type.SKY) {
                    return instance.getSkyLight(blockX, blockY, blockZ);
                } else {
                    return instance.getBlockLight(blockX, blockY, blockZ);
                }
            };
        }

        int getLight(final Type type, final int blockX, final int blockY, final int blockZ);

        enum Type {
            SKY,
            BLOCK
        }
    }
}
