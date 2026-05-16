package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenFire implements WorldGenerator {
    public WorldGenFire() {
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        for (int l = 0; l < 64; ++l) {
            int blockX = centerX + random.nextInt(8) - random.nextInt(8);
            int blockY = centerY + random.nextInt(4) - random.nextInt(4);
            int blockZ = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(blockX, blockY, blockZ).isAir() && worldContext.getBlock(blockX, blockY - 1, blockZ) == Block.NETHERRACK) {
                worldContext.setBlock(blockX, blockY, blockZ, Block.FIRE);
            }
        }

        return true;
    }
}
