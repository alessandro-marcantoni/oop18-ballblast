package ballblast.model.physics.handlers;

import ballblast.model.levels.Boundaries;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.utilities.Bounce;

/**
 * Represents the handler for the behavior of a {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Ball object.
        final int decLife = 1;
        switch (coll.getCollisionTag()) {
        case PLAYER:
            break;
        case WALL:
            final Coordinate boundaryPos = coll.getAttachedGameObject().getPosition();
            if (Boundaries.isFloor(boundaryPos)) {
                obj.setPosition(new Coordinate(obj.getPosition().getX(),
                        Boundaries.BOTTOM.getPosition().getY() - obj.getHeight()));
                Bounce.floorBounce(obj);
            } else if (Boundaries.isRoof(boundaryPos)) {
                obj.setPosition(new Coordinate(obj.getPosition().getX(),
                        Boundaries.TOP.getPosition().getY() + Boundaries.TOP.getHeight()));
                Bounce.floorBounce(obj);
            } else {
                Bounce.wallBounce(obj);
            }
            break;
        case BULLET:
            // Decrement the Ball life by 'decLife' and destroy if life = 0.
            this.decrementLife(obj, decLife);
            if (((Ball) obj).getCurrentLife() <= 0) {
                obj.destroy();
            }
            break;
        default:
            break;
        }
    }

    private void decrementLife(final GameObject ball, final int decrementBy) {
        ((Ball) ball).setCurrentLife(((Ball) ball).getCurrentLife() - decrementBy);
    }
}
