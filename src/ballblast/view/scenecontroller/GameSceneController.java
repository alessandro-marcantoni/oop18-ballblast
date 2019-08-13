package ballblast.view.scenecontroller;

import ballblast.model.levels.Level;
import javafx.scene.canvas.Canvas;

/**
 * 
 * Interface that represent the game scene controller.
 * 
 */
public interface GameSceneController {

    /**
     * 
     * @return 
     *          the game page canvas.
     */
    Canvas getCanvas();
    
    /**
     * 
     * @param level
     *          the game level.
     */
    void setGameData(Level level);
    
}
