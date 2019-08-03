package ballblast.model.levels;

import ballblast.model.gameobjects.GameObjectManager;
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
     * Gets the {@link GameObjectManager}.
     * @return
     *      the {@link GameObjectManager}.
     */
    GameObjectManager getGameObjectManager();
    /**
     * Gets the Level's score.
     * @return
     *      the Level's score 
     */
    int getGameScore();
}
