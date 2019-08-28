package ballblast.view.scenefactory;

import ballblast.view.View;
import ballblast.view.sceneloader.SceneLoader;
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
    public SceneFactoryImpl(final View view) {
        this.view = view;
        this.sceneLoader = new SceneLoader();
    }
    /**
     * @param stage TODO.
     */
    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;
    }
    /**
     * @return TODO.
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public final void openMenuScene() {
        this.openNewScene(ViewScenes.MENU);
    }


    @Override
    public final void openSettingsScene() {
        this.openNewScene(ViewScenes.SETTINGS);
    }

    @Override
    public final void openLeaderboardScene() {
        this.openNewScene(ViewScenes.LEADERBOARD);
    }

    @Override
    public final void openManualScene() {
        this.openNewScene(ViewScenes.MANUAL);
    }

    @Override
    public final void openGameOverScene() {
        this.openNewScene(ViewScenes.GAMEOVER);
    }

    @Override
    public final void openPauseScene() {
       this.openNewScene(ViewScenes.PAUSE);
    }

    @Override
    public final void openGameSelection() {
        this.openNewScene(ViewScenes.GAME_MODE);
    }

    private void openNewScene(final ViewScenes scene) {
        this.checkFullScreen();
        scene.compareTo(scene); //to delete.
       //this.sceneLoader.loadScene(this.stage, scene);
    }

    private void checkFullScreen() {
        // DA IMPLEMENTARE
        view.notifyAll(); //To delete.
        createNewStage(); //To delete.
    }

    private void createNewStage() {
        // DA IMPLEMENTARE
        sceneLoader.notifyAll();
    }

}
