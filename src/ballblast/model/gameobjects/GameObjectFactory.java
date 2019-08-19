package ballblast.model.gameobjects;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.GravityComponent;
import ballblast.model.components.InputComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.components.ShooterComponent;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.BallCollisionHandler;
import ballblast.model.physics.handlers.BulletCollisionHandler;
import ballblast.model.physics.handlers.PlayerCollisionHandler;
import ballblast.model.physics.handlers.WallCollisionHandler;

/**
 * Represents a factory used to instantiate new {@link GameObject}s.
 */
public final class GameObjectFactory {
    private GameObjectFactory() {
    }

    /**
     * Creates {@link Player} game object.
     * 
     * @param gameObjectManager the {@link GameObjectManager}.
     * @param inputManager      the {@link InputManager}.
     * @param tag               the {@link PlayerTags}.
     * @param collisionManager  the {@link CollisionManager}.
     * @param velocity          the {@link Player}'s velocity.
     * @param position          the {@link Player},s position.
     * @return the {@link GameObject} created.
     */
    public static GameObject createPlayer(final GameObjectManager gameObjectManager, final InputManager inputManager,
            final PlayerTags tag, final CollisionManager collisionManager, final Vector2D velocity, 
            final Coordinate position) {
        return new Player.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PlayerCollisionHandler())
                .addComponent(new InputComponent(inputManager, tag))
                .addComponent(new ShooterComponent(gameObjectManager, collisionManager))
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.PLAYER))
                .addComponent(new MovementComponent()).build();
    }

    /**
     * Creates {@link Wall} game object.
     * 
     * @param collisionManager the {@link CollisionManager}.
     * @param height           the {@link Wall}'s height.
     * @param width            the {@link Wall}'s width.
     * @param velocity         the {@link Wall}'s velocity.
     * @param position         the {@link Wall}'s position.
     * @return the {@link GameObject} created.
     */
    public static GameObject createWall(final double height, final double width, final Coordinate position,
            final Vector2D velocity, final CollisionManager collisionManager) {
        return new Wall.Builder()
                .setHeight(height)
                .setWidth(width)
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new WallCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.WALL)).build();
    }

    /**
     * Creates {@link Bullet} game object.
     * 
     * @param collisionManager the {@link CollisionManager}.
     * @param position         the {@link Bullet}'s position.
     * @param velocity         the {@link Bullet}'s velocity.
     * @return the {@link GameObject} created.
     */
    public static GameObject createBullet(final Coordinate position, final Vector2D velocity,
            final CollisionManager collisionManager) {
        return new Bullet.Builder()
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new BulletCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BULLET))
                .addComponent(new MovementComponent()).build();
    }

    /**
     * Creates {@link Ball} game object.
     * 
     * @param collisionManager the {@link CollisionManager}.
     * @param ballType         the {@link BallTypes}.
     * @param life             the {@link Ball}'s life.
     * @param position         the {@link Ball}'s position.
     * @param velocity         the {@link Ball}'s velocity.
     * @return the {@link GameObject created}.
     */
    public static GameObject createBall(final BallTypes ballType, final int life, final Coordinate position,
            final Vector2D velocity, final CollisionManager collisionManager) {
        return new Ball.Builder()
                .setBallType(ballType)
                .setLife(life)
                .setPosition(position)
                .setVelocity(velocity)
                .setCollisionHandler(new BallCollisionHandler())
                .addComponent(new GravityComponent())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.BALL))
                .addComponent(new MovementComponent()).build();
    }
}
