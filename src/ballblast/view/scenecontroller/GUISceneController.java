package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.model.levels.Level;
import ballblast.view.View;
import ballblast.view.states.GUIState;
import ballblast.view.states.IdleState;
import ballblast.view.states.InGameState;
import ballblast.view.states.PausedState;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * 
 * Graphical User Interface scene controller.
 * 
 */
public class GUISceneController extends AbstractSceneController {

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

    @FXML // fx:id message
    private Pane message;

    @FXML // fx:id pausePane
    private Pane pausePane;

    private GUIState currentState;
    private GUIState idleState;
    private GUIState inGameState;
    private GUIState pausedState;
    private UIFactory = userInterface;

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
        assert message != null : "fx:id message was not injected: check FXML file 'Game.fxml'";
        assert pausePane != null : "fx:id message was not injected: check FXML file 'Game.fxml'";
    }
    /**
     * @param controller
     *          the {@link Controller}.
     * @param view
     *          the {@link View}.
     */
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.idleState = new IdleState(this, controller, this.message);
        this.inGameState = new InGameState(this, controller);
        this.pausedState = new PausedState(this, controller, this.pausePane);
        

    }


//    /**
//     * 
//     * @return
//     */
//    public final Canvas getCanvas() {
//        this.canvas.setWidth(this.game.getWidth());
//        this.canvas.setHeight(this.game.getHeight());
//        return this.canvas;
//    }

    public void setGameData(Level level) {
        this.timeScoreGameLabel.setText(String.valueOf(level.getGameScore()));
        /**
         * Gli score da memorizzare sono due: time e balls. Da modificare quando ci sarà modo di tener traccia dei diversi score.
         */
    }

    @Override
    protected final ViewScenes getNextScene() {
        return null;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return null;
    }
    /**
     * 
     * @return
     *          the in game state.
     */
    public GUIState getInGameState() {
        return this.inGameState;
    }
    /**
     * 
     * @param state
     *          the state.
     */
    public void setState(final GUIState state) {

    }
    /**
     * 
     * @return
     *          the paused state.
     */
    public GUIState getPausedState() {
        return this.pausedState;
    }

}
