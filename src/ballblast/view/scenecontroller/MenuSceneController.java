package ballblast.view.scenecontroller;

import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;

/**
 * 
 * Simple implementation for "Menu.fxml" controller class.
 * 
 */
public class MenuSceneController extends AbstractSceneController {

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
    }
    /**
     * Open leader board scene.
     */
    @FXML
    protected void openLeaderboard() {
        this.leaderboard = true;
    }
    /**
     * Open settings scene.
     */
    @FXML
    protected void openSettings() {
        this.settings = true;
    }
    /**
     * Open manual scene.
     */
    @FXML
    protected void openManual() {
       this.manual = true;
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
 