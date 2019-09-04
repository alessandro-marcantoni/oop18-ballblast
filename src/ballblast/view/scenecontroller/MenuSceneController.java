package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * The {@link SceneController} for the main menu scene.
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
    private GameScenes selection;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.selection = GameScenes.MENU;
    }
    /**
     * Open the game mode selection scene.
     * Method is used when the user clicks the "START NEW GAME" button.
     * It is handled from JavaFX GameSelection.fxml file.
     */
    @FXML
    protected void openGameMode() {
        this.selection = GameScenes.GAME_MODE;
        this.nextScene();
    }

    /**
     * Open leader board scene.
     * Method is used when the user clicks the "LEADERBOARD" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void openLeaderboard() {
        this.selection = GameScenes.LEADERBOARD;
        this.nextScene();
    }

    /**
     * Open settings scene.
     * Method is used when the user clicks the "SETTINGS" button.
     * It is handled from JavaFX Menu.fxml file.
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
     * Method is used when the user clicks the "MANUAL" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void openManual() {
        this.selection = GameScenes.MANUAL;
        this.nextScene();
    }

    /**
     * Quit game.
     * Method is used when the user clicks the "QUIT" button.
     * It is handled from JavaFX Menu.fxml file.
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
