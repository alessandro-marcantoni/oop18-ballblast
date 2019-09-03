package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.scenecontroller.AbstractSceneController;
import ballblast.view.sceneloader.SceneLoader;
import ballblast.view.sceneloader.SceneWrapper;
import ballblast.view.scenes.GameScenes;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * A simple implementation of {@link View}.
 */
public class ViewImpl implements View {

    private static final String GAME_TITLE = "BALL BLAST";
    private static final double MIN_WIDTH = 384;
    private static final double MIN_HEIGHT = 200;
    private final Stage stage;
    private Controller controller;
    private AbstractSceneController currentSceneController;
    private boolean viewStarted;
    private boolean gameover;

    /**
     * 
     * @param primaryStage primaryStage
     */
    public ViewImpl(final Stage primaryStage) {
        super();
        this.stage = primaryStage;
        this.viewStarted = false;
    }

    @Override
    public final void launch(final Controller controller) {
        this.controller = controller;
        this.stage.setTitle(GAME_TITLE);
        this.stage.setMinHeight(MIN_HEIGHT);
        this.stage.setMinWidth(MIN_WIDTH);
        this.stage.setMaximized(true);
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
        this.gameover = false;
        this.loadScene(GameScenes.MAIN);
    }

    @Override
    public final void render() {

        Platform.runLater(() -> {
            this.currentSceneController.render();
        });
    }

    @Override
    public final void loadScene(final GameScenes scene) {
        try {
            final SceneWrapper wrapper;
            if (gameover) {
                wrapper = SceneLoader.getLoader().getScene(this.currentSceneController.getNextScene());
            } else {
                wrapper = SceneLoader.getLoader().getScene(scene);
            }
            wrapper.getController().init(controller, this);
            this.currentSceneController = wrapper.getController();

            final Parent root = wrapper.getScene().getRoot();
            root.requestFocus();
            root.setOnKeyPressed(wrapper.getController()::onKeyPressed);

            Platform.runLater(() -> {
                final double oldWidth = this.stage.getWidth();
                final double oldHeigth = this.stage.getHeight();
                this.stage.setScene(wrapper.getScene());
                this.stage.setWidth(oldWidth);
                this.stage.setHeight(oldHeigth);
                if (!this.viewStarted) {
                    this.stage.show();
                    this.viewStarted = true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void setGameOver(final boolean gameover) {
        this.currentSceneController.setGameover(gameover);
        this.gameover = gameover;
        if (gameover) {
            this.currentSceneController.nextScene();
        }
    }

}
