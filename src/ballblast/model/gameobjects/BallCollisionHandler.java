package ballblast.model.gameobjects;

import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler<Ball> {

    /**
     * Empty costructor because {@link CollisionHandler} is a functional interface.
     */
    public BallCollisionHandler() {

    }

    @Override
    public final void execute(final Collidable coll, final Ball obj) {
        switch (coll.getCollisionTag()) {
            case PLAYER:
                coll.getAttachedGameObject().get().destroy();
            case WALL:
                // metodo bounce()
            case BULLET:
                obj.setLife(obj.getLife() - 1);
            default:
                break;
        }
    }
}
