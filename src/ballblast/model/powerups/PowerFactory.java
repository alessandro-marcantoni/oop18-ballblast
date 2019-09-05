package ballblast.model.powerups;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.GravityComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.PowerCollisionHandler;

/**
 * Factory to create {@link Power}.
 */
public final class PowerFactory {
    private PowerFactory() {
    }

    /**
     * Creates a new {@link ShieldPower}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    public static Power createShieldPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new ShieldPower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }
    /**
     * Creates a new {@link DoubleFirePower}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    public static Power createDoubleFirePower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new DoubleFirePower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }
    /**
     * Creates a new {@link SpeedPower}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    public static Power createSpeedPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new SpeedPower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }

}
