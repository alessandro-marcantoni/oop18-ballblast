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
        switch (coll.getCollisionTag()) {
            case PLAYER:
                coll.getAttachedGameObject().get().destroy();
            case WALL:
                // metodo bounce()
            case BULLET:
                ((Ball) obj).setLife(((Ball) obj).getLife() - 1);
                if (((Ball) obj).getLife() == 0) {
                    obj.destroy();
                }
                coll.getAttachedGameObject().get().destroy();
            default:
                break;
        }
    }
}
