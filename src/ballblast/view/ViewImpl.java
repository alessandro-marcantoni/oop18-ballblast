package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.scenecontroller.AbstractSceneController;
import ballblast.view.sceneloader.SceneLoader;
import ballblast.view.sceneloader.SceneWrapper;
import ballblast.view.utilities.ViewScenes;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * 
 * 
 */
public class ViewImpl implements View {

    private static final String GAME_TITLE = "BALL BLAST";
    private static final double MIN_WIDTH = 384;
    private static final double MIN_HEIGHT = 200;
    private Controller control;
    private AbstractSceneController currentScene;
    private boolean viewStarted;
    private final Stage stage;

//    private static final int GREEN_SEMAPHORE = 1;


//    private Render viewRender;
//    private List<Optional<ViewEntity>> viewEntities;
//    private GameData gameData;
//    private final Semaphore mutex;

//    private final SceneFactory sceneFactory;
//    private GameMode gameMode;
//    private Level level;
//    private int score = 0;
    /**
     * 
     * @param primaryStage
     *          primaryStage
     */
    public ViewImpl(final Stage primaryStage) {
        super();
        this.stage = primaryStage;
        this.viewStarted = false;
//        this.mutex = new Semaphore(GREEN_SEMAPHORE);
//        this.sceneFactory = new SceneFactoryImpl(this);
//        this.level = new BasicLevel();
    }

//    @Override
//    public void startGame(GameSceneController gameSceneController) {
////        this.gameMode = gameMode;
//        this.viewRender = new Render(gameSceneController);
//    }

    @Override
    public final void render() {
        Platform.runLater(() -> this.currentScene.render());

//        try {
//            this.mutex.acquire();
//            this.viewEntities = viewEntities;
//            this.level = level;
//            this.score = this.level.getGameScore();
//            this.mutex.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void startRender() {
//       this.viewRender.start();
//    }
//
//    @Override
//    public void stopRender() {
//        this.viewRender.stopRender();
//    }


    @Override
    public final void launch(final Controller controller) {
       this.control = controller;
       this.stage.setTitle(GAME_TITLE);
       this.stage.setMinHeight(MIN_HEIGHT);
       this.stage.setMinWidth(MIN_WIDTH);
       this.stage.setMaximized(true);
       this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
       this.loadScene(ViewScenes.MENU);
//       this.sceneFactory.setStage(this.stage);
//       this.sceneFactory.openMenuScene();
    }
    /**
     * @param scene
     *          scene
     */
    public void loadScene(final ViewScenes scene) {
        try {
            final SceneWrapper wrapper = SceneLoader.getLoader().getScene(scene);
            //swrapper.getController().init(control, this);
            this.currentScene = wrapper.getController();

            final Parent root = wrapper.getScene().getRoot();
            root.requestFocus();
            root.setOnKeyPressed(wrapper.getController()::onKeyPressed);

            Platform.runLater(() -> {
                final double oldWidth = this.stage.getWidth();
                final double oldHeigth = this.stage.getHeight();
                this.stage.setScene(wrapper.getScene());
                this.stage.setWidth(oldWidth);
                this.stage.setHeight(oldHeigth);
                if (!this.viewStarted) {
                    this.stage.show();
                    this.viewStarted = true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Controller getController() {
//        return this.controller;
//    }
//
//
//    @Override
//    public SceneFactory getSceneFactory() {
//        return this.sceneFactory;
//    }

//    /**
//     * 
//     * 
//     * 
//     */
//
//    private class Render extends Thread{
//
//        private static final int FPS = 60;
//        private static final int MILLISECONDS = 1000/FPS;
//
//        private boolean running, end;
//        private final int period;
//        private List<Optional<ViewEntity>> viewEntitiesGame;
//
//        private Level level;
//        private int score = 0;
//
//        private final GameSceneController gameSceneController;
//        private GraphicsContext canvas;
//        private final ImageView backgroundImage;
//
//
//        Render(final GameSceneController gameSceneController) {
//            super();
//            this.period = MILLISECONDS;
//            this.gameSceneController = gameSceneController;
//            this.canvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
//            this.running = true;
////            this.gameMode = SURVIVAL;
//
//            this.backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/view/background/background.png"),
//                                                           SettingsImpl.getSettings().getSelectedResolution().getKey(),
//                                                           SettingsImpl.getSettings().getSelectedResolution().getValue(),
//                                                           false, false));
//
//       }
//
//
//        public void run() {
////            controller.initModel();
////            controller.initGameLoop();
////            controller.startGameLoop();
//
//            while (this.running) {
//                try {
//                    mutex.acquire();
//
//                    this.viewEntitiesGame = viewEntities;
//                    this.score = level.getGameScore();
//
//                    mutex.release();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                final long currentTime = System.currentTimeMillis();
//
//                Platform.runLater(() -> {
////                    this.gameSceneController.setPausePanelVisibility(getController().isGameLoopPaused());
//
//                    this.gameSceneController.setGameData(this.level);
//
//                    for (final Optional<ViewEntity> viewEntity : this.viewEntitiesGame) {
//                        if (viewEntity.isPresent() && viewEntity.get().getShape() instanceof Circle) {
//                            final ViewEntity ve = viewEntity.get();
//                            final Shape circle = ve.getShape();
//
//                        }
//                    }
//                });
//            }
//
//        }
//        public void endGame() {
//            this.end = true;
//        }
//        public void stopRender() {
//            this.running = false;
//        }
//        public void start() {
//            this.running = true;
//            super.start();
//        }
//
//
//    }

}
