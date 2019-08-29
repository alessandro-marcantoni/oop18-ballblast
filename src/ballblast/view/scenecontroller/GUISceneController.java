package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.model.Model;
import ballblast.model.gameobjects.GameObjectTypes;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * 
 * Graphical User Interface scene controller.
 * 
 */
public class GUISceneController extends AbstractSceneController {
    @FXML
    private BorderPane canvasContainer;

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane statusBarContainer;

    @FXML
    private BorderPane timebarContainer;


    @FXML
    private HBox powers;

    @FXML
    private Label score;

    @FXML
    private Pane playerShooter;

    @FXML
    private Label balls;

    @FXML
    private BorderPane pausePane;

    private GUIState currentState;
    private GUIState inGameState;
    private GUIState pausedState;
    private UIFactory userInterface;
    private CanvasDrawer canvasDrawer;


    /**
     * @param controller
     *          the {@link Controller}.
     * @param view
     *          the {@link View}.
     */
    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.inGameState = new InGameState(this, controller);
        this.pausedState = new PausedState(this, controller, this.pausePane);
        this.userInterface = new UIFactory();

        this.resetGameCanvasCoordinates();
        this.canvasDrawer = new CanvasDrawer(this.canvas);

        // Adjust the canvas when resizing the window.
        this.canvasContainer.widthProperty().addListener(w -> this.resizeCanvas());
        this.canvasContainer.heightProperty().addListener(h -> this.resizeCanvas());
        this.setState(this.inGameState);
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
        this.score.setText(Double.toString(this.getController().getGameData().getScore()));
        this.balls.setText(Integer.toString(this.getController().getGameData().getDestroyedBalls()));
        this.canvasDrawer.draw(this.getController().getGameObjects());
    }

    private void resetGameCanvasCoordinates() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setFill(Color.AQUAMARINE);
        gc.save();
        final double canvasWidth = this.canvas.getWidth();
        final double canvasHeight = this.canvas.getHeight();
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.scale(1, -1);
//        gc.translate(0, -canvasHeight);
        gc.scale(canvasWidth / (Model.WORLD_WIDTH), canvasHeight / Model.WORLD_HEIGHT);
    }

    private void resizeCanvas() {
        final double parentWidth = this.canvasContainer.getWidth();
        final double parentHeight = this.canvasContainer.getHeight();
        final double ratio = parentWidth / parentHeight;
        final double expectedRatio = (Model.WORLD_WIDTH + Model.WALL_OFFSET) / (Model.WORLD_HEIGHT + Model.WALL_OFFSET);

        if (ratio < expectedRatio) {
            this.canvas.setWidth(parentWidth);
            this.canvas.setHeight(parentWidth / expectedRatio);
        } else {
            this.canvas.setWidth(parentHeight * expectedRatio);
            this.canvas.setHeight(parentHeight);
        }
        this.canvas.getGraphicsContext2D().restore();
        this.resetGameCanvasCoordinates();
        this.render();
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
//    /**
//     * 
//     * @return
//     *          the idle state.
//     */
//    public GUIState getIdleState() {
//        return this.idleState;
//    }

}
