package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.rendering.ImageLoader;
import ballblast.view.scenecontroller.AbstractSceneController;
import ballblast.view.sceneloader.SceneLoader;
import ballblast.view.sceneloader.SceneWrapper;
import ballblast.view.utilities.ViewScenes;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * TODO.
 */
public class ViewImpl implements View {

    private static final String GAME_TITLE = "BALL BLAST";
    private static final double MIN_WIDTH = 384;
    private static final double MIN_HEIGHT = 200;
    private final Stage stage;
    private Controller controller;
    private AbstractSceneController currentScene;
    private boolean viewStarted;

    /**
     * 
     * @param primaryStage
     *          primaryStage
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
       this.loadScene(ViewScenes.MAIN);
//       ImageLoader.getLoader().loadAll();
    }

    @Override
    public final void render() {
        Platform.runLater(() -> this.currentScene.render());
    }

    @Override
    public final void loadScene(final ViewScenes scene) {
        try {
            final SceneWrapper wrapper = SceneLoader.getLoader().getScene(scene);
            wrapper.getController().init(controller, this);
            this.currentScene = wrapper.getController();

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
}
