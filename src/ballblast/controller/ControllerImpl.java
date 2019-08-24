package ballblast.controller;

import java.util.List;

import ballblast.model.Model;
import ballblast.model.ModelImpl;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.view.View;
import ballblast.view.ViewImpl;

/**
 * The imlementation of the Controller in the MVC architecture.
 */
public class ControllerImpl implements Controller {

    private Model model;
    private View view;
    private GameLoop gameloop;

    /**
     * Create a new instance of Controller.
     */
    public ControllerImpl() {
        this.model = new ModelImpl();
        this.view = new ViewImpl(null);
        this.gameloop = new GameLoopImpl(this.view, this.model);
    }

    @Override
    public final void startSurvivalMode() {
        this.model.startSurvival();
        this.gameloop.start();
    }

    @Override
    public final void pauseGame() {
        this.gameloop.pause();
    }

    @Override
    public final void resume() {
        this.gameloop.resumeLoop();
    }

    @Override
    public void sendInput(final PlayerTags tag, final InputTypes input) {

    }

    @Override
    public final List<GameObject> getGameObjects() {
        return this.model.getCurrentLevel().isPresent() ? this.model.getCurrentLevel().get().getGameObjectManager().getGameObjects() : null;
    }

    @Override
    public final int getTimeScore() {
        return this.model.getCurrentLevel().isPresent() ? this.model.getCurrentLevel().get().getGameScore() : 0;
    }

}
