package ballblast.view.scenecontroller;

import javafx.fxml.FXML;

/**
 * 
 * Simple implementation for "Menu.fxml" controller class.
 * 
 */
public class MenuSceneControllerImpl extends AbstractSceneController {

    /**
     * Open leader board scene.
     */
    @FXML
    protected void openLeaderboard() {
        this.getSceneFactory().openLeaderboardScene();
    }
    /**
     * Open settings scene.
     */
    @FXML
    protected void openSettings() {
        this.getSceneFactory().openSettingsScene();
    }
    /**
     * Open manual scene.
     */
    @FXML
    protected void openManual() {
        this.getSceneFactory().openManualScene();
    }
    /**
     * Quit game.
     */
    @FXML
    protected void quitGame() {
        Runtime.getRuntime().exit(0);
    }
}
