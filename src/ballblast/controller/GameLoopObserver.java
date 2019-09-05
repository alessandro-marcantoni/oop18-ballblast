package ballblast.controller;

/**
 * The interface representing the Observer of a {@link GameLoop}.
 */
public interface GameLoopObserver {

    /**
     * Updates the {@link Leaderboard}.
     */
    void updateLeaderboard();

}
