package ballblast.model.commons;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;

/**
 * 
 */
public final class Utils {
    private Utils() {
    }

    private static final double SPAWN_OFFSET = 5.0;
    private static final double SPAWN_Y = Boundaries.TOP.getHeight() + SPAWN_OFFSET;
    private static final double MIN_SPAWN_X = Boundaries.LEFT.getWidth() + Constants.LARGE_BALL_SIZE + SPAWN_OFFSET;
    private static final double MAX_SPAWN_X = Constants.WORLD_WIDTH - Boundaries.RIGHT.getWidth()
            - Constants.LARGE_BALL_SIZE - SPAWN_OFFSET;

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

}
