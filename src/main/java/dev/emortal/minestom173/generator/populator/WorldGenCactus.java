package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenCactus implements WorldGenerator {
    public WorldGenCactus() {
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        for (int l = 0; l < 10; ++l) {
            final Random random = worldContext.random();
            int i1 = centerX + random.nextInt(8) - random.nextInt(8);
            int j1 = centerY + random.nextInt(4) - random.nextInt(4);
            int k1 = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(i1, j1, k1).isAir()) {
                int l1 = 1 + random.nextInt(random.nextInt(3) + 1);
                for (int i2 = 0; i2 < l1; ++i2) {
                    if (LegacyUtil.BlockCactus_f(worldContext, i1, j1 + i2, k1)) {
                        worldContext.setBlock(i1, j1 + i2, k1, Block.CACTUS);
                    }
                }
            }
        }

        return true;
    }
}
