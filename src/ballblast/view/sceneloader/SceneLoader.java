package ballblast.view.sceneloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ballblast.view.scenes.GameScenes;
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
     * Singleton getter.
     * @return
     *          a new {@link SceneLoader}.
     */
    public static SceneLoader getLoader() {
        return SINGLETON;
    }

    /**
     * 
     * @param scene
     *          the {@link GameScenes} to be loaded.
     * @return
     *          a new {@link SceneWrapper} for the scene which will be loaded.
     * @throws IOException
     *          help
     */
    public SceneWrapper getScene(final GameScenes scene) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final Path path = Paths.get(scene.getPath());
        final Parent root = (Parent) loader.load(new FileInputStream(path.toFile()));
        return new SceneWrapperImpl(new Scene(root), loader.getController());
    }

}
