package ballblast.model.physics.handlers;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler {

    /**
     * Empty costructor because {@link CollisionHandler} is a functional interface.
     * Only the method implementation needed.
     */
    public BallCollisionHandler() {

    }

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
                // TODO metodo bounce()
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
