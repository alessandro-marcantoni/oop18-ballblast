package ballblast.model.data;

import java.io.Serializable;

import ballblast.model.data.GameDataManager.GameData;

/**
 * The class that represents the information of a User.
 */
public class UserData implements Serializable {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = -3297421101653830531L;
    private final String name;
    private int globalScore;
    private int destroyedBalls;
    private int matchesPlayed;
    private double gameTime;
    private int spawnedBullets;

    /**
     * The costructor to inizialize the user data.
     * @param userName
     *          the name of the user.
     */
    public UserData(final String userName) {
        this.name = userName;
        this.globalScore = 0;
        this.destroyedBalls = 0;
        this.matchesPlayed = 0;
        this.spawnedBullets = 0;
        this.gameTime = 0;
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
        this.globalScore += gameData.getScore();
        this.destroyedBalls += gameData.getDestroyedBalls();
        this.gameTime += gameData.getTime();
        this.spawnedBullets += gameData.getSpawnedBullets();
    }
}
