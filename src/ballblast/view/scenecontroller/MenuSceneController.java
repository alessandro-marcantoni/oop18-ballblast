package ballblast.view.scenecontroller;

import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
//    private boolean gameMode = false;
//    private boolean leaderboard = false;
//    private boolean settings = false;
//    private boolean manual = false;
    private GameScenes selection;

    /**
     * Open the game mode selection scene.
     */
    @FXML
    protected void openGameMode() {
//        this.gameMode = true;
//        this.manual = false;
//        this.settings = false;
//        this.leaderboard = false;
        this.selection = GameScenes.GAME_MODE;
        this.nextScene();
    }

    /**
     * Open leader board scene.
     */
    @FXML
    private void openLeaderboard() {
//        this.leaderboard = true;
//        this.gameMode = false;
//        this.settings = false;
//        this.manual = false;
        this.selection = GameScenes.LEADERBOARD;
        this.nextScene();
    }

    /**
     * Open settings scene.
     */
    @FXML
    private void openSettings() {
        // TODO
//        this.settings = true;
//        this.nextScene();
        final Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Work in progress...");
        alert.setHeaderText(null);
        alert.setContentText("Not implemented yet.");
        alert.showAndWait();
    }

    /**
     * Open manual scene.
     */
    @FXML
    private void openManual() {
//        this.manual = true;
        this.selection = GameScenes.MANUAL;
        this.nextScene();
    }

    /**
     * Quit game.
     */
    @FXML
    private void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @Override
    public final GameScenes getNextScene() {
        return selection;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.LOGIN;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
