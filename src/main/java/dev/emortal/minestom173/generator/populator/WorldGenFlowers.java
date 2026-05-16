package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenFlowers implements WorldGenerator {
    private final Block block;
    private final int minHeight;
    private final int maxHeight;

    public WorldGenFlowers(Block block, int minHeight, int maxHeight) {
        this.block = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        for (int l = 0; l < 64; ++l) {
            final int blockX = centerX + random.nextInt(8) - random.nextInt(8);
            final int blockY = centerY + random.nextInt(4) - random.nextInt(4);
            final int blockZ = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(blockX, blockY, blockZ).isAir() && LegacyUtil.BlockFlower_f(worldContext, minHeight, maxHeight, blockX, blockY, blockZ, this.block)) {
                worldContext.setBlock(blockX, blockY, blockZ, this.block);
            }
        }

        return true;
    }
}
