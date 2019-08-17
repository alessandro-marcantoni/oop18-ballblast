package ballblast.view.scenecontroller;
/**
 * Sample Skeleton for 'Leaderboard.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * 
 * Scene controller for Leaderboard scene.
 * 
 */
public class LeaderboardSceneController extends AbstractSubSceneController {


        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="player1label"
        private Label player1label; // Value injected by FXMLLoader

        @FXML // fx:id="score1label"
        private Label score1label; // Value injected by FXMLLoader

        @FXML // fx:id="player2label"
        private Label player2label; // Value injected by FXMLLoader

        @FXML // fx:id="player3label"
        private Label player3label; // Value injected by FXMLLoader

        @FXML // fx:id="player4label"
        private Label player4label; // Value injected by FXMLLoader

        @FXML // fx:id="player5label"
        private Label player5label; // Value injected by FXMLLoader

        @FXML // fx:id="player6label"
        private Label player6label; // Value injected by FXMLLoader

        @FXML // fx:id="player7label"
        private Label player7label; // Value injected by FXMLLoader

        @FXML // fx:id="player8label"
        private Label player8label; // Value injected by FXMLLoader

        @FXML // fx:id="score2label"
        private Label score2label; // Value injected by FXMLLoader

        @FXML // fx:id="score3label"
        private Label score3label; // Value injected by FXMLLoader

        @FXML // fx:id="score4label"
        private Label score4label; // Value injected by FXMLLoader

        @FXML // fx:id="score5label"
        private Label score5label; // Value injected by FXMLLoader

        @FXML // fx:id="score6label"
        private Label score6label; // Value injected by FXMLLoader

        @FXML // fx:id="score7label"
        private Label score7label; // Value injected by FXMLLoader

        @FXML // fx:id="score8label"
        private Label score8label; // Value injected by FXMLLoader

        @FXML // fx:id="player9label"
        private Label player9label; // Value injected by FXMLLoader

        @FXML // fx:id="player10label"
        private Label player10label; // Value injected by FXMLLoader

        @FXML // fx:id="score9label"
        private Label score9label; // Value injected by FXMLLoader

        @FXML // fx:id="score10label"
        private Label score10label; // Value injected by FXMLLoader

        @FXML // fx:id="backToMenuBtn"
        private Button backToMenuBtn; // Value injected by FXMLLoader

        @FXML // This method is called by the FXMLLoader when initialization is complete
        final void initialize() {
            assert player1label != null : "fx:id=\"player1label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score1label != null : "fx:id=\"score1label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player2label != null : "fx:id=\"player2label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player3label != null : "fx:id=\"player3label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player4label != null : "fx:id=\"player4label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player5label != null : "fx:id=\"player5label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player6label != null : "fx:id=\"player6label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player7label != null : "fx:id=\"player7label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player8label != null : "fx:id=\"player8label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score2label != null : "fx:id=\"score2label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score3label != null : "fx:id=\"score3label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score4label != null : "fx:id=\"score4label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score5label != null : "fx:id=\"score5label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score6label != null : "fx:id=\"score6label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score7label != null : "fx:id=\"score7label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score8label != null : "fx:id=\"score8label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player9label != null : "fx:id=\"player9label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert player10label != null : "fx:id=\"player10label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score9label != null : "fx:id=\"score9label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert score10label != null : "fx:id=\"score10label\" was not injected: check your FXML file 'Leaderboard.fxml'.";
            assert backToMenuBtn != null : "fx:id=\"backToMenuBtn\" was not injected: check your FXML file 'Leaderboard.fxml'.";

        }

        @Override
        protected final ViewScenes getNextScene() {
            return ViewScenes.MENU;
        }

        @Override
        protected final ViewScenes getPreviousScene() {
            return ViewScenes.MENU;
        }
    }
