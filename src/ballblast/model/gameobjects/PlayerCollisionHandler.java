package ballblast.model.gameobjects;

import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Player} after a collision.
 */
public class PlayerCollisionHandler implements CollisionHandler<Player> {

    /**
     * Empty costructor because {@link CollisionHandler} is a functional interface.
     */
    public PlayerCollisionHandler() {

    }

    @Override
    public final void execute(final Collidable coll, final Player obj) {
        switch (coll.getCollisionTag()) {
            case BALL:
                obj.destroy();
            case WALL:

            case POWERUP:
                obj.toString();
            default:
                break;
        }
    }
}