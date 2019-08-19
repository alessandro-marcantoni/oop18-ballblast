package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.rendering.CanvasDrawer;
import ballblast.view.scenefactory.UIFactory;
import ballblast.view.states.GUIState;
import ballblast.view.states.IdleState;
import ballblast.view.states.InGameState;
import ballblast.view.states.PausedState;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
    private UIFactory userInterface;
    private CanvasDrawer canvasDrawer;

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
        this.userInterface = new UIFactory();
        this.resetGameCanvasCoordinates();
        this.canvasDrawer = new CanvasDrawer(this.canvas);
        // this.getController().registerSurvivalModeStarted( e -> {
           // this.canvasDrawer = new CanvasDrawer(this.canvas);
            // background
            // [..]
           // this.level
      //  });

        // DA IMPLEMENTARE
        userInterface.notifyAll(); //toDelete.

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        this.currentState.onKeyPressed(event);
    }
    /**
     * Method to handle the onKeyRelease.
     * @param event
     *          the key released.
     */
    public void handleReleased(final KeyEvent event) {
        this.currentState.onKeyReleased(event);
    }

    @Override
    public final void render() {
        // SCORE DA IMPLEMENTARE!!!
        //this.timeScoreGameLabel.setText(Integer.toString(this.getController().getTimeScore()));
        //this.ballsScoreGameLabel.setText(Integer.toString(this.getController().getBallsScore()));
        this.canvasDrawer.draw();
    }

    private void resetGameCanvasCoordinates() {
        // DA IMPLEMENTARE
    }

    @Override
    protected final ViewScenes getNextScene() {
        return ViewScenes.GAMEOVER;
    }

    @Override
    protected final ViewScenes getPreviousScene() {
        return ViewScenes.MENU;
    }

    /**
     * POWER UP DA IMPLEMENTARE
     */
    // private void handlePickupEvent(final PowerUp powerUp, final Player player) {}

    /**
     * 
     * @param state
     *          the state.
     */
    public void setState(final GUIState state) {
        if (this.currentState != null) {
            this.currentState.onStateExit();
        }
        this.currentState = state;
        this.currentState.onStateEntry();
    }
    /**
     * 
     * @return
     *          the paused state.
     */
    public GUIState getPausedState() {
        return this.pausedState;
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
     * @return
     *          the idle state.
     */
    public GUIState getIdleState() {
        return this.idleState;
    }

}
