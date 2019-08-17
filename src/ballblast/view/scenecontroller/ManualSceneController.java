package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;

/**
 * 
 * Controller implementation for the manual scene.
 * 
 */

public class ManualSceneController extends AbstractSubSceneController {

    @Override
    protected ViewScenes getNextScene() {
        return ViewScenes.MENU;
    }

    @Override
    protected ViewScenes getPreviousScene() {
        return ViewScenes.MENU;
    }
}
