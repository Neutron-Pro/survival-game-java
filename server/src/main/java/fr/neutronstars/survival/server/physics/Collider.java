package fr.neutronstars.survival.server.physics;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Tile;

public class Collider {
    public static Tile collide(fr.neutronstars.survival.core.world.Box box, Location location) {
        final int minX = (int) Math.floor(location.x() - box.originX());
        final int maxX = (int) Math.floor(location.x() - box.originX() + box.width());

        final int minY = (int) Math.floor(location.y() - box.originY());
        final int maxY = (int) Math.floor(location.y() - box.originY() + box.width());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                final Tile tile = location.world().tileOf(x, y, location.z());
                if (tile != null && tile.block() != null && tile.block().isSolid()) {
                    if (AABB.intersects(location, box, tile.location(), tile.block().box())) {
                        return tile;
                    }
                }
                if (location.z() > 0) {
                    final Tile groundTile = location.world().tileOf(x, y, location.z() - 1);
                    if (
                        groundTile != null
                            && groundTile.block() != null
                            && !groundTile.block().canWalk()
                            && AABB.intersects(location, box, groundTile.location(), groundTile.block().box())
                    ) {
                        return groundTile;
                    }
                }
            }
        }
        return null;
    }
}
