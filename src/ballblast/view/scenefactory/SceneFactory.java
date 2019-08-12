package ballblast.view.scenefactory;

import javafx.stage.Stage;

/**
 * 
 * Represent the factory for the game scenes.
 * 
 */
public interface SceneFactory {
    
    /**
     * Set a new game stage.
     * 
     * @param stage
     *          the stage to be loaded.
     */
    void setStage(Stage stage);
    /**
     * get the current stage.
     * @return
     *          the current stage.
     */
    Stage getStage();
    /**
     * Open the menu scene.
     */
    void openMenuScene();
    /**
     * Open the game mode selection.
     */
    void openGameSelection();
    /**
     * Open the settings scene.
     */
    void openSettingsScene();
    /**
     * Open the leader board scene.
     */
    void openLeaderboardScene();
    /**
     * Open the manual scene.
     */
    void openManualScene();
    /**
     * Open the game over scene.
     */
    void openGameOverScene();
    /**
     * Open the pause scene.
     */
    void openPauseScene();
    
/* OPZIONALI    
 *    void openRegisterScene();
 *    void openLoginScene();
 *    void openSelectModeScene();
 */
}
