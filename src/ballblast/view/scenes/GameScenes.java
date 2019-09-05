package ballblast.view.scenes;

/**
 * 
 * Enumeration that contains the game scenes.
 */
public enum GameScenes {
    /**
     * The game scene.
     */
    GAME("res/view/scenes/Game.fxml"),
    /**
     * The game mode selection menu scene.
     */
    GAME_MODE("res/view/scenes/GameSelection.fxml"),
    /**
     * The main menu scene.
     */
    MENU("res/view/scenes/Menu.fxml"),
    /**
     * The pause scene.
     */
    PAUSE("res/view/scenes/Pause.fxml"),
    /**
     * The settings scene.
     */
    SETTINGS("res/view/scenes/Settings.fxml"),
    /**
     * The leader board scene.
     */
    LEADERBOARD("res/view/scenes/Leaderboard.fxml"),
    /**
     * The manual scene.
     */
    MANUAL("res/view/scenes/Manual.fxml"),
    /**
     * The game over scene.
     */
    GAMEOVER("res/view/scenes/Gameover.fxml"),
    /**
     * The login scene.
     */
    LOGIN("res/view/scenes/Login.fxml"),
    /**
     * The initial scene.
     */
    MAIN("res/view/scenes/Main.fxml");

    private final String selectedScene;

    /**
     * 
     * @param scene the scene.
     */
    GameScenes(final String scene) {
        this.selectedScene = scene;
    }

    /**
     * 
     * @return the Path where the scene is stored.
     */
    public String getPath() {
        return this.selectedScene;
    }
}
