package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.LegacyUtil;
import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class BushFeature implements WorldGenerator {
    private final Block tile;

    public BushFeature(Block tile) {
        this.tile = tile;
    }

    @Override
    public boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ) {
        final Random random = worldContext.random();
        int modifiedCenterY = centerY;

        Block var6;
        while (((var6 = worldContext.getBlock(centerX, centerY, centerZ)).isAir() || var6 == Block.OAK_LEAVES) && centerY > 0) {
            modifiedCenterY--;
        }

        for (int var7 = 0; var7 < 4; var7++) {
            int var8 = centerX + random.nextInt(8) - random.nextInt(8);
            int var9 = modifiedCenterY + random.nextInt(4) - random.nextInt(4);
            int var10 = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.m_64558657(var8, var9, var10) && LegacyUtil.Block_canPlace(worldContext, 128, var8, var9, var10, this.tile)) {
                worldContext.setBlock(var8, var9, var10, this.tile);
            }
        }

        return true;
    }
}
