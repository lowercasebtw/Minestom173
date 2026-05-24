package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class ReedsFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        for (int var6 = 0; var6 < 20; var6++) {
            int var7 = i + random.nextInt(4) - random.nextInt(4);
            int var8 = i2;
            int var9 = i3 + random.nextInt(4) - random.nextInt(4);
            if (level.m_64558657(var7, i2, var9)
                    && (
                    level.getMaterial(var7 - 1, i2 - 1, var9) == Material.WATER
                            || level.getMaterial(var7 + 1, i2 - 1, var9) == Material.WATER
                            || level.getMaterial(var7, i2 - 1, var9 - 1) == Material.WATER
                            || level.getMaterial(var7, i2 - 1, var9 + 1) == Material.WATER
            )) {
                int var10 = 2 + random.nextInt(random.nextInt(3) + 1);

                for (int var11 = 0; var11 < var10; var11++) {
                    if (Tile.REEDS.canPlace(level, var7, var8 + var11, var9)) {
                        level.setTileNoUpdate(var7, var8 + var11, var9, Tile.REEDS.id);
                    }
                }
            }
        }

        return true;
    }
}
