package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.model.Model;
import ballblast.view.View;
import ballblast.view.rendering.CanvasDrawer;
import ballblast.view.scenes.GameScenes;
import ballblast.view.scenes.UIFactory;
import ballblast.view.states.GUIState;
import ballblast.view.states.InGameState;
import ballblast.view.states.PausedState;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    private boolean gameover;
    private static final double FONT_SIZE = 8.0;

    /**
     * @param controller the {@link Controller}.
     * @param view       the {@link View}.
     */
    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.inGameState = new InGameState(this, controller);
        this.pausedState = new PausedState(this, controller, this.pausePane);
        this.userInterface = new UIFactory();

        this.resetGameCanvasCoordinates();
        this.canvasDrawer = new CanvasDrawer(this.canvas);

        // Listeners for resized windows.
        this.canvasContainer.widthProperty().addListener(w -> this.resizeCanvas());
        this.canvasContainer.heightProperty().addListener(h -> this.resizeCanvas());
        this.statusBarContainer.prefWidthProperty().bind(this.canvas.widthProperty());
        this.setState(this.inGameState);

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        this.currentState.onKeyPressed(event);
    }

    /**
     * Method to handle the onKeyRelease.
     * 
     * @param event the key released.
     */
    public void handleReleased(final KeyEvent event) {
        this.currentState.onKeyReleased(event);
    }

    @Override
    public final void render() {
        if (this.isGameover()) {
            this.nextScene();
        }
        this.clearCanvas();
        this.score.setText(Double.toString(this.getController().getGameData().getScore()));
        this.balls.setText(Integer.toString(this.getController().getGameData().getDestroyedBalls()));
        this.canvasDrawer.draw(this.getController().getGameObjects());
    }

    private void resetGameCanvasCoordinates() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setFont(new Font(FONT_SIZE));
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.LIGHTGREY);
        gc.save();
        final double canvasWidth = this.canvas.getWidth();
        final double canvasHeight = this.canvas.getHeight();
        gc.clearRect(0, 0, canvasWidth, canvasWidth);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.scale(1, -1);
        gc.scale(canvasWidth / (Model.WORLD_WIDTH), canvasHeight / Model.WORLD_HEIGHT);
    }

    // Clear the canvas after every render. It avoids ghosting effect.
    private void clearCanvas() {
        this.canvas.getGraphicsContext2D().restore();
        this.resetGameCanvasCoordinates();
    }

    // Resize the canvas proportionally when the app window is resized by the user.
    private void resizeCanvas() {
        final double parentWidth = this.canvasContainer.getWidth();
        final double parentHeight = this.canvasContainer.getHeight();
        final double ratio = parentWidth / parentHeight;
        final double expectedRatio = Model.WORLD_WIDTH / Model.WORLD_HEIGHT;

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
    public final GameScenes getNextScene() {
        return GameScenes.GAMEOVER;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.GAMEOVER;
    }

    /**
     * 
     * @param state the state.
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
     * @return the paused state.
     */
    public GUIState getPausedState() {
        return this.pausedState;
    }

    /**
     * 
     * @return the in game state.
     */
    public GUIState getInGameState() {
        return this.inGameState;
    }

    /**
     * @param gameover
     *          true if the player has lost.
     */
    public void setGameover(final boolean gameover) {
        this.gameover = gameover;
    }

    /**
     * 
     * @return True if the player has lost.
     */
    public boolean isGameover() {
        return this.gameover;
    }
}
