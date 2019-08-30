package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.scenes.GameScenes;

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
    void launch(Controller controller);

//    /**
//     * 
//     * @return 
//     *          the scene factory.
//     */
//    SceneFactory getSceneFactory();
//
//    /**
//     * Start a new match.
//     * 
//     * @param gameSceneController 
//     *          the game scene controller
//     */
//    void startGame(GameSceneController gameSceneController);

    /**
     * Render method.
     */
    void render();

    /**
     * 
     * @param scene
     *          scene
     */
    void loadScene(GameScenes scene);
}
