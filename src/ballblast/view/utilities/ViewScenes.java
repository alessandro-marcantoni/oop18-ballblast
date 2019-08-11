package ballblast.view.utilities;

/**
 * 
 * Enumeration that contains the game scenes.
 */
public enum ViewScenes {
    /*
     * The game scene.
     */
    GAME("Game.fxml");
    
    private static final String PATH = "/view/scenes";
    private final String selectedScene;
    
    private ViewScenes(final String scene) {
        this.selectedScene = scene;
    }
    
    public String getPath(){
        return ViewScenes.PATH + this.selectedScene;
    }
}
