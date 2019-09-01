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
     * @param controller the controller
     */
    void launch(Controller controller);

    /**
     * Render method.
     */
    void render();

    /**
     * 
     * @param scene scene
     */
    void loadScene(GameScenes scene);
}
