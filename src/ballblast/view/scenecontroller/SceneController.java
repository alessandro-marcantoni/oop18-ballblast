package ballblast.view.scenecontroller;

import ballblast.view.scenefactory.SceneFactory;

/**
 * 
 * A simple scene controller.
 */
public interface SceneController {

    /**
     * 
     * @param sceneFactory
     *          the scene factory.
     */
    void setSceneFactory (SceneFactory sceneFactory);
    
    /**
     * 
     * @return
     *          scene factory used to change the scenes.
     */
    SceneFactory getSceneFactory();
    
    /**
     * Redraw the menu on the screen.
     */
    void render();
}
