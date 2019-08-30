package ballblast.view.scenes;

/**
 * 
 * Enumeration that contains the game scenes.
 */
public enum GameScenes {
    /**
     * The game scene.
     */
    GAME("Game.fxml"),
    /**
     * The game mode selection menu scene.
     */
    GAME_MODE("GameSelection.fxml"),
    /**
     * The main menu scene.
     */
    MENU("Menu.fxml"),
    /**
     * The pause scene.
     */
    PAUSE("Pause.fxml"),
    /**
     * The settings scene.
     */
    SETTINGS("Settings.fxml"),
    /**
     * The leader board scene.
     */
    LEADERBOARD("Leaderboard.fxml"),
    /**
     * The manual scene.
     */
    MANUAL("Manual.fxml"),
    /**
     * The game over scene.
     */
    GAMEOVER("Gameover.fxml"),
    /**
     * The login scene.
     */
    LOGIN("Login.fxml"),
    /**
     * The initial scene.
     */
    MAIN("Main.fxml");

    /* OPZIONALI
     * REGISTER
     */

    private final String selectedScene;
    private static final String PATH = "res/view/scenes/";
    /**
     * 
     * @param scene
     *          the scene.
     */
    GameScenes(final String scene) {
        this.selectedScene = scene;
    }
    /**
     * 
     * @return
     *          the Path where the scene is stored.
     */
    public String getPath() {
        return GameScenes.PATH + this.selectedScene;
    }
}
