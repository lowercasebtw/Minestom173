package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.BlockConstants;
import dev.emortal.legacygeneration.util.LegacyUtil;
import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenDeadBush implements WorldGenerator {
    private final Block blockData;
    private final int minHeight;
    private final int maxHeight;

    public WorldGenDeadBush(Block i, int minHeight, int maxHeight) {
        this.blockData = i;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        Block block;
        while (((block = worldContext.getBlock(centerX, centerY, centerZ)).isAir() || BlockConstants.isLeaves(block)) && centerY > minHeight) {
            --centerY;
        }

        final Random random = worldContext.random();
        for (int i1 = 0; i1 < 4; ++i1) {
            int j1 = centerX + random.nextInt(8) - random.nextInt(8);
            int k1 = centerY + random.nextInt(4) - random.nextInt(4);
            int l1 = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(j1, k1, l1).isAir() && LegacyUtil.BlockFlower_f(worldContext, j1, k1, l1, minHeight, maxHeight, this.blockData)) {
                worldContext.setBlock(j1, k1, l1, this.blockData);
            }
        }

        return true;
    }
}
