package ballblast.model.levels;

import java.util.List;

import ballblast.model.gameobjects.GameObject;
/**
 * Represents a level and it is responsible for the game logic. 
 *
 */
public interface Level {
    /**
     * Updates the game status.
     * @param elapsed
     *      the time elapsed since last update.
     */
    void update(double elapsed);
    /**
     * Gets all Level's {@link GameObejct}s.
     * @return
     *      the {@link List} containing all Level's {@link GameObejct}s.
     */
    List<GameObject> getGameObjects();
    /**
     * Gets the Level's score.
     * @return
     *      the Level's score 
     */
    int getGameScore();
}
