package dev.emortal.legacygeneration.b1_8_1;

import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.biome.Biome;

public enum BiomeBase181 {
    OCEAN(Biome.OCEAN),
    PLAINS(Biome.PLAINS),
    DESERT(Biome.DESERT),
    EXTREME_HILLS(Biome.EXTREME_HILLS),
    FOREST(Biome.FOREST),
    TAIGA(Biome.TAIGA),
    SWAMPLAND(Biome.SWAMP),
    RIVER(Biome.RIVER),
    HELL(Biome.NETHER_WASTES),
    SKY(Biome.THE_END);

    private final RegistryKey<Biome> biome;

    BiomeBase181(final RegistryKey<Biome> biome) {
        this.biome = biome;
    }
}
