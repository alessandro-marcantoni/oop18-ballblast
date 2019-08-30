package ballblast.model.data;

/**
 * This class is a record for a {@link Leaderboard} keeping information about username, game time and score.
 */
public class RecordData {

    private String name;
    private int score;

    /**
     * Empty constructor to serialize the object in a xml file.
     */
//    public RecordData() {
//    }

    /**
     * Getter of the User score.
     * @return
     *          the score in the last played game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter of the User name.
     * @return
     *          the name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the {@RecordData} user name.
     * @param name
     *          the user name who submit the record.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter for the {@RecordData} user score.
     * @param score
     *          the score of the record.
     */
    public void setScore(final int score) {
        this.score = score;
    }

}
