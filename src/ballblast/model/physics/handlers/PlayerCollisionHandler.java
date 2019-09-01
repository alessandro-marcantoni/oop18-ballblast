package ballblast.model.physics.handlers;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of a {@link Player} after a
 * collision.
 */
public class PlayerCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Player object.
        switch (coll.getCollisionTag()) {
        case BALL:
            // Destroy the Player and finish the game session.
            obj.destroy();
            break;
        case WALL:
            final GameObject boundary = coll.getAttachedGameObject();
            if (Boundaries.isRight(boundary.getPosition())) {
                obj.setPosition(
                        new Coordinate(boundary.getPosition().getX() - obj.getWidth(), obj.getPosition().getY()));
            }
            if (Boundaries.isLeft(boundary.getPosition())) {
                obj.setPosition(
                        new Coordinate(boundary.getPosition().getX() + boundary.getWidth(), obj.getPosition().getY()));
            }
            break;
        case POWERUP:
            obj.toString();
            break;
        default:
            break;
        }
    }
}
