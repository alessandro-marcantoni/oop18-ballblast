package ballblast.view.sceneloader;

import ballblast.view.scenecontroller.AbstractSceneController;
import javafx.scene.Scene;

/**
 * TODO.
 */
public class SceneWrapperImpl implements SceneWrapper {

    private final Scene scene;
    private final AbstractSceneController controller;

    /**
     * TODO.
     * 
     * @param scene      TODO.
     * @param controller TODO.
     */
    public SceneWrapperImpl(final Scene scene, final AbstractSceneController controller) {
        this.scene = scene;
        this.controller = controller;
    }

    @Override
    public final Scene getScene() {
        return this.scene;
    }

    @Override
    public final AbstractSceneController getController() {
        return this.controller;
    }

}
