package ballblast.model.powerups;

import java.util.Random;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.GravityComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.PowerCollisionHandler;

/**
 * Implementation of the {@link PowerFactory} interface.
 */
public final class PowerFactory {
    private PowerFactory() { }

    /**
     * Creates a new random {@link Power}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    public static Power createRandomPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        final int powerTypePick = new Random().nextInt(PowerTypes.values().length);
        switch (powerTypePick) {
        case 1:
            return createShieldPower(velocity, position, collisionManager);
        case 2:
            return createDoubleFirePower(velocity, position, collisionManager);
        default:
            return createSpeedPower(velocity, position, collisionManager);
        }
    }

    private static Power createShieldPower(final Vector2D velocity, final Coordinate position,
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

    private static Power createDoubleFirePower(final Vector2D velocity, final Coordinate position,
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

    private static Power createSpeedPower(final Vector2D velocity, final Coordinate position,
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
