package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;
import net.minestom.server.instance.block.Block;

public class WorldGenLiquids implements WorldGenerator {

    private final Block blockData;

    public WorldGenLiquids(Block i) {
        this.blockData = i;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        if (worldContext.getBlock(centerX, centerY + 1, centerZ) != Block.STONE) {
            return false;
        } else if (worldContext.getBlock(centerX, centerY - 1, centerZ) != Block.STONE) {
            return false;
        } else if (!worldContext.getBlock(centerX, centerY, centerZ).isAir() && worldContext.getBlock(centerX, centerY, centerZ) != Block.STONE) {
            return false;
        } else {
            int l = 0;

            if (worldContext.getBlock(centerX - 1, centerY, centerZ) == Block.STONE) {
                ++l;
            }

            if (worldContext.getBlock(centerX + 1, centerY, centerZ) == Block.STONE) {
                ++l;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ - 1) == Block.STONE) {
                ++l;
            }

            if (worldContext.getBlock(centerX, centerY, centerZ + 1) == Block.STONE) {
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

            if (l == 3 && i1 == 1) {
                worldContext.setBlock(centerX, centerY, centerZ, this.blockData); // want physics here so the water flows TODO: add physics
            }

            return true;
        }
    }
}
