package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.WorldGenerator;

import java.util.Random;

public class HellSpringFeature implements WorldGenerator {
    private final int tile;

    public HellSpringFeature(int tile) {
        this.tile = tile;
    }

    @Override
    public boolean populate(Level level, Random random, int i, int i2, int i3) {
        if (level.getTile(i, i2 + 1, i3) != Tile.NETHERRACK.id) {
            return false;
        } else if (level.getTile(i, i2, i3) != 0 && level.getTile(i, i2, i3) != Tile.NETHERRACK.id) {
            return false;
        } else {
            int var6 = 0;
            if (level.getTile(i - 1, i2, i3) == Tile.NETHERRACK.id) {
                var6++;
            }

            if (level.getTile(i + 1, i2, i3) == Tile.NETHERRACK.id) {
                var6++;
            }

            if (level.getTile(i, i2, i3 - 1) == Tile.NETHERRACK.id) {
                var6++;
            }

            if (level.getTile(i, i2, i3 + 1) == Tile.NETHERRACK.id) {
                var6++;
            }

            if (level.getTile(i, i2 - 1, i3) == Tile.NETHERRACK.id) {
                var6++;
            }

            int var7 = 0;
            if (level.m_64558657(i - 1, i2, i3)) {
                var7++;
            }

            if (level.m_64558657(i + 1, i2, i3)) {
                var7++;
            }

            if (level.m_64558657(i, i2, i3 - 1)) {
                var7++;
            }

            if (level.m_64558657(i, i2, i3 + 1)) {
                var7++;
            }

            if (level.m_64558657(i, i2 - 1, i3)) {
                var7++;
            }

            if (var6 == 4 && var7 == 1) {
                level.setTile(i, i2, i3, this.tile);
                level.instaTick = true;
                Tile.tiles[this.tile].tick(level, i, i2, i3, random);
                level.instaTick = false;
            }

            return true;
        }
    }
}
