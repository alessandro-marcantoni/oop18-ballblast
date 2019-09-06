package ballblast.model.commons;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ballblast.model.Model;
import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;
import ballblast.model.physics.CollisionManager;
import ballblast.model.powerups.Power;
import ballblast.model.powerups.PowerFactory;
import ballblast.model.powerups.PowerTypes;

/**
 * Contains some useful methods used by more than one class.
 */
public final class Utils {
    private static final Random RANDOM = new Random();
    private static final double SPAWN_OFFSET = 20;
    private static final double SPAWN_Y = Boundaries.TOP.getHeight() + 5;
    private static final double MIN_SPAWN_X = Boundaries.LEFT.getWidth() + SPAWN_OFFSET;
    private static final double MAX_SPAWN_X = Model.WORLD_WIDTH - Boundaries.RIGHT.getWidth() - SPAWN_OFFSET;
    private static final List<PowerTypes> POWERS = ImmutableList.copyOf(PowerTypes.values());
    private static final Map<PowerTypes, PowerConsumer> POWER_MAP = ImmutableMap.of(
            PowerTypes.DOUBLEFIRE, PowerFactory::createDoubleFirePower,
            PowerTypes.SPEED, PowerFactory::createSpeedPower,
            PowerTypes.SHIELD, PowerFactory::createShieldPower
    );

    /**
     * Generates a random spawn position based on World dimensions.
     * @return a random spawn position.
     */
    public static Coordinate getRandomSpawnPosition() {
        return new Coordinate((Math.random() * ((MAX_SPAWN_X - MIN_SPAWN_X) + 1)) + MIN_SPAWN_X, SPAWN_Y);
    }
    /**
     * Enables all the {@link Component}s of a specific {@link GameObject}.
     * @param gameObject the {@link GameObject} which has to enable all his {@link Component}s.
     */
    public static void activeComponents(final GameObject gameObject) {
        gameObject.getComponents().forEach(Component::enable);
    }

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
        return POWER_MAP.get(getRandomPowerType()).apply(velocity, position, collisionManager);
    }

    private Utils() { }

    private interface PowerConsumer {
        Power apply(Vector2D v, Coordinate p, CollisionManager c);
    }

    private static PowerTypes getRandomPowerType() {
        return POWERS.get(RANDOM.nextInt(POWERS.size()));
    }

}
