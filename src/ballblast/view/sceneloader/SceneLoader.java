package ballblast.view.sceneloader;

import java.io.IOException;
import ballblast.view.utilities.ViewScenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * A simple implementation of {@link SceneLoader} interface.
 *
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();

    /**
     * 
     * @return
     *          pippo
     */
    public static SceneLoader getLoader() {
        return SINGLETON;
    }

    /**
     * 
     * @param scene
     *          scene
     * @return
     *          pippo
     * @throws IOException
     *          help
     */
    public SceneWrapper getScene(final ViewScenes scene) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final String path = scene.getPath();
        final Parent parent = (Parent) loader.load(this.getClass().getResourceAsStream(path));
        return new SceneWrapperImpl(new Scene(parent), loader.getController());
    }

//    private final View view;
//    private static final String STYLE_CSS_PATH = "/view/style/style.css";
//    private static final String LOGO_PATH = "/view/logo/ballblast.png";
//    private static final String BACKGROUND_PATH = "/view/background/background.png";

//    @Override
//    public void loadScene(Stage stage, ViewScenes viewScene) {
//        final Region root;
//        final FXMLLoader loader = new FXMLLoader();
//        final SceneController controller;
//        final Scene scene;
//
//        try {
//            loader.setLocation(getClass().getResource(viewScene.getPath()));
//            root = loader.load();
//
//            controller = loader.getController();
//            controller.setSceneFactory(this.view.getSceneFactory());
//
//            root.setPrefSize(SettingsImpl.getSettings().getSelectedResolution().getKey(),
//                             SettingsImpl.getSettings().getSelectedResolution().getValue());
//
//            root.getChildrenUnmodifiable().stream().forEach( e -> {
//                    // Da implementare
//            });
//
//            scene = new Scene(root);
//            scene.getStylesheets().add(STYLE_CSS_PATH);
//
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.getIcons().add(new Image(LOGO_PATH));
//
//            /**
//             * Control if the view is full screen mode.
//             *         If true nothing changes.
//             *         If false, it center the view.
//             */
//            if (!SettingsImpl.getSettings().isFullScreen()) {
//                stage.sizeToScene();
//                stage.centerOnScreen();
//            }
//
//            /**
//             * Control if the view if showing properly.
//             *          If not, it forces the showing.
//             */
//            if (!stage.isShowing()) {
//                stage.show();
//            }
//
//
//            /**
//             * Various scenes.
//             */
//            switch (viewScene) {
//            case GAME:
//                this.addEventHandler(stage);
//                this.drawBackground((GameSceneController) controller);
//                this.view.startGame((GameSceneController) controller);
//                break;
//            case MENU:
//                //
//                break;
//            case PAUSE:
//                //
//                break;
//            case SETTINGS:
//                //
//                break;
//            case LEADERBOARD:
//                //
//                break;
//            case MANUAL:
//                //
//                break;
//            case GAMEOVER:
//                //
//                break;
//            case GAME_MODE:
//                break;
//            default:
//                break;
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    /**
//     * Add and event handler to the stage passed.
//     * 
//     * @param stage
//     *          the view stage.
//     */
//    private void addEventHandler(final Stage stage) {
//        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, e -> {
//           /*
//            * Da implementare
//            */
//        });
//    }
//    /**
//     * Sets the background.
//     * @param controller
//     *          reference to game scene controller.
//     */
//    private void drawBackground(final GameSceneController controller) {
//        final ImageView backgroundImage = new ImageView(new Image(getClass().getResourceAsStream(BACKGROUND_PATH),
//                                                        SettingsImpl.getSettings().getSelectedResolution().getKey(),
//                                                        SettingsImpl.getSettings().getSelectedResolution().getValue(),
//                                                        false, false));
//        controller.getCanvas().getGraphicsContext2D().drawImage(backgroundImage.getImage(), 0, 0, 
//                                                                SettingsImpl.getSettings().getSelectedResolution().getKey(),
//                                                                SettingsImpl.getSettings().getSelectedResolution().getValue());
//        backgroundImage.setPreserveRatio(true);
//    }

}
