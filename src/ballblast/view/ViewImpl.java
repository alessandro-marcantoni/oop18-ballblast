package ballblast.view;

import java.util.List;
import java.util.Optional;

import ballblast.view.entities.ViewEntity;
import ballblast.view.render.Render;
import ballblast.view.scenecontroller.GameSceneController;
import ballblast.view.scenefactory.SceneFactory;

public class ViewImpl implements View {

    private Controller controller;
    private Render viewRender;
    private List<Optional<ViewEntity>> viewEntities;
    @Override
    public SceneFactory getSceneFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startGame(GameSceneController gameSceneController) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(List<Optional<ViewEntity>> viewEntities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void startRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopRender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void viewLauncher(Controller controller) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

}
