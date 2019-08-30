package ballblast.model.physics.handlers;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of a {@link Bullet} after a collision.
 */
public class BulletCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Bullet object.
        switch (coll.getCollisionTag()) {
            case BALL:
                // Destroy the Bullet after the collision with the Ball.
                obj.destroy();
                // TODO handle score
                break;
            case WALL:
                obj.destroy();
                break;
            default:
                break;
        }
    }

}
