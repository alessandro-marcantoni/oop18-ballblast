package ballblast.settings;

import javafx.util.Pair;

/**
 * 
 * The interface which manage the game settings.
 * 
 */
public interface Settings {

    /**
     * 
     * @return
     */
    Pair<Integer, Integer> getDefaultResolution();
    /**
     * 
     * @return
     */
    Pair<Integer, Integer> getSelectedResolution();
    /**
     * 
     * @return
     */
    double getScaleFactor();
    /**
     * 
     * @return
     */
    boolean isFullScreen();
    /**
     * 
     * @param fullScreen
     */
    void setFullScreen(boolean fullScreen);
    
    
}
