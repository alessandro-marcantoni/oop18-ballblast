package ballblast.view.scenefactory;

import ballblast.settings.SettingsImpl;
import ballblast.view.View;
import ballblast.view.sceneloader.SceneLoader;
import ballblast.view.sceneloader.SceneLoaderImpl;
import ballblast.view.utilities.ViewScenes;
import javafx.stage.Stage;

/**
 * 
 * A simple implementation of {@link SceneFactory} interface.
 * 
 */
public class SceneFactoryImpl implements SceneFactory {

    private Stage stage;
    private final View view;
    private final SceneLoader sceneLoader;
    
    /**
     * Public constructor for SceneFactory.
     * 
     * @param view
     *          the view of the game.
     */
    public SceneFactoryImpl(View view) {
        this.view = view;
        this.sceneLoader = new SceneLoaderImpl(this.view);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public final void openMenuScene() {
        this.openNewScene(ViewScenes.MENU);
    }


    @Override
    public void openSettingsScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void openLeaderboardScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void openManualScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void openGameOverScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void openPauseScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void openGameSelection() {
        // TODO Auto-generated method stub
        
    }
    
    private void openNewScene(ViewScenes scene) {
        this.checkFullScreen();
        this.sceneLoader.loadScene(this.stage, scene);
    }

    private void checkFullScreen() {
        // DA IMPLEMENTARE
    }
    
    private void createNewStage() {
        // DA IMPLEMENTARE
    }

}
