package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.LegacyUtil;
import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class BirchFeature implements WorldGenerator {
    @Override
    public boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ) {
        final Random random = worldContext.random();
        int var6 = random.nextInt(3) + 5;
        boolean var7 = true;
        if (centerY >= 1 && centerY + var6 + 1 <= 128) {
            for (int var8 = centerY; var8 <= centerY + 1 + var6; var8++) {
                byte var9 = 1;
                if (var8 == centerY) {
                    var9 = 0;
                }

                if (var8 >= centerY + 1 + var6 - 2) {
                    var9 = 2;
                }

                for (int var10 = centerX - var9; var10 <= centerX + var9 && var7; var10++) {
                    for (int var11 = centerZ - var9; var11 <= centerZ + var9 && var7; var11++) {
                        if (var8 >= 0 && var8 < 128) {
                            Block var12 = worldContext.getBlock(var10, var8, var11);
                            if (!var12.isAir() && var12 != Block.OAK_LEAVES) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7) {
                return false;
            } else {
                Block var16 = worldContext.getBlock(centerX, centerY - 1, centerZ);
                if ((var16 == Block.GRASS_BLOCK || var16 == Block.DIRT) && centerY < 128 - var6 - 1) {
                    worldContext.setBlock(centerX, centerY - 1, centerZ, Block.DIRT);
                    for (int var17 = centerY - 3 + var6; var17 <= centerY + var6; var17++) {
                        int var19 = var17 - (centerY + var6);
                        int var21 = 1 - var19 / 2;

                        for (int var22 = centerX - var21; var22 <= centerX + var21; var22++) {
                            int var13 = var22 - centerX;

                            for (int var14 = centerZ - var21; var14 <= centerZ + var21; var14++) {
                                int var15 = var14 - centerZ;
                                if ((Math.abs(var13) != var21 || Math.abs(var15) != var21 || random.nextInt(2) != 0 && var19 != 0) && !LegacyUtil.Block_isSolid(worldContext.getBlock(var22, var17, var14))) {
                                    worldContext.setBlock(var22, var17, var14, Block.BIRCH_LEAVES);
                                }
                            }
                        }
                    }

                    for (int var18 = 0; var18 < var6; var18++) {
                        Block var20 = worldContext.getBlock(centerX, centerY + var18, centerZ);
                        if (var20.isAir() || var20 == Block.OAK_LEAVES) {
                            worldContext.setBlock(centerX, centerY + var18, centerZ, Block.BIRCH_LOG);
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
