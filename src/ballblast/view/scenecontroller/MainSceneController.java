package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * 
 * Controller for the Main scene.
 * 
 */
public class MainSceneController extends AbstractSceneController {

    @Override
    protected final ViewScenes getNextScene() {
        return ViewScenes.LOGIN;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.MAIN;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
