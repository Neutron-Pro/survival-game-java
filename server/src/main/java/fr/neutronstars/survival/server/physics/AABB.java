package fr.neutronstars.survival.server.physics;

import fr.neutronstars.survival.core.world.Location;
import fr.neutronstars.survival.core.world.Box;

public class AABB {
    public static boolean intersects(Location aLocation, Box aBox, Location bLocation, Box bBox) {
        return (aLocation.x() - aBox.originX()) < (bLocation.x() - bBox.originX()) + bBox.width()
            && (aLocation.x() - aBox.originX()) + aBox.width() > (bLocation.x() - bBox.originX())
            && (aLocation.y() - aBox.originY()) < (bLocation.y() - bBox.originX()) + bBox.height()
            && (aLocation.y() - aBox.originY()) + aBox.height() > (bLocation.y() - bBox.originY());
    }
}
