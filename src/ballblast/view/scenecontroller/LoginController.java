package ballblast.view.scenecontroller;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        Platform.runLater(() -> this.userTextField.requestFocus());
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MAIN;
    }

    /**
     * @throws IOException                  ...
     * @throws SAXException                 ...
     * @throws ParserConfigurationException ...
     * 
     */
    @FXML
    public void userLogin() throws ParserConfigurationException, SAXException, IOException {
        if (checkTextField()) {
            if (this.getController().checkLoginUser(userTextField.getText(), pswTextField.getText())) {
//            if (UserManager.login(userTextField.getText(), pswTextField.getText()).isPresent()) {
                this.nextScene();
            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Data mismatch.");
            alert.showAndWait();
        }
    }

    /**
     * @throws SAXException                 ...
     * @throws TransformerException         ...
     * @throws IOException                  ...
     * @throws ParserConfigurationException ...
     * 
     */
    @FXML
    public void userRegister() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        if (checkTextField()) {
            if (this.getController().checkRegisterUser(userTextField.getText(), pswTextField.getText())) {
                this.nextScene();
//            final Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("Work in progress...");
//            alert.setHeaderText(null);
//            alert.setContentText("Not implemented yet.");
//            alert.showAndWait();
            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("DANGER");
            alert.setHeaderText(null);
            alert.setContentText("Registrazione non effettuata.");
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
        } else {
            return true;
        }
        return true;

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                this.userLogin();
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
