package ballblast.controller.leaderboard;

import java.io.Serializable;

/**
 * This class is a record for a {@link Leaderboard} keeping information about username, game time and score.
 */
public class Record implements Serializable, Comparable<Record> {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = -3402166234477683311L;
    private final String userName;
    private final int gameTime;
    private final int score;

    /**
     * Creates a new record.
     * @param name
     *          the name of the User.
     * @param time
     *          the time of the last survival game session.
     * @param score
     *          the score reached in the last game session.
     */
    public Record(final String name, final int time, final int score) {
        this.userName = name;
        this.gameTime = time;
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
     * Getter of the game time.
     * @return
     *          the game time reached in the last game session.
     */
    public int getGameTime() {
        return this.gameTime;
    }

    /**
     * Getter of the User name.
     * @return
     *          the name of the User.
     */
    public String getUserName() {
        return this.userName;
    }

    @Override
    public final int compareTo(final Record rec) {
        final int scoreComparison = Integer.compare(this.score, rec.getScore());
        return scoreComparison == 0 ? Integer.compare(this.gameTime, rec.getGameTime()) : scoreComparison;
    }

    @Override
    public final int hashCode() {
        final int prime = 17;
        int res = 1;
        res = prime * res + this.score;
        res = prime * res + this.gameTime;
        return res;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        if (score != other.score) {
            return false;
        }
        return gameTime == other.gameTime;
    }

}
