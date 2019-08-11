package ballblast.view;

import java.util.List;
import java.util.Optional;

import ballblast.view.entities.ViewEntity;
import ballblast.view.scenecontroller.GameSceneController;
import ballblast.view.scenefactory.SceneFactory;

/**
 * 
 * Interface that represents the view of the game.
 * 
 */

public interface View {

    /**
     * 
     * @param controller 
     *          the controller
     */
    void viewLauncher(Controller controller);
    
    /**
     * 
     * @return 
     *          the scene factory.
     */
    SceneFactory getSceneFactory();
    
    /**
     * Start a new match.
     * 
     * @param gameSceneController 
     *          the game scene controller
     */
    void startGame(GameSceneController gameSceneController);
    
    /**
     * 
     * @param viewEntities 
     *          entities in the view
     */
    void render(List<Optional<ViewEntity>> viewEntities);
    
    /**
     * Start the view thread.
     */
    void startRender();
    
    /**
     * Stop the view thread.
     */
    void stopRender();
   
    /**
     * 
     * @return the controller.
     */
    Controller getController();
}
