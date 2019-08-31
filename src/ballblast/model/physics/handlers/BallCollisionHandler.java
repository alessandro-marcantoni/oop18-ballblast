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
                // TODO metodo endGameSession()
                break;
            case WALL:
                final Coordinate boundaryPos = coll.getAttachedGameObject().get().getPosition();
                if (this.checkFloor(boundaryPos)) {
                    Bounce.floorBounce(obj);
                } else {
                    Bounce.wallBounce(obj);
                }
                break;
            case BULLET:
                // Decrement the Ball life by 'decLife' and destroy if life = 0.
                ((Ball) obj).setCurrentLife(((Ball) obj).getCurrentLife() - decLife);
                if (((Ball) obj).getCurrentLife() <= 0) {
                    obj.destroy();
                }
                // TODO handle score
                break;
            default:
                break;
        }
    }

    private boolean checkFloor(final Coordinate position) {
        return position.equals(Boundaries.BOTTOM.getPosition()) || position.equals(Boundaries.TOP.getPosition());
    }
}
