package ballblast.controller;

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
}
