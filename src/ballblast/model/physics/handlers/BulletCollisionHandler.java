package ballblast.model.physics.handlers;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Bullet} after a collision.
 */
public class BulletCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Bullet object.
        final int decLife = 1;
        switch (coll.getCollisionTag()) {
            case BALL:
                // Decrement the Ball life by 'decLife' and destroy if life = 0.
                final Ball ball = ((Ball) coll.getAttachedGameObject().get());
                ball.setLife(ball.getLife() - decLife);
                if (ball.getLife() == 0) {
                    ball.destroy();
                }
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
