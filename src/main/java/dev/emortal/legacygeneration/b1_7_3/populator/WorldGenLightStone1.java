package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.WorldContext;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenLightStone1 implements WorldGenerator {
    public WorldGenLightStone1() {
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        if (!worldContext.getBlock(centerX, centerY, centerZ).isAir()) {
            return false;
        } else if (!worldContext.getBlock(centerX, centerY + 1, centerZ).compare(Block.NETHERRACK)) {
            return false;
        } else {
            final Random random = worldContext.random();
            worldContext.setBlock(centerX, centerY, centerZ, Block.GLOWSTONE);
            for (int l = 0; l < 1500; ++l) {
                int i1 = centerX + random.nextInt(8) - random.nextInt(8);
                int j1 = centerY - random.nextInt(12);
                int k1 = centerZ + random.nextInt(8) - random.nextInt(8);
                if (worldContext.getBlock(i1, j1, k1).isAir()) {
                    int l1 = 0;
                    for (int i2 = 0; i2 < 6; ++i2) {
                        Block j2 = switch (i2) {
                            case 0 -> worldContext.getBlock(i1 - 1, j1, k1);
                            case 1 -> worldContext.getBlock(i1 + 1, j1, k1);
                            case 2 -> worldContext.getBlock(i1, j1 - 1, k1);
                            case 3 -> worldContext.getBlock(i1, j1 + 1, k1);
                            case 4 -> worldContext.getBlock(i1, j1, k1 - 1);
                            default -> worldContext.getBlock(i1, j1, k1 + 1);
                        };
                        if (j2.compare(Block.GLOWSTONE)) {
                            ++l1;
                        }
                    }

                    if (l1 == 1) {
                        worldContext.setBlock(i1, j1, k1, Block.GLOWSTONE);
                    }
                }
            }

            return true;
        }
    }
}
