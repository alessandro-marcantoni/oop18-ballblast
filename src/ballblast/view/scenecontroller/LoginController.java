package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.utilities.ViewScenes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * Login scene controller.
 * 
 */
public class LoginController extends AbstractSceneController {

    @FXML
    private TextField userTextField;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        Platform.runLater(() -> this.userTextField.requestFocus());
    }

    @Override
    protected final ViewScenes getNextScene() {
        return ViewScenes.MENU;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.MAIN;
    }
    /**
     * 
     */
    public void userLogin() {
        if (!this.userTextField.getText().equals("")) {
            nextScene();
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Insert a user name");
            alert.showAndWait();
        }
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.userLogin();
        }
    }

}
