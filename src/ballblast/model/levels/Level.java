package ballblast.model.levels;

import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;
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
     * Gets the {@link CollisionManager}.
     * @return
     *     the {@link CollisionManager}.
     */
    CollisionManager getCollisionManager();
    /**
     * Gets the Level's score.
     * @return
     *      the Level's score 
     */
    int getGameScore();
}
