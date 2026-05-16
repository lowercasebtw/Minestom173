plugins {
    id("java")
}

group = "dev.emortal"
version = "1.0.0"

repositories {
}

dependencies {
    compileOnly("net.minestom:minestom:26_1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25)) // Minestom has a minimum Java version of 21
    }
}