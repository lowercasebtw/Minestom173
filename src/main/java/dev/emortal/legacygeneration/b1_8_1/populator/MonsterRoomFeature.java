package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class MonsterRoomFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        byte var6 = 3;
        int var7 = random.nextInt(2) + 2;
        int var8 = random.nextInt(2) + 2;
        int var9 = 0;

        for (int var10 = i - var7 - 1; var10 <= i + var7 + 1; var10++) {
            for (int var11 = i2 - 1; var11 <= i2 + var6 + 1; var11++) {
                for (int var12 = i3 - var8 - 1; var12 <= i3 + var8 + 1; var12++) {
                    Material var13 = level.getMaterial(var10, var11, var12);
                    if (var11 == i2 - 1 && !var13.isSolid()) {
                        return false;
                    }

                    if (var11 == i2 + var6 + 1 && !var13.isSolid()) {
                        return false;
                    }

                    if ((var10 == i - var7 - 1 || var10 == i + var7 + 1 || var12 == i3 - var8 - 1 || var12 == i3 + var8 + 1)
                            && var11 == i2
                            && level.m_64558657(var10, var11, var12)
                            && level.m_64558657(var10, var11 + 1, var12)) {
                        var9++;
                    }
                }
            }
        }

        if (var9 >= 1 && var9 <= 5) {
            for (int var19 = i - var7 - 1; var19 <= i + var7 + 1; var19++) {
                for (int var22 = i2 + var6; var22 >= i2 - 1; var22--) {
                    for (int var24 = i3 - var8 - 1; var24 <= i3 + var8 + 1; var24++) {
                        if (var19 != i - var7 - 1
                                && var22 != i2 - 1
                                && var24 != i3 - var8 - 1
                                && var19 != i + var7 + 1
                                && var22 != i2 + var6 + 1
                                && var24 != i3 + var8 + 1) {
                            level.setTile(var19, var22, var24, 0);
                        } else if (var22 >= 0 && !level.getMaterial(var19, var22 - 1, var24).isSolid()) {
                            level.setTile(var19, var22, var24, 0);
                        } else if (level.getMaterial(var19, var22, var24).isSolid()) {
                            if (var22 == i2 - 1 && random.nextInt(4) != 0) {
                                level.setTile(var19, var22, var24, Tile.MOSS_STONE.id);
                            } else {
                                level.setTile(var19, var22, var24, Tile.COBBLESTONE.id);
                            }
                        }
                    }
                }
            }

            for (int var20 = 0; var20 < 2; var20++) {
                for (int var23 = 0; var23 < 3; var23++) {
                    int var25 = i + random.nextInt(var7 * 2 + 1) - var7;
                    int var14 = i3 + random.nextInt(var8 * 2 + 1) - var8;
                    if (level.m_64558657(var25, i2, var14)) {
                        int var15 = 0;
                        if (level.getMaterial(var25 - 1, i2, var14).isSolid()) {
                            var15++;
                        }

                        if (level.getMaterial(var25 + 1, i2, var14).isSolid()) {
                            var15++;
                        }

                        if (level.getMaterial(var25, i2, var14 - 1).isSolid()) {
                            var15++;
                        }

                        if (level.getMaterial(var25, i2, var14 + 1).isSolid()) {
                            var15++;
                        }

                        if (var15 == 1) {
                            level.setTile(var25, i2, var14, Tile.CHEST.id);
                            ChestTileEntity var16 = (ChestTileEntity) level.getTileEntity(var25, i2, var14);
                            if (var16 != null) {
                                for (int var17 = 0; var17 < 8; var17++) {
                                    ItemInstance var18 = this.generateLoot(random);
                                    if (var18 != null) {
                                        var16.setItem(random.nextInt(var16.getContainerSize()), var18);
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }

            level.setTile(i, i2, i3, Tile.SPAWNER.id);
            MobSpawnerTileEntity var21 = (MobSpawnerTileEntity) level.getTileEntity(i, i2, i3);
            var21.setEntityId(this.randomEntityId(random));
            return true;
        } else {
            return false;
        }
    }

    private ItemInstance generateLoot(Random random) {
        int var2 = random.nextInt(11);
        if (var2 == 0) {
            return new ItemInstance(Item.SADDLE);
        } else if (var2 == 1) {
            return new ItemInstance(Item.IRON_INGOT, random.nextInt(4) + 1);
        } else if (var2 == 2) {
            return new ItemInstance(Item.BREAD);
        } else if (var2 == 3) {
            return new ItemInstance(Item.WHEAT, random.nextInt(4) + 1);
        } else if (var2 == 4) {
            return new ItemInstance(Item.SULPHUR, random.nextInt(4) + 1);
        } else if (var2 == 5) {
            return new ItemInstance(Item.STRING, random.nextInt(4) + 1);
        } else if (var2 == 6) {
            return new ItemInstance(Item.BUCKET);
        } else if (var2 == 7 && random.nextInt(100) == 0) {
            return new ItemInstance(Item.GOLD_APPLE);
        } else if (var2 == 8 && random.nextInt(2) == 0) {
            return new ItemInstance(Item.REDSTONE, random.nextInt(4) + 1);
        } else if (var2 == 9 && random.nextInt(10) == 0) {
            return new ItemInstance(Item.items[Item.RECORD_13.id + random.nextInt(2)]);
        } else {
            return var2 == 10 ? new ItemInstance(Item.DYE, 1, 3) : null;
        }
    }

    private String randomEntityId(Random random) {
        int var2 = random.nextInt(4);
        if (var2 == 0) {
            return "Skeleton";
        } else if (var2 == 1) {
            return "Zombie";
        } else if (var2 == 2) {
            return "Zombie";
        } else {
            return var2 == 3 ? "Spider" : "";
        }
    }
}
