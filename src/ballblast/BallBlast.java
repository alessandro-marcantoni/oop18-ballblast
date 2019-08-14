package ballblast;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The class containing the main method to start the application.
 */
public final class BallBlast extends Application{
    private BallBlast() { };

    @Override
    public void start(final Stage stage) throws Exception {
        //final View view = new ViewImpl(stage);
        //final Controller controller = new ControllerImpl(() -> new ModelImpl(), view);
        //view.viewLauncher(controller);
          
    }
    
    /**
     * The main entry point of the application.
     * @param args
     *      CLI arguments.
     */
    public static void main(final String [] args) {
        launch();
    }
}
