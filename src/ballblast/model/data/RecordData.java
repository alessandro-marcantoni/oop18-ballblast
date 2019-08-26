package ballblast.model.data;

import java.io.Serializable;

/**
 * This class is a record for a {@link Leaderboard} keeping information about username, game time and score.
 */
public class RecordData implements Serializable {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = -3402166234477683311L;
    private final String userName;
    private final int score;

    /**
     * Creates a new record.
     * @param name
     *          the name of the User.
     * @param score
     *          the score reached in the last game session.
     */
    public RecordData(final String name, final int score) {
        this.userName = name;
        this.score = score;
    }

    /**
     * Getter of the User score.
     * @return
     *          the score in the last played game.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter of the User name.
     * @return
     *          the name of the User.
     */
    public String getUserName() {
        return this.userName;
    }

}
