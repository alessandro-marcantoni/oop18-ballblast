package ballblast.model.gameobjects;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.components.ShooterComponent;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;

/**
 * Represents a factory used to instantiate new {@link GameObject}.
 */
public final class GameObjectFactory {
    private GameObjectFactory() {
    }

    /**
     * Creates {@link Player} game object.
     * @param collisionManager
     *     the {@link CollisionManager}.
     * @param gameObjectManager
     *     the {@link GameObjectManager}.
     * @return the {@link GameObject} created.
     */
    public static GameObject createPlayer(final GameObjectManager gameObjectManager,
            final CollisionManager collisionManager) {
        return new Player.Builder()
                .addComponent(new ShooterComponent(gameObjectManager, collisionManager))
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.PLAYER))
                .addComponent(new MovementComponent(Player.VELOCITY))
                .build();
    }

    /**
     * Creates {@link Wall} game object.
     * @param collisionManager
     *     the {@link CollisionManager}.
     * @param height   the {@link Wall}'s height.
     * @param width    the {@link Wall}'s width.
     * @param position the {@link Wall}'s position.
     * @return the {@link GameObject} created.
     */
    public static GameObject createWall(final double height, final double width, final Coordinate position,
            final CollisionManager collisionManager) {
        return new Wall.Builder()
                .setHeight(height)
                .setWidth(width)
                .setPosition(position)
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.WALL))
                .build();
    }

    /**
     * Creates {@link Bullet} game object.
     * @param collisionManager
     *     the {@link CollisionManager}.
     * @param position the {@link Bullet}'s position.
     * @return the {@link GameObject} created.
     */
    public static GameObject createBullet(final Coordinate position, final CollisionManager collisionManager) {
        return new Bullet.Builder()
                .setPosition(position)
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BULLET))
                .addComponent(new MovementComponent(Bullet.VELOCITY))
                .build();
    }

    /**
     * Creates {@link Ball} game object.
     * @param collisionManager
     *     the {@link CollisionManager}.
     * @param ballType the {@link BallTypes}.
     * @param life     the {@link Ball}'s life.
     * @param position the {@link Ball}'s position.
     * @return the {@link GameObject created}.
     */
    public static GameObject createBall(final BallTypes ballType, final int life, final Coordinate position,
            final CollisionManager collisionManager) {
        return new Ball.Builder()
                .setBallType(ballType)
                .setLife(life)
                .setPosition(position)
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BALL))
                .addComponent(new MovementComponent(Ball.VELOCITY))
                .build();
    }
}
