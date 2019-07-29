package ballblast.model;

import java.util.Optional;

import ballblast.model.levels.Level;

/**
 * The class represents a macro class, containing levels and game objects.
 * It can be seen as an entry point because delegates its tasks to the Level class.
 */
public interface Model {
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
