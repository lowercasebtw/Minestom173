package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class FlowerFeature implements WorldGenerator {
    private final int tile;

    public FlowerFeature(int tile) {
        this.tile = tile;
    }

    @Override
    public boolean populate(WorldContext worldContext, int i, int i2, int i3) {
        for (int var6 = 0; var6 < 64; var6++) {
            final Random random = worldContext.random();
            int var7 = i + random.nextInt(8) - random.nextInt(8);
            int var8 = i2 + random.nextInt(4) - random.nextInt(4);
            int var9 = i3 + random.nextInt(8) - random.nextInt(8);
            if (level.m_64558657(var7, var8, var9) && ((Bush) Tile.tiles[this.tile]).canPlace(level, var7, var8, var9)) {
                level.setTileNoUpdate(var7, var8, var9, this.tile);
            }
        }

        return true;
    }
}
