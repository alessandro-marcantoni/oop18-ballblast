package ballblast.model.data;

import ballblast.model.levels.Level;

/**
 * Represents the data at the end of a game session.
 */
public class GameData {

    private int gameScore;
    private int killedBalls;
    private int gameTime;

    /**
     * Creates a new istance of the game data.
     * @param lvl
     *          the level that handled the game session.
     */
    public GameData(final Level lvl) {
        this.gameScore = lvl.getGameScore();
        //this.killedBalls = lvl.getKilledBalls();
        //this.gameTime = lvl.getGameTime();
    }

    /**
     * Getter for the game session score.
     * @return
     *          the score achieved in the game session.
     */
    public int getGameScore() {
        return this.gameScore;
    }

    /**
     * Getter for the killed balls.
     * @return
     *          the number of killed balls during the game session.
     */
    public int getKilledBalls() {
        return this.killedBalls;
    }

    /**
     * Getter for the game session time.
     * @return
     *          the time of the game session.
     */
    public int getGameTime() {
        return this.gameTime;
    }

}
