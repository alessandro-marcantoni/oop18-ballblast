package ballblast.model.physics.handlers;

import ballblast.model.constants.Boundaries;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.utilities.Bounce;

/**
 * Represents the handler for the behavior of the {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Ball object.
        final int decLife = 1;
        switch (coll.getCollisionTag()) {
            case PLAYER:
                // Destroy the Player and finish the game session.
                coll.getAttachedGameObject().get().destroy();
                // TODO metodo endGameSession()
            case WALL:
                if (coll.getAttachedGameObject().get().getPosition().equals(Boundaries.BOTTOM.getPosition())) {
                    Bounce.floorBounce(obj);
                } else {
                    Bounce.wallBounce(obj);
                }
            case BULLET:
                // Decrement the Ball life by 'decLife' and destroy if life = 0.
                ((Ball) obj).setLife(((Ball) obj).getLife() - decLife);
                if (((Ball) obj).getLife() == 0) {
                    obj.destroy();
                }
                // Destroy the Bullet that collide with the Ball.
                coll.getAttachedGameObject().get().destroy();
                // TODO handle score
            default:
                break;
        }
    }
}
