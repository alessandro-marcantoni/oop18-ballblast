package ballblast.controller;

import java.util.List;

import ballblast.model.Model;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.view.View;

/**
 * The imlementation of the Controller in the MVC architecture.
 */
public class ControllerImpl implements Controller {

    private Model model;
    private View view;
    private GameLoop gameloop;

    /**
     * Create a new instance of Controller.
     * @param model
     *      The model of the MVC architecture.
     * @param view
     *      The view of the MVC architecture.
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
        this.gameloop = new GameLoopImpl(this.model, this.view);
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
