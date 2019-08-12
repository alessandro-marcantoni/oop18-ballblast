package ballblast.view.sceneloader;

import ballblast.settings.SettingsImpl;
import ballblast.view.View;
import ballblast.view.scenecontroller.GameSceneController;
import ballblast.view.scenecontroller.SceneController;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * 
 * A simple implementation of {@link SceneLoader} interface.
 *
 */
public class SceneLoaderImpl implements SceneLoader {

    private final View view;
    private static final String STYLE_CSS_PATH = "/view/style/style.css";
    private static final String LOGO_PATH = "/view/logo/ballblast.png";
    private static final String BACKGROUND_PATH = "/view/background/background.png";
    
    /**
     * 
     * @param view
     *          the entire view
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }
    
    @Override
    public void loadScene(Stage stage, ViewScenes viewScene) {
        final Region root;
        final FXMLLoader loader = new FXMLLoader();
        final SceneController controller;
        final Scene scene;
        
        try {
            loader.setLocation(getClass().getResource(viewScene.getPath()));
            root = loader.load();
            
            controller = loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());
            
            root.setPrefSize(SettingsImpl.getSettings().getSelectedResolution().getKey(),
                             SettingsImpl.getSettings().getSelectedResolution().getValue());
            
            root.getChildrenUnmodifiable().stream().forEach( e -> {
                    // Da implementare
            });
            
            scene = new Scene(root);
            scene.getStylesheets().add(STYLE_CSS_PATH);
            
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(LOGO_PATH));
            
            /**
             * Control if the view is full screen mode.
             *         If true nothing changes.
             *         If false, it center the view.
             */
            if (!SettingsImpl.getSettings().isFullScreen()) {
                stage.sizeToScene();
                stage.centerOnScreen();
            }
            
            /**
             * Control if the view if showing properly.
             *          If not, it forces the showing.
             */
            if (!stage.isShowing()) {
                stage.show();
            }
            
            
            /**
             * Various scenes.
             */
            switch (viewScene) {
            case GAME:
                //
                break;
            case MENU:
                //
                break;
            case PAUSE:
                //
                break;
            case SETTINGS:
                //
                break;
            case LEADERBOARD:
                //
                break;
            case MANUAL:
                //
                break;
            case GAMEOVER:
                //
                break;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    private void addEventHandler(final Stage stage) {
        
    }
    /**
     * Sets the background.
     * @param controller
     *          reference to game scene controller.
     */
    private void drawBackground(final GameSceneController controller) {
        final ImageView backgroundImage = new ImageView(new Image(getClass().getResourceAsStream(BACKGROUND_PATH),
                                                        SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                                        SettingsImpl.getSettings().getSelectedResolution().getValue(),
                                                        false, false));
        controller.getCanvas().getGraphicsContext2D().drawImage(backgroundImage.getImage(), 0, 0, 
                                                                SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                                                SettingsImpl.getSettings().getSelectedResolution().getValue());
        backgroundImage.setPreserveRatio(true);
    }

}
