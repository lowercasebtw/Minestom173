package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenReed implements WorldGenerator {
    private final int maxHeight;

    public WorldGenReed(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        for (int l = 0; l < 20; ++l) {
            int i1 = centerX + random.nextInt(4) - random.nextInt(4);
            int j1 = centerY;
            int k1 = centerZ + random.nextInt(4) - random.nextInt(4);
            if (worldContext.getBlock(i1, centerY, k1).isAir() && (worldContext.getBlock(i1 - 1, centerY - 1, k1) == Block.WATER || worldContext.getBlock(i1 + 1, centerY - 1, k1) == Block.WATER || worldContext.getBlock(i1, centerY - 1, k1 - 1) == Block.WATER || worldContext.getBlock(i1, centerY - 1, k1 + 1) == Block.WATER)) {
                int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
                for (int i2 = 0; i2 < l1; ++i2) {
                    if (LegacyUtil.Block_canPlace(worldContext, maxHeight, i1, j1 + i2, k1, Block.SUGAR_CANE)) { // f just calls canPlace
                        worldContext.setBlock(i1, j1 + i2, k1, Block.SUGAR_CANE);
                    }
                }
            }
        }

        return true;
    }
}
