package ballblast.model.levels;

import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.inputs.InputManager;
import ballblast.model.physics.CollisionManager;
/**
 * Represents a level and it is responsible for the game logic. 
 *
 */
public interface Level {
    /**
     * Enables all {@link GameObject}s.
     */
    void start();
    /**
     * Updates the game status.
     * @param elapsed
     *      the time elapsed since last update.
     */
    void update(double elapsed);
    /**
     * Gets the {@link GameStatus}.
     * @return
     *     the current {@link GameStatus}.
     */
    GameStatus getGameStatus();
    /**
     * Sets the {@link GameStatus}.
     * @param gameStatus
     *     the {@link GameStatus} to be set.
     */
    void setGameStatus(GameStatus gameStatus);
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
     * Gets the {@link InputManager}.
     * @return
     *     the {@link InputManager}.
     */
    InputManager getInputManager();
    /**
     * Gets the Level's score.
     * @return
     *      the Level's score 
     */
    int getGameScore();
    /**
     * Gets the playing time in seconds.
     * @return 
     *     the double represent the sum of all elapsed.
     */
    double getGameTime();
}
