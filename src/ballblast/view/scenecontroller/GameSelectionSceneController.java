package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * 
 * 
 */
public class GameSelectionSceneController extends AbstractSceneController{

    @FXML
    private Button btnSurvivalMode;

    @FXML
    void startSurvivalMode() {
        this.getController().startSurvivalMode();
        this.nextScene();
    }

    @Override
    protected final ViewScenes getNextScene() {
        return ViewScenes.GAME;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.MENU;
    }

}

