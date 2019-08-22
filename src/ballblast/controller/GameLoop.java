package ballblast.controller;

/**
 * Represents the loop that takes commands from the user, updates the game world and renders it on the view.
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
}
