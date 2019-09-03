package ballblast.controller;

import ballblast.model.inputs.InputTypes;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * Represents the loop that takes commands from the user, updates the game world
 * and renders it on the view.
 */
public interface GameLoop {

    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Pauses the game loop.
     */
    void pause();

    /**
     * Resumes the game loop.
     */
    void resumeLoop();

    /**
     * Stops the game loop.
     */
    void stopLoop();

    /**
     * Receives inputs.
     * 
     * @param tag   the tag which identifies the {@link Player}.
     * @param input the input to be resolved.
     */
    void receiveInput(PlayerTags tag, InputTypes input);

    /**
     * Adds a new {@link Observer}.
     * 
     * @param observer The {@link Observer} to be added.
     */
    void addObserver(GameLoopObserver observer);

    /**
     * Notifies the {@link Observer} when the state changes.
     */
    void sendState();
}
