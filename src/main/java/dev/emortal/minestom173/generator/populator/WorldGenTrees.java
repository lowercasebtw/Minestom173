package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.BlockConstants;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenTrees implements WorldGenerator {
    private final int minHeight;
    private final int maxHeight;

    public WorldGenTrees(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        int l = random.nextInt(3) + 4;
        boolean flag = true;

        if (centerY >= (minHeight + 1) && centerY + l + 1 <= (maxHeight + 1)) {
            int i1;
            int j1;
            int k1;
            int l1;
            Block type;

            for (i1 = centerY; i1 <= centerY + 1 + l; ++i1) {
                byte b0 = 1;

                if (i1 == centerY) {
                    b0 = 0;
                }

                if (i1 >= centerY + 1 + l - 2) {
                    b0 = 2;
                }

                for (j1 = centerX - b0; j1 <= centerX + b0 && flag; ++j1) {
                    for (k1 = centerZ - b0; k1 <= centerZ + b0 && flag; ++k1) {
                        if (i1 >= minHeight && i1 < (maxHeight + 1)) {
                            type = worldContext.getBlock(j1, i1, k1);
                            if (!type.isAir() && !BlockConstants.isLeaves(type)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                type = worldContext.getBlock(centerX, centerY - 1, centerZ);
                if ((type == Block.GRASS_BLOCK || type == Block.DIRT) && centerY < (maxHeight + 1) - l - 1) {
                    worldContext.setBlock(centerX, centerY - 1, centerZ, Block.DIRT);

                    int i2;
                    for (i2 = centerY - 3 + l; i2 <= centerY + l; ++i2) {
                        j1 = i2 - (centerY + l);
                        k1 = 1 - j1 / 2;
                        for (l1 = centerX - k1; l1 <= centerX + k1; ++l1) {
                            int j2 = l1 - centerX;

                            for (int k2 = centerZ - k1; k2 <= centerZ + k1; ++k2) {
                                int l2 = k2 - centerZ;

                                if ((Math.abs(j2) != k1 || Math.abs(l2) != k1 || random.nextInt(2) != 0 && j1 != 0) && !LegacyUtil.Block_o(worldContext.getBlock(l1, i2, k2))) {
                                    worldContext.setBlock(l1, i2, k2, Block.OAK_LEAVES);
                                }
                            }
                        }
                    }

                    for (i2 = 0; i2 < l; ++i2) {
                        type = worldContext.getBlock(centerX, centerY + i2, centerZ);
                        if (type.isAir() || BlockConstants.isLeaves(type)) {
                            worldContext.setBlock(centerX, centerY + i2, centerZ, Block.OAK_LOG);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
