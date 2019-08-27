package ballblast.model.data;

/**
 * Keeps all game data up to date.
 */
public class GameDataManager {
    private final GameData gameData;

    /**
     * Class constructor.
     */
    public GameDataManager() {
        this.gameData = new GameData();
    }

    /**
     * Increments the destroyed balls counter.
     */
    public void incrementDestroyedBalls() {
        this.gameData.setDestroyedBalls(this.gameData.getDestroyedBalls() + 1);
    }

    /**
     * Increments the spawned bullets counter.
     */
    public void incrementSpawnedBullets() {
        this.gameData.setSpawnedBullets(this.gameData.getSpawnedBullets() + 1);
    }

    /**
     * Keeps the game time.
     * 
     * @param elapsed the time elapsed since last update.
     */
    public void updateGameTime(final double elapsed) {
        this.gameData.setGameTime(this.gameData.getTime() + elapsed);
    }

    /**
     * Keeps the game score.
     * 
     * @param score the score calculated during the current update.
     */
    public void calculateScore(final int score) {
        this.gameData.setScore(score);
    }

    /**
     * Gets the {@link GameData} associated.
     * 
     * @return the {@link GameData}.
     */
    public GameData getGameData() {
        return this.gameData;
    }

    /**
     * Contains all the game data, these can only be modified using the
     * {@link GameDataManager} class, but the getters are public so the values are
     * accessible to anyone.
     */
    public class GameData {
        private int destroyedBalls;
        private int spawnedBullets;
        private int score;
        private double gameTime;

        /**
         * Gets the game score.
         * 
         * @return the game score.
         */
        public int getScore() {
            return this.score;
        }

        /**
         * Gets the current number of destroyed balls.
         * 
         * @return the number of destroyed balls.
         */
        public int getDestroyedBalls() {
            return this.destroyedBalls;
        }

        /**
         * Gets the current number of spawned bullets.
         * 
         * @return the number of spawned bullets.
         */
        public int getSpawnedBullets() {
            return this.spawnedBullets;
        }

        /**
         * Gets the current game time.
         * 
         * @return the current game time.
         */
        public double getTime() {
            return this.gameTime;
        }

        /**
         * Sets the destroyed balls counter.
         * 
         * @param destroyedBalls the new value to be set.
         */
        protected void setDestroyedBalls(final int destroyedBalls) {
            this.destroyedBalls = destroyedBalls;
        }

        /**
         * Sets the destroyed bullets counter.
         * 
         * @param spawnedBullets the new value to be set.
         */
        protected void setSpawnedBullets(final int spawnedBullets) {
            this.spawnedBullets = spawnedBullets;
        }

        /**
         * Sets the current score.
         * 
         * @param score the new value to be set.
         */
        protected void setScore(final int score) {
            this.score = score;
        }

        /**
         * Sets the game time.
         * 
         * @param gameTime the new value to be set.
         */
        protected void setGameTime(final double gameTime) {
            this.gameTime = gameTime;
        }
    }
}
