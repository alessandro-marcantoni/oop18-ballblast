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
     *          default screen resolution
     */
    Pair<Integer, Integer> getDefaultResolution();
    /**
     * 
     * @return
     *          selected screen resolution          
     */
    Pair<Integer, Integer> getSelectedResolution();
    /**
     * Provides the factor by which coordinates are scaled about the center of the object.
     * 
     * @return
     *          the scale factor
     */
    double getScaleFactor();
    /**
     * 
     * @return
     *          true if full screen mode is enabled
     *          false otherwise
     */
    boolean isFullScreen();
    /**
     * Change the resolution to full screen.
     * 
     * @param fullScreen
     *          true if full screen is active
     *          false otherwise
     */
    void setFullScreen(boolean fullScreen);
    
    
}
