package ballblast.view;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.Level;
import ballblast.settings.SettingsImpl;
import ballblast.view.entities.ViewEntity;
import ballblast.view.scenecontroller.GameSceneController;
import ballblast.view.scenefactory.SceneFactory;
import ballblast.view.scenefactory.SceneFactoryImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ViewImpl implements View {

    private static final String GAME_TITLE = "BALL BLAST";
    private static final int GREEN_SEMAPHORE = 1;
    
//    private Controller controller;
    private Render viewRender;
    private List<Optional<ViewEntity>> viewEntities;
//    private GameData gameData;
    private final Semaphore mutex;
    
    private final SceneFactory sceneFactory;
    private final Stage stage;
//    private GameMode gameMode;
    private Level level;
    private int score = 0;
    
    public ViewImpl(final Stage primaryStage) {
        super();
        this.stage = primaryStage;
        this.mutex = new Semaphore(GREEN_SEMAPHORE);
        this.sceneFactory = new SceneFactoryImpl(this);
        this.level = new BasicLevel();
    }
    
    @Override
    public void startGame(GameSceneController gameSceneController) {
//        this.gameMode = gameMode;
//        this.viewRender = new Render(gameSceneController, controller, mutex);
    }

    @Override
    public void render(final List<Optional<ViewEntity>> viewEntities, final Level level) {
        
        try {
            this.mutex.acquire();
            this.viewEntities = viewEntities;
            this.level = level;
            this.score = this.level.getGameScore();
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startRender() {
       this.viewRender.start();
    }

    @Override
    public void stopRender() {
        this.viewRender.stopRender();
    }

    
//    @Override
//    public void viewLauncher(final Controller controller) {
//       this.controller = controller;
//       this.stage.setTitle(GAME_TITLE);
//       this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
//       this.sceneFactory.setStage(this.stage);
//       this.sceneFactory.openMenuScene();
//    }
//    @Override
//    public Controller getController() {
//        return this.controller;
//    }
    
    
    @Override
    public SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }
    
    private class Render extends Thread{
        
        private static final int FPS = 60;
        private static final int MILLISECONDS = 1000/FPS;
        
        private boolean running, end;
        private final int period;
        private List<Optional<ViewEntity>> viewEntitiesGame;
        
        private Level level;
        private int score = 0;
        
        private final GameSceneController gameSceneController;
        private GraphicsContext canvas;
        private final ImageView backgroundImage;
        
//        private final Controller controller;
        
//        private final GameMode gameMode;
        
        
        
        Render(final GameSceneController gameSceneController) {
            super();
            this.period = MILLISECONDS;
            this.gameSceneController = gameSceneController;
            this.canvas = this.gameSceneController.getCanvas().getGraphicsContext2D();
            this.running = true;
//            this.gameMode = SURVIVAL;
            
            this.backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/view/background/background.png"),
                                                           SettingsImpl.getSettings().getSelectedResolution().getKey(),
                                                           SettingsImpl.getSettings().getSelectedResolution().getValue(),
                                                           false, false));
            
        }
        
        
        public void run() {
//            controller.initModel(gameMode);
//            controller.initGameLoop();
//            controller.startGameLoop();
            
            while (this.running) {
                try {
                    mutex.acquire();
                    
                    this.viewEntitiesGame = viewEntities;
                    this.score = level.getGameScore();
                    
                    mutex.release();
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        public void endGame() {
            this.end = true;
        }
        public void stopRender() {
            this.running = false;
        }
        public void start() {
            this.running = true;
            super.start();
        }
        
        
    }



}
