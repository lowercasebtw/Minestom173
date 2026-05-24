package dev.emortal.legacygeneration.b1_7_3.populator;

import dev.emortal.legacygeneration.util.WorldContext;

public interface WorldGenerator {
    boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ);

    default void scale(final double scaleX, final double scaleY, final double scaleZ) {
    }
}
