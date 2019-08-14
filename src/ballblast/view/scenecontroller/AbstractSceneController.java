package ballblast.view.scenecontroller;

import ballblast.view.scenefactory.SceneFactory;

/**
 * 
 * Abstract class which partial implements {@link SceneController} interface.
 * 
 */
public abstract class AbstractSceneController implements SceneController {

    private SceneFactory sceneFactory;
    
    @Override
    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }

}
