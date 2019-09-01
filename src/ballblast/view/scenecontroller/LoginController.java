package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
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
    @FXML
    private TextField pswTextField;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        Platform.runLater(() -> this.userTextField.requestFocus());
    }

    @Override
    protected final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MAIN;
    }

    /**
     * 
     */
    @FXML
    public void userLogin() {
        if (checkTextField()) {
//            if (this.getController().checkLogin(userTextField.getText(), pswTextField.getText())) {
            this.nextScene();
//            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Data mismatch.");
            alert.showAndWait();
        }
    }

    /**
     * 
     */
    @FXML
    public void userRegister() {
        if (checkTextField()) {
//            if (this.getController().checkRegisterUser(userTextField.getText(), pswTextField.getText())) {
//            this.nextScene();
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Work in progress...");
            alert.setHeaderText(null);
            alert.setContentText("Not implemented yet.");
            alert.showAndWait();
//            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Data mismatch.");
            alert.showAndWait();
        }
    }

    private boolean checkTextField() {
        if (this.userTextField.getText().equals("")) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Insert a user name");
            alert.showAndWait();
        } else if (this.pswTextField.getText().equals("")) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Insert a password");
            alert.showAndWait();
        }
        return true;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.userLogin();
        }
    }

}
