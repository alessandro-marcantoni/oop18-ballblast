package ballblast.controller;

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

}
