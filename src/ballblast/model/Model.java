package ballblast.model;

import java.util.Optional;

import ballblast.model.levels.Level;

/**
 * It represents a macro class, containing levels and game objects.
 * It can be seen as an entry point because delegates its tasks to the Level class.
 */
public interface Model {
    /**
     * Default width for the levels.
     */
    double WORLD_WIDTH = 200;
    /**
     * Default height for the levels.
     */
    double WORLD_HEIGHT = 100;
    /**
     * Default {@link Wall} width for external boundaries.
     */
    double WALL_OFFSET = 5.5;
    /**
     * Returns the current level, it can be null, this is the reason of Optional.
     * @return 
     *      the current level.
     */
    Optional<Level> getCurrentLevel();
    /**
     * Starts new survival session. 
     */
    void startSurvival();
}
