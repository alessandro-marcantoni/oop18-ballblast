package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * Controller implementation for the manual scene.
 * 
 */

public class ManualSceneController extends AbstractSubSceneController {

    @Override
    protected final ViewScenes getNextScene() {
        return ViewScenes.MENU;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.MENU;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
