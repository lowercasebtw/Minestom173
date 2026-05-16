package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.entity.EntityType;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.Random;

public class WorldGenDungeons implements WorldGenerator {
    public WorldGenDungeons() {
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        byte b0 = 3;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        int j1 = 0;

        int k1;
        int l1;
        int i2;

        for (k1 = centerX - l - 1; k1 <= centerX + l + 1; ++k1) {
            for (l1 = centerY - 1; l1 <= centerY + b0 + 1; ++l1) {
                for (i2 = centerZ - i1 - 1; i2 <= centerZ + i1 + 1; ++i2) {
                    Block material = worldContext.getBlock(k1, l1, i2);

                    if (l1 == centerY - 1 && !LegacyUtil.Block_isBuildable(material)) {
                        return false;
                    }

                    if (l1 == centerY + b0 + 1 && !LegacyUtil.Block_isBuildable(material)) {
                        return false;
                    }

                    if ((k1 == centerX - l - 1 || k1 == centerX + l + 1 || i2 == centerZ - i1 - 1 || i2 == centerZ + i1 + 1) && l1 == centerY && worldContext.getBlock(k1, l1, i2).isAir() && worldContext.getBlock(k1, l1 + 1, i2).isAir()) {
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5) {
            for (k1 = centerX - l - 1; k1 <= centerX + l + 1; ++k1) {
                for (l1 = centerY + b0; l1 >= centerY - 1; --l1) {
                    for (i2 = centerZ - i1 - 1; i2 <= centerZ + i1 + 1; ++i2) {
                        if (k1 != centerX - l - 1 && l1 != centerY - 1 && i2 != centerZ - i1 - 1 && k1 != centerX + l + 1 && l1 != centerY + b0 + 1 && i2 != centerZ + i1 + 1) {
                            worldContext.setBlock(k1, l1, i2, Block.AIR);
                        } else if (l1 >= 0 && !LegacyUtil.Block_isBuildable(worldContext.getBlock(k1, l1 - 1, i2))) {
                            worldContext.setBlock(k1, l1, i2, Block.AIR);
                        } else if (LegacyUtil.Block_isBuildable(worldContext.getBlock(k1, l1, i2))) {
                            if (l1 == centerY - 1 && random.nextInt(4) != 0) {
                                worldContext.setBlock(k1, l1, i2, Block.MOSSY_COBBLESTONE);
                            } else {
                                worldContext.setBlock(k1, l1, i2, Block.COBBLESTONE);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2) {
                l1 = 0;

                while (true) {
                    if (l1 < 3) {
                        label204:
                        {
                            i2 = centerX + random.nextInt(l * 2 + 1) - l;
                            int j2 = centerZ + random.nextInt(i1 * 2 + 1) - i1;

                            if (worldContext.getBlock(i2, centerY, j2).isAir()) {
                                int k2 = 0;

                                if (LegacyUtil.Block_isBuildable(worldContext.getBlock(i2 - 1, centerY, j2))) {
                                    ++k2;
                                }

                                if (LegacyUtil.Block_isBuildable(worldContext.getBlock(i2 + 1, centerY, j2))) {
                                    ++k2;
                                }

                                if (LegacyUtil.Block_isBuildable(worldContext.getBlock(i2, centerY, j2 - 1))) {
                                    ++k2;
                                }

                                if (LegacyUtil.Block_isBuildable(worldContext.getBlock(i2, centerY, j2 + 1))) {
                                    ++k2;
                                }

                                if (k2 == 1) {
                                    worldContext.setBlock(i2, centerY, j2, Block.CHEST);

                                    // TODO: this

//                                    Chest tileentitychest = (Chest)world.getBlockState(i2, centerY, j2);
//
//                                    for (int l2 = 0; l2 < 8; ++l2) {
//                                        ItemStack itemstack = this.getRandomItem(random);
//
//                                        if (itemstack != null) {
//                                            tileentitychest.getBlockInventory().setItem(random.nextInt(tileentitychest.getBlockInventory().getSize()), itemstack);
//                                        }
//                                    }
                                    break label204;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            // TODO: this
//            worldContext.setBlock(centerX, centerY, centerZ, BlockConstants.SPAWNER, false);
//            CreatureSpawner tileentitymobspawner = (CreatureSpawner)world.getBlockState(centerX, centerY, centerZ);
//
//            tileentitymobspawner.setSpawnedType(this.getRandomSpawnerType(random));
//            tileentitymobspawner.update(true, false);
            return true;
        } else {
            return false;
        }
    }

    private ItemStack getRandomItem(Random random) {
        int i = random.nextInt(11);

        return i == 0 ? ItemStack.of(Material.SADDLE) : (i == 1 ? ItemStack.of(Material.IRON_INGOT, random.nextInt(4) + 1) : (i == 2 ? ItemStack.of(Material.BREAD) : (i == 3 ? ItemStack.of(Material.WHEAT, random.nextInt(4) + 1) : (i == 4 ? ItemStack.of(Material.GUNPOWDER, random.nextInt(4) + 1) : (i == 5 ? ItemStack.of(Material.STRING, random.nextInt(4) + 1) : (i == 6 ? ItemStack.of(Material.BUCKET) : (i == 7 && random.nextInt(100) == 0 ? ItemStack.of(Material.GOLDEN_APPLE) : (i == 8 && random.nextInt(2) == 0 ? ItemStack.of(Material.REDSTONE, random.nextInt(4) + 1) : (i == 9 && random.nextInt(10) == 0 ? /*new ItemStack(Item.byId[Item.GOLD_RECORD.id + random.nextInt(2)])*/ ItemStack.of(random.nextInt(2) == 0 ? Material.MUSIC_DISC_13 : Material.MUSIC_DISC_CAT) : (i == 10 ? ItemStack.of(Material.COCOA_BEANS) : null))))))))));
    }

    private EntityType getRandomSpawnerType(Random random) {
        return switch (random.nextInt(4)) {
            case 0 -> EntityType.SKELETON;
            case 1, 2 -> EntityType.ZOMBIE;
            case 3 -> EntityType.SPIDER;
            default -> throw new IllegalStateException("Shouldn't happen");
        };
    }
}
