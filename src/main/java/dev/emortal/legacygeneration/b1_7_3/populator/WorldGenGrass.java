package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.BlockConstants;
import dev.emortal.legacygeneration.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenGrass implements WorldGenerator {
    private final Block blockData;
    private final int minHeight;
    private final int maxHeight;

    public WorldGenGrass(Block i, int minHeight, int maxHeight) {
        this.blockData = i;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        Block block;
        while (((block = worldContext.getBlock(centerX, centerY, centerZ)).isAir() || BlockConstants.isLeaves(block)) && centerY > 0) {
            --centerY;
        }

        final Random random = worldContext.random();
        for (int i = 0; i < 128; ++i) {
            final int blockX = centerX + random.nextInt(8) - random.nextInt(8);
            final int blockY = centerY + random.nextInt(4) - random.nextInt(4);
            final int blockZ = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(blockX, blockY, blockZ).isAir() && LegacyUtil.BlockFlower_f(worldContext, minHeight, maxHeight, blockX, blockY, blockZ, this.blockData)) {
                worldContext.setBlock(blockX, blockY, blockZ, this.blockData);
            }
        }

        return true;
    }
}
