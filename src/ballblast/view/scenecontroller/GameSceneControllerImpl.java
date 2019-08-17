package ballblast.view.scenecontroller;

import ballblast.model.levels.Level;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameSceneControllerImpl extends AbstractSceneController implements GameSceneController {
    
    @FXML // fx:id game
    private BorderPane game;
    
    @FXML // fx:id timeScoreGameLabel
    private Label timeScoreGameLabel;
    
    @FXML // fx:id ballsScoreGameLabel
    private Label ballsScoreGameLabel;
    
    @FXML // fx:id topHBox
    private HBox topHBox;
    
    @FXML // fx:id bottomHBox
    private HBox bottomHBox;
    
    @FXML // fx:id canvas
    private Canvas canvas;
    
    /**
     * Initialize the FXML components.
     */
    @FXML
    protected final void initialize() {
        assert game != null : "fx:id game was not injected: check FXML file 'Game.fxml'";
        assert timeScoreGameLabel != null : "fx:id timeScoreGameLabel was not injected: check FXML file 'Game.fxml'";
        assert ballsScoreGameLabel != null : "fx:id ballsScoreGameLabel was not injected: check FXML file 'Game.fxml'";
        assert topHBox != null : "fx:id topHBox was not injected: check FXML file 'Game.fxml'";
        assert bottomHBox != null : "fx:id bottomHBox was not injected: check FXML file 'Game.fxml'";
        assert canvas != null : "fx:id canvas was not injected: check FXML file 'Game.fxml'";
    }
    
    public GameSceneControllerImpl() {
    }

    @Override
    public Canvas getCanvas() {
        this.canvas.setWidth(this.game.getWidth());
        this.canvas.setHeight(this.game.getHeight());
        return this.canvas;
    }

    @Override
    public void setGameData(Level level) {
        this.timeScoreGameLabel.setText(String.valueOf(level.getGameScore()));
        /**
         * Gli score da memorizzare sono due: time e balls. Da modificare quando ci sarà modo di tener traccia dei diversi score.
         */
    }

    @Override
    protected ViewScenes getNextScene() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ViewScenes getPreviousScene() {
        // TODO Auto-generated method stub
        return null;
    }

}
