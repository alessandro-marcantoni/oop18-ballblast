package ballblast.controller;

import java.util.List;

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
     * Gives the player's score based on the game time.
     * @return
     *       The score.
     */
    int getTimeScore();
}
