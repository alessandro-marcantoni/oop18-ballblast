package ballblast.view.sceneloader;

import ballblast.view.utilities.ViewScenes;
import javafx.stage.Stage;

/**
 * 
 * Interface which provides the methods to load the game scenes.
 * 
 */
public interface SceneLoader {
   
    /**
     * 
     * @param stage
     *          stage to be loaded
     * @param scene
     *          required game scene
     */
    void loadScene(Stage stage, ViewScenes scene);
}
