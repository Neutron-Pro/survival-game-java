package fr.neutronstars.survival.server.physics;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;
import fr.neutronstars.survival.server.world.ServerContext;

public class Collider {
    public static boolean collide(Box box, Location location) {
        final int minX = (int) Math.floor(location.x() - box.origin());
        final int maxX = (int) Math.floor(location.x() - box.origin() + box.width());

        final int minY = (int) Math.floor(location.y() - box.origin());
        final int maxY = (int) Math.floor(location.y() - box.origin() + box.width());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                final Tile tile = location.world().tileOf(x, y, location.z());
                if (tile != null && tile.block() != null) {
                    if (tile.block().context() instanceof ServerContext context && context.isSolid()) {
                        if (AABB.intersects(location, box, tile.location(), context.box())) {
                            System.out.println("Collide: " + tile.block().getClass());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
