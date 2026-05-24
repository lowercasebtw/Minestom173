package dev.emortal.legacygeneration;

import dev.emortal.legacygeneration.util.WorldContext;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.instance.InstanceChunkLoadEvent;
import net.minestom.server.event.trait.InstanceEvent;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.ChunkLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.batch.AbsoluteBlockBatch;
import net.minestom.server.tag.Tag;
import net.minestom.server.timer.TaskSchedule;

import java.util.Objects;

public final class LegacyPopulateHack {
    private static final Tag<Boolean> HAS_POPULATED = Tag.Boolean("hasPopulated");

    private static void setHasPopulated(final Chunk chunk, final boolean value) {
        chunk.setTag(HAS_POPULATED, value);
    }

    private static boolean hasPopulated(final Chunk chunk) {
        return Objects.requireNonNullElse(chunk.getTag(HAS_POPULATED), false);
    }

    private static void runPopulators(final InstanceContainer world, final Chunk chunk) {
        final ChunkGenerator generator = (ChunkGenerator) world.getChunkLoader();
        setHasPopulated(chunk, true);

        final AbsoluteBlockBatch batch = new AbsoluteBlockBatch();
        generator.populateChunk(new WorldContext.Impl(world, batch, world, new WorldContext.LightGetter(world), generator.getRandom()), chunk.getChunkX(), chunk.getChunkZ());
        batch.apply(world, null);
    }

    public static void registerEvents(final EventNode<InstanceEvent> eventNode) {
        eventNode.addListener(InstanceChunkLoadEvent.class, LegacyPopulateHack::handleEvent);
    }

    private static void handleEvent(final InstanceChunkLoadEvent event) {
        event.getInstance().scheduler().buildTask(() -> {
            final InstanceContainer world = (InstanceContainer) event.getInstance();
            final ChunkLoader generator = world.getChunkLoader();
            if (generator instanceof ChunkGenerator) {
                final Chunk center = event.getChunk();
                final int centerX = center.getChunkX();
                final int centerZ = center.getChunkZ();

                setHasPopulated(center, false);
                final Chunk topRight = world.getChunk(centerX + 1, centerZ + 1);
                final Chunk top = world.getChunk(centerX, centerZ + 1);
                final Chunk right = world.getChunk(centerX + 1, centerZ);
                final Chunk left = world.getChunk(centerX - 1, centerZ);
                final Chunk topLeft = world.getChunk(centerX - 1, centerZ + 1);
                final Chunk bottom = world.getChunk(centerX, centerZ - 1);
                final Chunk bottomRight = world.getChunk(centerX + 1, centerZ - 1);
                final Chunk bottomLeft = world.getChunk(centerX - 1, centerZ - 1);

                // try populate us
                if (!hasPopulated(center) && topRight != null && top != null && right != null) {
                    runPopulators(world, center);
                }

                // try populate left
                if (left != null && !hasPopulated(left) && topLeft != null && top != null) {
                    runPopulators(world, left);
                }

                // try populate bottom
                if (bottom != null && !hasPopulated(bottom) && bottomRight != null && right != null) {
                    runPopulators(world, bottom);
                }

                // try populate bottom left
                if (bottomLeft != null && !hasPopulated(bottomLeft) && bottom != null && left != null) {
                    runPopulators(world, bottomLeft);
                }
            }
        }).delay(TaskSchedule.tick(1)).schedule();
    }
}
