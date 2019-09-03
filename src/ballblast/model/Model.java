package ballblast.model;

import java.util.List;

import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputTypes;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.levels.GameStatus;

/**
 * It represents a macro class, containing the current {@link Level} and his {@link GameObject}s. 
 * It can be seen as an entry point because delegates its tasks to the Level class.
 */
public interface Model {
    /**
     * Starts new survival session.
     */
    void startSurvival();

    /**
     * Gets the {@link List} containing all {@link GameObject}s.
     * 
     * @return the gameObject list.
     */
    List<GameObject> getGameObjects();

    /**
     * Resolves the received inputs inside the {@link InputManager}.
     * 
     * @param tag    the {@link PlayerTags}.
     * @param inputs the {@link List} of inputs to be resolved.
     */
    void resolveInputs(PlayerTags tag, List<InputTypes> inputs);

    /**
     * Updates the game.
     * 
     * @param elapsed the time elapsed since last update.
     */
    void update(double elapsed);

    /**
     * Gets the {@link GameStatus}.
     * 
     * @return the status of the game.
     */
    GameStatus getGameStatus();

    /**
     * Gets the game data (score, time, destroyed balls ecc..).
     * 
     * @return The {@link GameData}.
     */
    GameData getGameData();
}
