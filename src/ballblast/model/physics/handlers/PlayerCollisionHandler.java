package ballblast.model.physics.handlers;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.gameobjects.Player;

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
            if (!((Player) obj).isImmune()) {
                obj.destroy();
            }
            break;
        case WALL:
            final GameObject boundary = coll.getAttachedGameObject();
            this.checkBoundLimit(boundary, obj);
            break;
        default:
            break;
        }
    }

    private void checkBoundLimit(final GameObject bound, final GameObject obj) {
        if (Boundaries.isRight(bound.getPosition())) {
            obj.setPosition(new Coordinate(bound.getPosition().getX() - obj.getWidth(), obj.getPosition().getY()));
        } else if (Boundaries.isLeft(bound.getPosition())) {
            obj.setPosition(new Coordinate(bound.getPosition().getX() + bound.getWidth(), obj.getPosition().getY()));
        }
    }
}
