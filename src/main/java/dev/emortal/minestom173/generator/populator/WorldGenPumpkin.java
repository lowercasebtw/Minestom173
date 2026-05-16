package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.LegacyUtil;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;

import java.util.Random;

public class WorldGenPumpkin implements WorldGenerator {
    private static final Block[] RANDOM_CARVED_PUMPKIN_FACING;

    static {
        final BlockFace[] facings = new BlockFace[]{BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST};
        RANDOM_CARVED_PUMPKIN_FACING = new Block[facings.length];
        for (int i = 0, len = facings.length; i < len; ++i) {
            RANDOM_CARVED_PUMPKIN_FACING[i] = Block.CARVED_PUMPKIN.withProperty("facing", facings[i].name().toLowerCase());
        }
    }

    private final int maxHeight;

    public WorldGenPumpkin(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public static Block getRandomCarvedPumpkin(final Random random) {
        return RANDOM_CARVED_PUMPKIN_FACING[random.nextInt(RANDOM_CARVED_PUMPKIN_FACING.length)];
    }

    @Override
    public boolean populate(final WorldContext worldContext, int centerX, int centerY, int centerZ) {
        final Random random = worldContext.random();
        for (int l = 0; l < 64; ++l) {
            final int blockX = centerX + random.nextInt(8) - random.nextInt(8);
            final int blockY = centerY + random.nextInt(4) - random.nextInt(4);
            final int blockZ = centerZ + random.nextInt(8) - random.nextInt(8);
            if (worldContext.getBlock(blockX, blockY, blockZ).isAir() && worldContext.getBlock(blockX, blockY - 1, blockZ) == Block.GRASS_BLOCK && LegacyUtil.Block_canPlace(worldContext, maxHeight, blockX, blockY, blockZ, Block.PUMPKIN)) {
                worldContext.setBlock(blockX, blockY, blockZ, getRandomCarvedPumpkin(random));
            }
        }

        return true;
    }
}
