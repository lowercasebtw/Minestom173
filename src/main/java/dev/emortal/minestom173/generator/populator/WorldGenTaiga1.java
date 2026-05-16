package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.BlockConstants;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenTaiga1 implements WorldGenerator {
    private final int minHeight;
    private final int maxHeight;

    public WorldGenTaiga1(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        int l = random.nextInt(5) + 7;
        int i1 = l - random.nextInt(2) - 3;
        int j1 = l - i1;
        int k1 = 1 + random.nextInt(j1 + 1);
        boolean flag = true;
        if (centerY >= (minHeight + 1) && centerY + l + 1 <= (maxHeight + 1)) {
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            Block type;
            for (l1 = centerY; l1 <= centerY + 1 + l && flag; ++l1) {
                if (l1 - centerY < i1) {
                    l2 = 0;
                } else {
                    l2 = k1;
                }

                for (i2 = centerX - l2; i2 <= centerX + l2 && flag; ++i2) {
                    for (j2 = centerZ - l2; j2 <= centerZ + l2 && flag; ++j2) {
                        if (l1 >= minHeight && l1 < (maxHeight + 1)) {
                            type = worldContext.getBlock(i2, l1, j2);
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
                if ((type == Block.GRASS_BLOCK || type == Block.DIRT) && centerY < 128 - l - 1) {
                    worldContext.setBlock(centerX, centerY - 1, centerZ, Block.DIRT);
                    l2 = 0;

                    for (i2 = centerY + l; i2 >= centerY + i1; --i2) {
                        for (j2 = centerX - l2; j2 <= centerX + l2; ++j2) {
                            k2 = j2 - centerX;

                            for (int i3 = centerZ - l2; i3 <= centerZ + l2; ++i3) {
                                int j3 = i3 - centerZ;

                                if ((Math.abs(k2) != l2 || Math.abs(j3) != l2 || l2 <= 0) && !LegacyUtil.Block_o(worldContext.getBlock(j2, i2, i3))) {
                                    worldContext.setBlock(j2, i2, i3, Block.SPRUCE_LEAVES);
                                }
                            }
                        }

                        if (l2 >= 1 && i2 == centerY + i1 + 1) {
                            --l2;
                        } else if (l2 < k1) {
                            ++l2;
                        }
                    }

                    for (i2 = 0; i2 < l - 1; ++i2) {
                        type = worldContext.getBlock(centerX, centerY + i2, centerZ);
                        if (type.isAir() || BlockConstants.isLeaves(type)) {
                            worldContext.setBlock(centerX, centerY + i2, centerZ, Block.SPRUCE_LOG);
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
