package ballblast.model.powerups;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.physics.CollisionManager;

/**
 * Represents the factory Interface for creating {@link Power} in a Factory
 * Method.
 */
public interface PowerFactory {

    /**
     * Creates a new random {@link Power}.
     * 
     * @param velocity         The initial velocity.
     * @param position         The initial position.
     * @param collisionManager The collision manager.
     * @return The new {@link Power}.
     */
    Power createRandomPower(Vector2D velocity, Coordinate position, CollisionManager collisionManager);

}
