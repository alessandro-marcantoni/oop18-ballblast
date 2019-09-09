package ballblast.model;

import java.util.List;

import ballblast.commons.events.EventTypes;
import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputTypes;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.levels.GameStatus;

/**
 * It represents a macro class, containing the current {@link Level} and his
 * {@link GameObject}s. It can be seen as an entry point because delegates its
 * tasks to the {@link Level} class.
 */
public interface Model {
    /**
     * Default World's width.
     */
    double WORLD_WIDTH = 200;
    /**
     * Default World's height.
     */
    double WORLD_HEIGHT = 100;

    /**
     * Starts new survival level.
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
     * @return the {@link GameData}.
     */
    GameData getGameData();

    /**
     * Gets the game's events.
     * 
     * @return the game's events.
     */
    List<EventTypes> getGameEvents();
}
