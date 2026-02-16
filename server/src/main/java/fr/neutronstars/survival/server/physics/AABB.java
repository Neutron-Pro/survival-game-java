package fr.neutronstars.survival.server.physics;

import fr.neutronstars.survival.core.world.Location;

public class AABB {
    public static boolean intersects(Location aLocation, Box aBox, Location bLocation, Box bBox) {
        return (aLocation.x() - aBox.origin()) < (bLocation.x() - bBox.origin()) + bBox.width()
            && (aLocation.x() - aBox.origin()) + aBox.width() > (bLocation.x() - bBox.origin())
            && (aLocation.y() - aBox.origin()) < (bLocation.y() - bBox.origin()) + bBox.height()
            && (aLocation.y() - aBox.origin()) + aBox.height() > (bLocation.y() - bBox.origin());
    }
}
