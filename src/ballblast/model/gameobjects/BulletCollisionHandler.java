package ballblast.model.gameobjects;

import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Bullet} after a collision.
 */
public class BulletCollisionHandler implements CollisionHandler<Bullet> {

    /**
     * Empty costructor because {@link CollisionHandler} is a functional interface.
     * Only the method implementation needed.
     */
    public BulletCollisionHandler() {

    }

    @Override
    public final void execute(final Collidable coll, final Bullet obj) {
        switch (coll.getCollisionTag()) {
            case BALL:
                Ball ball = ((Ball) coll.getAttachedGameObject().get());
                ball.setLife(ball.getLife() - 1);
                obj.destroy();
            case WALL:
                obj.destroy();
            default:
                break;
        }
    }
}
