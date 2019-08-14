package ballblast.view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Sample Skeleton for 'Pause.fxml' Controller Class
 */

public class PauseSceneControllerImpl extends AbstractSubSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="borderPane"
        private BorderPane borderPane; // Value injected by FXMLLoader

        @FXML // fx:id="actualScoreLabel"
        private Label actualScoreLabel; // Value injected by FXMLLoader

        @FXML // fx:id="backToMenu"
        private Button backToMenu; // Value injected by FXMLLoader

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Pause.fxml'.";
            assert actualScoreLabel != null : "fx:id=\"actualScoreLabel\" was not injected: check your FXML file 'Pause.fxml'.";
            assert backToMenu != null : "fx:id=\"backToMenu\" was not injected: check your FXML file 'Pause.fxml'.";

        }
    }