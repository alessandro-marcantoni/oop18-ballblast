package ballblast;

import ballblast.view.View;
import ballblast.view.ViewImpl;
import ballblast.view.scenecontroller.GameSceneController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The class containing the main method to start the application.
 */
public final class BallBlast extends Application{
    private BallBlast() { };
    /**
     * The main entry point of the application.
     * @param args
     *      CLI arguments.
     */
    public static void main(final String [] args) {
        launch(args);
    }
    @Override
    public void start(final Stage stage) throws Exception {
        final View view = new ViewImpl(stage);
        //final Controller controller = new ControllerImpl(() -> new ModelImpl(), view);
        //view.viewLauncher(controller);
    }
}
