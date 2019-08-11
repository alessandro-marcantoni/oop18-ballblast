package ballblast.view.utilities;

/**
 * 
 * Enumeration that contains the game scenes.
 */
public enum ViewScenes {
    /**
     * The game scene.
     */
    GAME("Game.fxml"),
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
    GAMEOVER("Gameover.fxml");
    
    /* OPZIONALI
     * LOGIN
     * REGISTER
     * SELECTMODE
     */
    
    private static final String PATH = "/view/scenes";
    private final String selectedScene;
    
    private ViewScenes(final String scene) {
        this.selectedScene = scene;
    }
    
    public String getPath(){
        return ViewScenes.PATH + this.selectedScene;
    }
}
