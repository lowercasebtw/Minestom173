package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import net.minestom.server.instance.block.Block;

public class WorldGenHellLava implements WorldGenerator {
    private final Block block;

    public WorldGenHellLava(Block i) {
        this.block = i;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        if (!worldContext.getBlock(centerX, centerY + 1, centerZ).compare(Block.NETHERRACK)) {
            return false;
        } else if (!worldContext.getBlock(centerX, centerY, centerZ).isAir() && !worldContext.getBlock(centerX, centerY, centerZ).compare(Block.NETHERRACK)) {
            return false;
        } else {
            int l = 0;

            if (worldContext.getBlock(centerX - 1, centerY, centerZ).compare(Block.NETHERRACK)) {
                ++l;
            }

            if (worldContext.getBlock(centerX + 1, centerY, centerZ).compare(Block.NETHERRACK)) {
                ++l;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ - 1).compare(Block.NETHERRACK)) {
                ++l;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ + 1).compare(Block.NETHERRACK)) {
                ++l;
            }

            if (worldContext.getBlock(centerX, centerY - 1, centerZ).compare(Block.NETHERRACK)) {
                ++l;
            }

            int i1 = 0;

            if (worldContext.getBlock(centerX - 1, centerY, centerZ).isAir()) {
                ++i1;
            }

            if (worldContext.getBlock(centerX + 1, centerY, centerZ).isAir()) {
                ++i1;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ - 1).isAir()) {
                ++i1;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ + 1).isAir()) {
                ++i1;
            }

            if (worldContext.getBlock(centerX, centerY - 1, centerZ).isAir()) {
                ++i1;
            }

            if (l == 4 && i1 == 1) {
                worldContext.setBlock(centerX, centerY, centerZ, this.block); // want physics here so the lava falls. // TODO: add physics here
            }

            return true;
        }
    }
}
