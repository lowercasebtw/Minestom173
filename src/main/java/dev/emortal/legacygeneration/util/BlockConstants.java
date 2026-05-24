package dev.emortal.legacygeneration.util;

import net.minestom.server.instance.block.Block;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BlockConstants {
    /*
     * In order to avoid map lookups for converting from Block->NMS IBlockData we cache the block datas
     * here.
     */
    public static final Set<Block> AIRS = new HashSet<>();
    public static final Set<Block> LIQUIDS = new HashSet<>();
    public static final Set<Block> SAPLINGS = new HashSet<>();
    public static final Set<Block> LEAVES = new HashSet<>();
    public static final Set<Block> WOODS = new HashSet<>();
    public static final Set<Block> LOGS = new HashSet<>();
    public static final Set<Block> BUTTONS = new HashSet<>();
    public static final Set<Block> WOODEN_FENCES = new HashSet<>();
    public static final Set<Block> WOODEN_STAIRS = new HashSet<>();
    public static final Set<Block> FLOWERS = new HashSet<>();
    public static final Set<Block> RAILS = new HashSet<>();
    public static final Set<Block> WOOLS = new HashSet<>();
    public static final Set<Block> DOORS = new HashSet<>();
    public static final Set<Block> PRESSURE_PLATES = new HashSet<>();
    public static final Set<Block> TRAP_DOORS = new HashSet<>();
    public static final Set<Block> GENERIC_TORCHS = new HashSet<>();
    public static final Set<Block> BEDS = new HashSet<>();
    public static final Set<Block> SIGNS = new HashSet<>();

    static {
        AIRS.add(Block.AIR);
        AIRS.add(Block.VOID_AIR);
        AIRS.add(Block.CAVE_AIR);

        LIQUIDS.add(Block.WATER);
        LIQUIDS.add(Block.LAVA);

        SAPLINGS.add(Block.ACACIA_SAPLING);
        SAPLINGS.add(Block.BIRCH_SAPLING);
        SAPLINGS.add(Block.DARK_OAK_SAPLING);
        SAPLINGS.add(Block.JUNGLE_SAPLING);
        SAPLINGS.add(Block.SPRUCE_SAPLING);
        SAPLINGS.add(Block.OAK_SAPLING);

        LEAVES.add(Block.ACACIA_LEAVES);
        LEAVES.add(Block.BIRCH_LEAVES);
        LEAVES.add(Block.DARK_OAK_LEAVES);
        LEAVES.add(Block.JUNGLE_LEAVES);
        LEAVES.add(Block.SPRUCE_LEAVES);
        LEAVES.add(Block.OAK_LEAVES);

        WOODS.add(Block.ACACIA_WOOD);
        WOODS.add(Block.BIRCH_WOOD);
        WOODS.add(Block.DARK_OAK_WOOD);
        WOODS.add(Block.JUNGLE_WOOD);
        WOODS.add(Block.SPRUCE_WOOD);
        WOODS.add(Block.OAK_WOOD);

        LOGS.add(Block.ACACIA_LOG);
        LOGS.add(Block.BIRCH_LOG);
        LOGS.add(Block.DARK_OAK_LOG);
        LOGS.add(Block.JUNGLE_LOG);
        LOGS.add(Block.SPRUCE_LOG);
        LOGS.add(Block.OAK_LOG);

        BUTTONS.add(Block.STONE_BUTTON);
        BUTTONS.add(Block.ACACIA_BUTTON);
        BUTTONS.add(Block.BIRCH_BUTTON);
        BUTTONS.add(Block.DARK_OAK_BUTTON);
        BUTTONS.add(Block.JUNGLE_BUTTON);
        BUTTONS.add(Block.SPRUCE_BUTTON);
        BUTTONS.add(Block.OAK_BUTTON);

        WOODEN_FENCES.add(Block.ACACIA_FENCE);
        WOODEN_FENCES.add(Block.BIRCH_FENCE);
        WOODEN_FENCES.add(Block.DARK_OAK_FENCE);
        WOODEN_FENCES.add(Block.JUNGLE_FENCE);
        WOODEN_FENCES.add(Block.SPRUCE_FENCE);
        WOODEN_FENCES.add(Block.OAK_FENCE);

        WOODEN_STAIRS.add(Block.ACACIA_STAIRS);
        WOODEN_STAIRS.add(Block.BIRCH_STAIRS);
        WOODEN_STAIRS.add(Block.DARK_OAK_STAIRS);
        WOODEN_STAIRS.add(Block.JUNGLE_STAIRS);
        WOODEN_STAIRS.add(Block.SPRUCE_STAIRS);
        WOODEN_STAIRS.add(Block.OAK_STAIRS);

        FLOWERS.add(Block.DANDELION);
        FLOWERS.add(Block.POPPY);
        FLOWERS.add(Block.BLUE_ORCHID);
        FLOWERS.add(Block.ALLIUM);
        FLOWERS.add(Block.AZURE_BLUET);
        FLOWERS.add(Block.RED_TULIP);
        FLOWERS.add(Block.ORANGE_TULIP);
        FLOWERS.add(Block.WHITE_TULIP);
        FLOWERS.add(Block.PINK_TULIP);
        FLOWERS.add(Block.OXEYE_DAISY);
        FLOWERS.add(Block.CORNFLOWER);
        FLOWERS.add(Block.LILY_OF_THE_VALLEY);

        RAILS.add(Block.RAIL);
        RAILS.add(Block.ACTIVATOR_RAIL);
        RAILS.add(Block.DETECTOR_RAIL);
        RAILS.add(Block.POWERED_RAIL);

        WOOLS.add(Block.BLACK_WOOL);
        WOOLS.add(Block.BLUE_WOOL);
        WOOLS.add(Block.BROWN_WOOL);
        WOOLS.add(Block.CYAN_WOOL);
        WOOLS.add(Block.GRAY_WOOL);
        WOOLS.add(Block.GREEN_WOOL);
        WOOLS.add(Block.LIGHT_BLUE_WOOL);
        WOOLS.add(Block.LIGHT_GRAY_WOOL);
        WOOLS.add(Block.LIME_WOOL);
        WOOLS.add(Block.MAGENTA_WOOL);
        WOOLS.add(Block.ORANGE_WOOL);
        WOOLS.add(Block.PINK_WOOL);
        WOOLS.add(Block.PURPLE_WOOL);
        WOOLS.add(Block.RED_WOOL);
        WOOLS.add(Block.WHITE_WOOL);
        WOOLS.add(Block.YELLOW_WOOL);

        DOORS.add(Block.ACACIA_DOOR);
        DOORS.add(Block.BIRCH_DOOR);
        DOORS.add(Block.DARK_OAK_DOOR);
        DOORS.add(Block.JUNGLE_DOOR);
        DOORS.add(Block.SPRUCE_DOOR);
        DOORS.add(Block.OAK_DOOR);
        DOORS.add(Block.IRON_DOOR);

        PRESSURE_PLATES.add(Block.STONE_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.LIGHT_WEIGHTED_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.HEAVY_WEIGHTED_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.ACACIA_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.BIRCH_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.DARK_OAK_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.JUNGLE_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.SPRUCE_PRESSURE_PLATE);
        PRESSURE_PLATES.add(Block.OAK_PRESSURE_PLATE);

        TRAP_DOORS.add(Block.IRON_TRAPDOOR);
        TRAP_DOORS.add(Block.ACACIA_TRAPDOOR);
        TRAP_DOORS.add(Block.BIRCH_TRAPDOOR);
        TRAP_DOORS.add(Block.DARK_OAK_TRAPDOOR);
        TRAP_DOORS.add(Block.JUNGLE_TRAPDOOR);
        TRAP_DOORS.add(Block.SPRUCE_TRAPDOOR);
        TRAP_DOORS.add(Block.OAK_TRAPDOOR);

        GENERIC_TORCHS.addAll(List.of(Block.TORCH, Block.WALL_TORCH, Block.REDSTONE_TORCH, Block.REDSTONE_WALL_TORCH));

        BEDS.addAll(List.of(
                Block.RED_BED,
                Block.BLACK_BED,
                Block.BLUE_BED,
                Block.BROWN_BED,
                Block.CYAN_BED,
                Block.GRAY_BED,
                Block.GREEN_BED,
                Block.LIGHT_BLUE_BED,
                Block.LIGHT_GRAY_BED,
                Block.LIME_BED,
                Block.MAGENTA_BED,
                Block.ORANGE_BED,
                Block.PINK_BED,
                Block.PURPLE_BED,
                Block.WHITE_BED,
                Block.YELLOW_BED
        ));

        SIGNS.addAll(List.of(
                Block.ACACIA_SIGN,
                Block.BIRCH_SIGN,
                Block.DARK_OAK_SIGN,
                Block.JUNGLE_SIGN,
                Block.SPRUCE_SIGN,
                Block.OAK_SIGN,
                Block.ACACIA_WALL_SIGN,
                Block.BIRCH_WALL_SIGN,
                Block.DARK_OAK_WALL_SIGN,
                Block.JUNGLE_WALL_SIGN,
                Block.SPRUCE_WALL_SIGN,
                Block.OAK_WALL_SIGN
        ));
    }

    public static boolean isLiquid(final Block Block) {
        return LIQUIDS.contains(Block);
    }

    public static boolean isLeaves(final Block Block) {
        return LEAVES.contains(Block);
    }

    public static boolean isButton(final Block Block) {
        return BUTTONS.contains(Block);
    }

    public static boolean isWoodenFence(final Block Block) {
        return WOODEN_FENCES.contains(Block);
    }

    public static boolean isFlower(final Block Block) {
        return FLOWERS.contains(Block);
    }

    public static boolean isRail(final Block Block) {
        return RAILS.contains(Block);
    }

    public static boolean isDoor(final Block Block) {
        return DOORS.contains(Block);
    }

    public static boolean isPressurePlate(final Block Block) {
        return PRESSURE_PLATES.contains(Block);
    }

    public static boolean isGenericTorch(final Block Block) {
        return GENERIC_TORCHS.contains(Block);
    }
}
