package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class GrassFeature implements WorldGenerator {
    private final int tile;
    private final int meta;

    public GrassFeature(int tile, int meta) {
        this.tile = tile;
        this.meta = meta;
    }

    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        int var6 = 0;

        while (((var6 = level.getTile(i, i2, i3)) == 0 || var6 == Tile.LEAVES.id) && i2 > 0) {
            i2--;
        }

        for (int var7 = 0; var7 < 128; var7++) {
            int var8 = i + random.nextInt(8) - random.nextInt(8);
            int var9 = i2 + random.nextInt(4) - random.nextInt(4);
            int var10 = i3 + random.nextInt(8) - random.nextInt(8);
            if (level.m_64558657(var8, var9, var10) && ((Bush) Tile.tiles[this.tile]).canPlace(level, var8, var9, var10)) {
                level.setTileAndDataNoUpdate(var8, var9, var10, this.tile, this.meta);
            }
        }

        return true;
    }
}
