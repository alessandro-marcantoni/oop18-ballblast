package ballblast.view.scenecontroller;

import ballblast.view.scenes.GameScenes;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * Controller implementation for the manual scene.
 * 
 */

public class ManualSceneController extends AbstractSubSceneController {

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
