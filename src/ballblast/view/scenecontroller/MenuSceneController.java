package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 
 * Simple implementation for "Menu.fxml" controller class.
 * 
 */
public class MenuSceneController extends AbstractSceneController {

    @FXML
    private Button startNewGameBtn;

    @FXML
    private Button leaderboardBtn;

    @FXML
    private Button manualBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button quitBtn;
    private boolean gameMode = false;
    private boolean leaderboard = false;
    private boolean settings = false;
    private boolean manual = false;
    /**
     * Open the game mode selection scene.
     */
    @FXML
    protected void openGameMode() {
        this.gameMode = true;
        this.nextScene();
    }
    /**
     * Open leader board scene.
     */
    @FXML
    protected void openLeaderboard() {
        this.leaderboard = true;
        this.nextScene();
    }
    /**
     * Open settings scene.
     */
    @FXML
    protected void openSettings() {
        this.settings = true;
        this.nextScene();
    }
    /**
     * Open manual scene.
     */
    @FXML
    protected void openManual() {
       this.manual = true;
       this.nextScene();
    }
    /**
     * Quit game.
     */
    @FXML
    protected void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @Override
    protected final ViewScenes getNextScene() {
        ViewScenes selection = ViewScenes.MENU;
        if (this.gameMode) {
            selection = ViewScenes.GAME_MODE;
        }
        if (this.leaderboard) {
            selection = ViewScenes.LEADERBOARD;
        }
        if (this.settings) {
            selection = ViewScenes.SETTINGS;
        }
        if (this.manual) {
            selection = ViewScenes.MANUAL;
        }
        return selection;
    }
    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.LOGIN;
    }
}
