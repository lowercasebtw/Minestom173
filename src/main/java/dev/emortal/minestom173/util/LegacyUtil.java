package dev.emortal.minestom173.util;

import dev.emortal.minestom173.generator.WorldContext;
import net.minestom.server.instance.block.Block;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class LegacyUtil {
    // Source code for b173 server can be found here: https://github.com/Bukkit/mc-dev/tree/1a792ed860ebe2c6d4c40c52f3bc7b9e0789ca23

    // NOTE: The following methods are supposed to mirror beta 1.7.3 behaviour! They are not guaranteed to mirror
    // NEW behaviour.
    private static final Set<Block> REPLACEABLE_BLOCKS = new HashSet<>();
    private static final Set<Block> NON_BUILDABLE_BLOCKS = new HashSet<>();
    private static final Set<Block> Block_F = new HashSet<>();
    private static final Set<Block> NOT_Block_b = new HashSet<>();
    private static final Map<Block, Integer> BlockFire_a = new HashMap<>();
    private static final Set<Block> NOT_Block_a = new HashSet<>();

    static {
        REPLACEABLE_BLOCKS.addAll(BlockConstants.AIRS);
        REPLACEABLE_BLOCKS.add(Block.WATER);
        REPLACEABLE_BLOCKS.add(Block.LAVA);
        REPLACEABLE_BLOCKS.add(Block.SNOW); // TODO SNOW_BLOCK?
        REPLACEABLE_BLOCKS.add(Block.FIRE);

        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.AIRS);
        NON_BUILDABLE_BLOCKS.add(Block.FIRE);
        NON_BUILDABLE_BLOCKS.add(Block.WATER);
        NON_BUILDABLE_BLOCKS.add(Block.LAVA);
        NON_BUILDABLE_BLOCKS.add(Block.NETHER_PORTAL);
        NON_BUILDABLE_BLOCKS.add(Block.SNOW); // TODO SNOW_BLOCK?
        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.BUTTONS);
        NON_BUILDABLE_BLOCKS.add(Block.REPEATER);
        NON_BUILDABLE_BLOCKS.add(Block.LADDER);
        NON_BUILDABLE_BLOCKS.add(Block.LEVER);
        NON_BUILDABLE_BLOCKS.add(Block.RAIL);
        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.RAILS);
        NON_BUILDABLE_BLOCKS.add(Block.REDSTONE_WIRE);
        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.GENERIC_TORCHS);
        NON_BUILDABLE_BLOCKS.add(Block.SUGAR_CANE);
        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.FLOWERS);
        NON_BUILDABLE_BLOCKS.add(Block.RED_MUSHROOM);
        NON_BUILDABLE_BLOCKS.add(Block.BROWN_MUSHROOM);
        NON_BUILDABLE_BLOCKS.add(Block.DEAD_BUSH);
        NON_BUILDABLE_BLOCKS.add(Block.SHORT_GRASS);
        NON_BUILDABLE_BLOCKS.add(Block.FERN);
        NON_BUILDABLE_BLOCKS.addAll(BlockConstants.SAPLINGS);
        NON_BUILDABLE_BLOCKS.add(Block.WHEAT);

        Block_F.add(Block.CACTUS);
        Block_F.add(Block.SNOW); // TODO SNOW_BLOCK?
        Block_F.add(Block.ICE);
        Block_F.add(Block.TNT);
        Block_F.add(Block.GLASS);
        Block_F.addAll(BlockConstants.LEAVES);

        NOT_Block_b.addAll(BlockConstants.BEDS);
        NOT_Block_b.addAll(BlockConstants.BUTTONS);
        NOT_Block_b.add(Block.CACTUS);
        NOT_Block_b.add(Block.CAKE);
        NOT_Block_b.add(Block.REPEATER);
        NOT_Block_b.addAll(BlockConstants.DOORS);
        NOT_Block_b.addAll(BlockConstants.WOODEN_FENCES);
        NOT_Block_b.add(Block.FIRE);
        NOT_Block_b.addAll(BlockConstants.FLOWERS);
        NOT_Block_b.add(Block.WHEAT);
        NOT_Block_b.add(Block.DEAD_BUSH);
        NOT_Block_b.add(Block.SHORT_GRASS);
        NOT_Block_b.add(Block.FERN);
        NOT_Block_b.add(Block.RED_MUSHROOM);
        NOT_Block_b.add(Block.BROWN_MUSHROOM);
        NOT_Block_b.addAll(BlockConstants.SAPLINGS);
        NOT_Block_b.add(Block.WATER);
        NOT_Block_b.add(Block.LAVA);
        NOT_Block_b.add(Block.LADDER);
        NOT_Block_b.add(Block.LEVER);
        NOT_Block_b.addAll(BlockConstants.RAILS);
        NOT_Block_b.add(Block.PISTON);
        NOT_Block_b.add(Block.STICKY_PISTON);
        NOT_Block_b.add(Block.PISTON_HEAD);
        NOT_Block_b.add(Block.MOVING_PISTON);
        NOT_Block_b.add(Block.NETHER_PORTAL);
        NOT_Block_b.addAll(BlockConstants.PRESSURE_PLATES);
        NOT_Block_b.add(Block.REDSTONE_WIRE);
        NOT_Block_b.add(Block.SUGAR_CANE);
        NOT_Block_b.addAll(BlockConstants.SIGNS);
        NOT_Block_b.add(Block.SNOW); // TODO SNOW_BLOCK?
        NOT_Block_b.add(Block.FARMLAND);
        NOT_Block_b.addAll(BlockConstants.WOODEN_STAIRS);
        // TODO slabs
        NOT_Block_b.addAll(BlockConstants.GENERIC_TORCHS);
        NOT_Block_b.addAll(BlockConstants.TRAP_DOORS);
        NOT_Block_b.add(Block.COBWEB);

        /*
        this.a(Block.WOOD.id, 5, 20);
        this.a(Block.FENCE.id, 5, 20);
        this.a(Block.WOOD_STAIRS.id, 5, 20);
        this.a(Block.LOG.id, 5, 5);
        this.a(Block.LEAVES.id, 30, 60);
        this.a(Block.BOOKSHELF.id, 30, 20);
        this.a(Block.TNT.id, 15, 100);
        this.a(Block.LONG_GRASS.id, 60, 100);
        this.a(Block.WOOL.id, 30, 60);
         */
        for (final Block wood : BlockConstants.WOODS) {
            BlockFire_a.put(wood, 5);
        }

        for (final Block fence : BlockConstants.WOODEN_FENCES) {
            BlockFire_a.put(fence, 5);
        }

        for (final Block stair : BlockConstants.WOODEN_STAIRS) {
            BlockFire_a.put(stair, 5);
        }

        for (final Block log : BlockConstants.LOGS) {
            BlockFire_a.put(log, 5);
        }

        for (final Block leaves : BlockConstants.LEAVES) {
            BlockFire_a.put(leaves, 30);
        }

        BlockFire_a.put(Block.BOOKSHELF, 30);
        BlockFire_a.put(Block.TNT, 15);
        BlockFire_a.put(Block.SHORT_GRASS, 60);
        BlockFire_a.put(Block.FERN, 60);
        for (final Block wool : BlockConstants.WOOLS) {
            BlockFire_a.put(wool, 30);
        }

        NOT_Block_a.addAll(BlockConstants.BEDS);
        NOT_Block_a.addAll(BlockConstants.BUTTONS);
        NOT_Block_a.add(Block.CACTUS);
        NOT_Block_a.add(Block.CAKE);
        NOT_Block_a.add(Block.REPEATER);
        NOT_Block_a.addAll(BlockConstants.DOORS);
        NOT_Block_a.addAll(BlockConstants.WOODEN_FENCES);
        NOT_Block_a.add(Block.FIRE);
        NOT_Block_a.addAll(BlockConstants.FLOWERS);
        NOT_Block_a.add(Block.WHEAT);
        NOT_Block_a.add(Block.DEAD_BUSH);
        NOT_Block_a.add(Block.SHORT_GRASS);
        NOT_Block_a.add(Block.FERN);
        NOT_Block_a.add(Block.RED_MUSHROOM);
        NOT_Block_a.add(Block.BROWN_MUSHROOM);
        NOT_Block_a.addAll(BlockConstants.SAPLINGS);
        NOT_Block_a.add(Block.WATER);
        NOT_Block_a.add(Block.LAVA);
        NOT_Block_a.add(Block.LADDER);
        NOT_Block_a.add(Block.LEVER);
        NOT_Block_a.addAll(BlockConstants.RAILS);
        NOT_Block_a.add(Block.PISTON);
        NOT_Block_a.add(Block.STICKY_PISTON);
        NOT_Block_a.add(Block.PISTON_HEAD);
        NOT_Block_a.add(Block.MOVING_PISTON);
        NOT_Block_a.add(Block.NETHER_PORTAL);
        NOT_Block_a.addAll(BlockConstants.PRESSURE_PLATES);
        NOT_Block_a.add(Block.REDSTONE_WIRE);
        NOT_Block_a.add(Block.SUGAR_CANE);
        NOT_Block_a.addAll(BlockConstants.SIGNS);
        NOT_Block_a.add(Block.SNOW); // TODO SNOW_BLOCK?
        NOT_Block_a.add(Block.FARMLAND);
        NOT_Block_a.addAll(BlockConstants.WOODEN_STAIRS);
        // TODO slabs
        NOT_Block_a.addAll(BlockConstants.GENERIC_TORCHS);
        NOT_Block_a.addAll(BlockConstants.TRAP_DOORS);
        NOT_Block_a.add(Block.COBWEB);

        // new additions
        NOT_Block_a.add(Block.GLASS);
        NOT_Block_a.add(Block.ICE);
        NOT_Block_a.add(Block.NETHER_PORTAL);
        //NOT_Block_a.addAll(BlockConstants.LEAVES); // overrides but always is true
        NOT_Block_a.add(Block.SPAWNER);
    }

    public static boolean Block_isReplacable(final Block Block) {
        return REPLACEABLE_BLOCKS.contains(Block);
    }

    public static boolean Block_isBuildable(final Block Block) {
        return !NON_BUILDABLE_BLOCKS.contains(Block);
    }

    public static boolean Block_b(final Block Block) {
        return !NOT_Block_b.contains(Block);
    }

    public static boolean World_e(final WorldContext worldContext, final int x, final int y, final int z) {
        final Block block = worldContext.getBlock(x, y, z);
        if (!block.isAir()) {
            return (!Block_F.contains(block) && Block_isSolid(block)) && Block_b(block);
        } else {
            return false;
        }
    }

    public static boolean BlockFire_b(final WorldContext worldContext, final int x, final int y, final int z) {
        return BlockFire_a.getOrDefault(worldContext.getBlock(x, y, z), -1) > 0;
    }

    public static boolean Block_o(final Block block) {
        // As there is no block with air, mojang opted to use a boolean array to avoid NPE... And to decide that air should return false
        return !block.isAir() && Block_a(block);
    }

    public static boolean Block_a(final Block block) {
        if (!block.isAir()) {
            return !NOT_Block_a.contains(block);
        } else {
            throw new NullPointerException();
        }
    }

    public static boolean Block_isSolid(final Block Block) {
        return Block_isBuildable(Block);
    }

    public static boolean BlockChest_g(final WorldContext worldContext, final int x, final int y, final int z) {
        return worldContext.getBlock(x, y, z).compare(Block.CHEST) && (worldContext.getBlock(x - 1, y, z).compare(Block.CHEST) || (worldContext.getBlock(x + 1, y, z).compare(Block.CHEST) || (worldContext.getBlock(x, y, z - 1).compare(Block.CHEST) || worldContext.getBlock(x, y, z + 1).compare(Block.CHEST))));
    }

    public static boolean BlockTorch_g(final WorldContext worldContext, final int x, final int y, final int z) {
        return World_e(worldContext, x, y, z) || BlockConstants.isWoodenFence(worldContext.getBlock(x, y, z));
    }

    public static boolean BlockFlower_c(final Block type, final Block param) {
        if (type == Block.WHEAT) {
            return param == Block.FARMLAND;
        } else if (type == Block.DEAD_BUSH) {
            return param == Block.SAND;
        } else if (type == Block.RED_MUSHROOM || type == Block.BROWN_MUSHROOM) {
            return Block_o(param);
        } else {
            return param == Block.GRASS_BLOCK || param == Block.DIRT || param == Block.FARMLAND;
        }
    }

    public static boolean BlockFlower_f(final WorldContext worldContext, int minHeight, int maxHeight, final int x, final int y, final int z, final Block type) {
        if (type == Block.RED_MUSHROOM || type == Block.BROWN_MUSHROOM) {
            return y >= minHeight && y < (maxHeight + 1) && worldContext.getBlockLight(x, y, z) < 13 && BlockFlower_c(type, worldContext.getBlock(x, y - 1, z));
        } else if (y == 0) {
            return false;
        } else {
            // default
            // we can always assume the chunk is loaded when calling
            return BlockFlower_c(type, worldContext.getBlock(x, y - 1, z));
        }
    }

    public static boolean BlockCactus_f(final WorldContext worldContext, final int x, final int y, final int z) {
        if (Block_isBuildable(worldContext.getBlock(x - 1, y, z))) {
            return false;
        } else if (Block_isBuildable(worldContext.getBlock(x + 1, y, z))) {
            return false;
        } else if (Block_isBuildable(worldContext.getBlock(x, y, z - 1))) {
            return false;
        } else if (Block_isBuildable(worldContext.getBlock(x, y, z + 1))) {
            return false;
        } else {
            Block l = worldContext.getBlock(x, y - 1, z);

            return l == Block.CACTUS || l == Block.SAND;
        }
    }

    public static boolean Block_canPlace(final WorldContext worldContext, int maxHeight, final int x, final int y, final int z, final Block block) {
        if (BlockConstants.isButton(block)) {
            return World_e(worldContext, x - 1, y, z) || (World_e(worldContext, x + 1, y, z) || (World_e(worldContext, x, y, z - 1) || World_e(worldContext, x, y, z + 1)));
        } else if (block.compare(Block.CACTUS)) {
            return Block_isReplacable(worldContext.getBlock(x, y, z)) && BlockCactus_f(worldContext, x, y, z);
        } else if (block.compare(Block.CAKE)) {
            return Block_isReplacable(worldContext.getBlock(x, y, z)) && Block_isBuildable(worldContext.getBlock(x, y - 1, z));
        } else if (block.compare(Block.CHEST)) {
            int l = 0;

            if (worldContext.getBlock(x - 1, y, z).compare(net.minestom.server.instance.block.Block.CHEST)) {
                ++l;
            }

            if (worldContext.getBlock(x + 1, y, z).compare(net.minestom.server.instance.block.Block.CHEST)) {
                ++l;
            }

            if (worldContext.getBlock(x, y, z - 1).compare(net.minestom.server.instance.block.Block.CHEST)) {
                ++l;
            }

            if (worldContext.getBlock(x, y, z + 1).compare(net.minestom.server.instance.block.Block.CHEST)) {
                ++l;
            }

            return l <= 1 && (!BlockChest_g(worldContext, x - 1, y, z) && (!BlockChest_g(worldContext, x + 1, y, z) && (!BlockChest_g(worldContext, x, y, z - 1) && !BlockChest_g(worldContext, x, y, z + 1))));
        } else if (block == Block.REPEATER) {
            return World_e(worldContext, x, y - 1, z) && Block_isReplacable(worldContext.getBlock(x, y, z));
        } else if (BlockConstants.isDoor(block)) {
            return y < maxHeight && World_e(worldContext, x, y - 1, z) && Block_isReplacable(worldContext.getBlock(x, y, z)) && Block_isReplacable(worldContext.getBlock(x, y + 1, z));
        } else if (block == Block.FIRE) {
            if (World_e(worldContext, x, y - 1, z)) {
                return true;
            } else {
                return BlockFire_b(worldContext, x + 1, y, z) || (BlockFire_b(worldContext, x - 1, y, z) || (BlockFire_b(worldContext, x, y - 1, z) || (BlockFire_b(worldContext, x, y + 1, z) || (BlockFire_b(worldContext, x, y, z - 1) || BlockFire_b(worldContext, x, y, z + 1)))));
            }
        } else if (BlockConstants.isFlower(block)) {
            if (!Block_isReplacable(worldContext.getBlock(x, y, z))) {
                return false;
            } else {
                final Block below = worldContext.getBlock(x, y - 1, z);
                return below == Block.GRASS_BLOCK || below == Block.DIRT || below == Block.FARMLAND;
            }
        } else if (block == Block.LADDER) {
            return World_e(worldContext, x - 1, y, z) || (World_e(worldContext, x + 1, y, z) || (World_e(worldContext, x, y, z - 1) || World_e(worldContext, x, y, z + 1)));
        } else if (block == Block.LEVER) {
            return World_e(worldContext, x - 1, y, z) || (World_e(worldContext, x + 1, y, z) || (World_e(worldContext, x, y, z - 1) || (World_e(worldContext, x, y, z + 1) || World_e(worldContext, x, y - 1, z))));
        } else if (BlockConstants.isRail(block)) {
            // locked_chest no longer exists.
            return World_e(worldContext, x, y - 1, z);
        } else if (block == Block.PISTON_HEAD || block == Block.MOVING_PISTON) {
            return false;
        } else if (BlockConstants.isPressurePlate(block)) {
            return World_e(worldContext, x, y - 1, z);
        } else if (block == Block.CARVED_PUMPKIN) {
            return Block_isReplacable(worldContext.getBlock(x, y, z)) && World_e(worldContext, x, y - 1, z);
        } else if (block == Block.REDSTONE_WIRE) {
            return World_e(worldContext, x, y - 1, z);
        } else if (block == Block.SUGAR_CANE) {
            final Block below = worldContext.getBlock(x, y - 1, z);
            return below == Block.SUGAR_CANE || ((below == Block.GRASS_BLOCK || below == Block.DIRT) && (worldContext.getBlock(x - 1, y - 1, z) == Block.WATER || (worldContext.getBlock(x + 1, y - 1, z) == Block.WATER || (worldContext.getBlock(x, y - 1, z - 1) == Block.WATER || worldContext.getBlock(x, y - 1, z + 1) == Block.WATER))));
        } else if (block == Block.SNOW) { // TODO SNOW_BLOCK?
            final Block below = worldContext.getBlock(x, y - 1, z);
            return !below.isAir() && Block_a(below) && Block_isSolid(below);
        } else if (BlockConstants.isGenericTorch(block)) {
            // stairs maps to either Block.WOOD or Block.COBBLESTONE, depending on the type of the stairs.
            // both do not override canPlace, so they aren't checked here (as they enter the default case)
            return World_e(worldContext, x - 1, y, z) || (World_e(worldContext, x + 1, y, z) || (World_e(worldContext, x, y, z - 1) || (World_e(worldContext, x, y, z + 1) || BlockTorch_g(worldContext, x, y - 1, z))));
        } else {
            // default
            return Block_isReplacable(worldContext.getBlock(x, y, z));
        }
    }

    /*
     * For reference, Chunks used to store block data as a single byte in a giant array. Indexing was done as follows:
     * index = x << 11 | z << 7 | y where x and z are in [0, 15] and y is in [0, 127]
     */

// helper functions to easily convert code using the old byte[] access for chunk data

    // due to very questionable decisions by spigot, the getHighestBlockYAt function was changed to return
    // the actual block y, after returning the block y + 1 for about 9 years. Move the correction here just in case
    // spigot breaks it again, so we can account for it easily.
    public static int getHighestBlockYAt(final WorldContext worldContext, final int minHeight, final int maxHeight, final int x, final int z) {
        // TODO: double check this is correct
        for (int i = minHeight; i < maxHeight; i++) {
            if (worldContext.getBlock(x, i, z, Block.Getter.Condition.TYPE).isAir()) {
                return i;
            }
        }

        return 1;
    }
}