package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 
 *
 */
public class GameOverSceneController extends AbstractSubSceneController {

    @FXML
    private Label score;
    @FXML
    private Label user;
    @FXML
    private Label ballsDestroyed;
    @FXML
    private Label bulletsShot;
    @FXML
    private Button btnBackToMenu;
    @FXML
    private Button btnOpenLeaderboard;
    @FXML
    private Button btnNewGame;
    private GameScenes selection;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.selection = GameScenes.MENU;
        this.user.setText(controller.getCurrentUser());
        this.score.setText(String.valueOf(controller.getGameData().getScore()));
        this.ballsDestroyed.setText(String.valueOf(controller.getGameData().getDestroyedBalls()));
        this.bulletsShot.setText(String.valueOf(controller.getGameData().getSpawnedBullets()));
    }

    @FXML
    private void openNewGame() {
        this.selection = GameScenes.GAME_MODE;
        this.nextScene();
    }

    @FXML
    private void openLeaderboard() {
        this.selection = GameScenes.LEADERBOARD;
        this.nextScene();
    }

    @Override
    public final GameScenes getNextScene() {
        return this.selection;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

}