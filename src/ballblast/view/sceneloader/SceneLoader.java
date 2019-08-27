package ballblast.view.sceneloader;

import java.io.FileInputStream;
import java.io.IOException;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * A simple implementation of {@link SceneLoader} interface.
 *
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();

    /**
     * 
     * @return
     *          pippo
     */
    public static SceneLoader getLoader() {
        return SINGLETON;
    }

    /**
     * 
     * @param scene
     *          scene
     * @return
     *          pippo
     * @throws IOException
     *          help
     */
    public SceneWrapper getScene(final ViewScenes scene) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final String path = scene.getPath();
//        final Parent root = (Parent) loader.load(this.getClass().getResourceAsStream(scene.getPath()));
        final Parent root = (Parent) loader.load(new FileInputStream(scene.getPath()));
        return new SceneWrapperImpl(new Scene(root), loader.getController());
    }

}
