package ballblast.model.data;

import java.io.Serializable;

/**
 * The class that represents the information of a User.
 */
public class UserData implements Serializable {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = -3297421101653830531L;
    private String name;
    private int globalScore;
    private int killedBalls;
    private int matchesPlayed;

    /**
     * The costructor to inizialize the user data.
     * @param userName
     *          the name of the user.
     */
    public UserData(final String userName) {
        this.name = userName;
        this.globalScore = 0;
        this.killedBalls = 0;
        this.matchesPlayed = 0;
    }

    /**
     * Getter for the user name.
     * @return
     *          the user name.
     */
    public String getUserName() {
       return this.name;
    }

    /**
     * Update the user fields with the game session data.
     * @param gameData
     *          the data of the game session.
     */
    public void addGameData(final GameData gameData) {
        this.matchesPlayed++;
        this.globalScore += gameData.getGameScore();
        this.killedBalls += gameData.getKilledBalls();
    }
}
