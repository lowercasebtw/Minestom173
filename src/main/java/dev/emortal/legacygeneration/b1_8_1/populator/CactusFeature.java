package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class CactusFeature implements WorldGenerator {
    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        for (int var6 = 0; var6 < 10; var6++) {
            int var7 = i + random.nextInt(8) - random.nextInt(8);
            int var8 = i2 + random.nextInt(4) - random.nextInt(4);
            int var9 = i3 + random.nextInt(8) - random.nextInt(8);
            if (level.m_64558657(var7, var8, var9)) {
                int var10 = 1 + random.nextInt(random.nextInt(3) + 1);

                for (int var11 = 0; var11 < var10; var11++) {
                    if (Tile.CACTUS.canPlace(level, var7, var8 + var11, var9)) {
                        level.setTileNoUpdate(var7, var8 + var11, var9, Tile.CACTUS.id);
                    }
                }
            }
        }

        return true;
    }
}
