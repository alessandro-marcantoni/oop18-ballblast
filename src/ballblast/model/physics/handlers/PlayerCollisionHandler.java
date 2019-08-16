package ballblast.model.physics.handlers;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Player} after a collision.
 */
public class PlayerCollisionHandler implements CollisionHandler {

    /**
     * Empty costructor because {@link CollisionHandler} is a functional interface.
     * Only the method implementation needed.
     */
    public PlayerCollisionHandler() {

    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Player object.
        switch (coll.getCollisionTag()) {
            case BALL:
                // Destroy the Player and finish the game session.
                obj.destroy();
                // TODO metodo endGameSession()
            case WALL:

            case POWERUP:
                obj.toString();
            default:
                break;
        }
    }
}
