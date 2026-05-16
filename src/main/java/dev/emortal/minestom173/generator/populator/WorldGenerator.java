package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;

public interface WorldGenerator {
    boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ);

    default void scale(final double scaleX, final double scaleY, final double scaleZ) {
    }
}
