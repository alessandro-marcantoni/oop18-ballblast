package ballblast.model.powerups;

import java.util.Random;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.PowerCollisionHandler;

/**
 * Implementation of the {@link PowerFactory} interface.
 */
public class PowerFactoryImpl implements PowerFactory {

    @Override
    public final Power createRandomPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        final int powerTypePick = new Random().nextInt(PowerTypes.values().length);
        switch (powerTypePick) {
        case 1:
            return this.createShieldPower(velocity, position, collisionManager);
        case 2:
            return this.createDoubleFirePower(velocity, position, collisionManager);
        default:
            return this.createFreezePower(velocity, position, collisionManager);
        }
    }

    private Power createShieldPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new ShieldPower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .build();
    }

    private Power createDoubleFirePower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new DoubleFirePower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .build();
    }

    private Power createFreezePower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new FreezePower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .build();
    }

}
