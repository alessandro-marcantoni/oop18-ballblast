package ballblast.model.levels;
/**
 * Enum to define the status of the Model.
 * 
 */
public enum GameStatus {
    /**
     * Player win if he completes all levels.
     */
    WON,
    /**
     * The game is over when the player has been destroyed.
     */
    OVER,
    /**
     * The game is running.
     */
    RUNNING,
    /**
     * Game paused.
     */
    PAUSE;
}
