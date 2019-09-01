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
    private Button btnBackToMenu;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.user.setText(controller.getCurrentUser());
        this.score.setText(String.valueOf(controller.getGameData().getScore()));
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

}