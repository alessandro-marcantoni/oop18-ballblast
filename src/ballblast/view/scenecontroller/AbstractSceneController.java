package ballblast.view.scenecontroller;

import ballblast.view.View;
import ballblast.view.scenefactory.SceneFactory;
import javafx.scene.input.KeyEvent;

/**
 * 
 * Abstract class which partial implements {@link SceneController} interface.
 * 
 */
public abstract class AbstractSceneController implements SceneController {

    private SceneFactory sceneFactory;
    private View view;
    
    @Override
    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }
    
    /**
     * Redraws the scene on the screen.
     */
    public void render() {
        // Empty for subclasses.
    }
    
    protected View getView() {
        return this.view;
    }
    
    
    public void onKeyPressed(final KeyEvent event) {
        // Empty for subclasses.
    }

}
