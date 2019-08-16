package ballblast.view.sceneloader;

import ballblast.view.scenecontroller.AbstractSceneController;
import javafx.scene.Scene;

public interface SceneWrapper {
    Scene getScene();
    AbstractSceneController getController();
}
