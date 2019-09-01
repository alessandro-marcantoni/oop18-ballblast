package ballblast.view.scenecontroller;

import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * 
 */
public class GameSelectionSceneController extends AbstractSceneController {

    @FXML
    private Button btnSurvivalMode;

    @FXML
    final void startSurvivalMode() {
        this.getController().startSurvivalMode();
        this.nextScene();
    }

    @Override
    protected final GameScenes getNextScene() {
        return GameScenes.GAME;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
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
