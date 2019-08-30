package ballblast.controller;

import java.util.List;

import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;

/**
 * Represents the Controller in the MVC pattern.
 */
public interface Controller {

    /**
     * Starts a new survival mode game.
     */
    void startSurvivalMode();

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Resumes the paused game.
     */
    void resume();

    /**
     * Ends the game.
     */
    void gameOver();

    /**
     * Sends an input to be resolved the next update by the right {@link Player} to the {@link Controller}.
     * @param tag
     *     the tag which identifies the {@link Player}.
     * @param input
     *     the input to be resolved.
     */
    void sendInput(PlayerTags tag, InputTypes input);

    /**
     * Returns the list of active {@link GameObjects} to be rendered.
     * @return
     *       A list of {@link GameObject}.
     */
    List<GameObject> getGameObjects();

    /**
     * Gets the game data (score, time, destroyed balls ecc..).
     * @return
     *       The {@link GameData}.
     */
    GameData getGameData();

}
