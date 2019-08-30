package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    // TO DELETE
    // Inserito per andare più veloce nel testing.
    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.startSurvivalMode();
        }
    }

}

