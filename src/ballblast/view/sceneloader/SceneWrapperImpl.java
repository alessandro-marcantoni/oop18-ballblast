package ballblast.view.sceneloader;

import ballblast.view.scenecontroller.AbstractSceneController;
import javafx.scene.Scene;

public class SceneWrapperImpl implements SceneWrapper {

    private final Scene scene;
    private final AbstractSceneController controller;
    
    public SceneWrapperImpl(final Scene scene, final AbstractSceneController controller) {
        this.scene = scene;
        this.controller = controller;
    }
    
    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public AbstractSceneController getController() {
        return this.controller;
    }

}
