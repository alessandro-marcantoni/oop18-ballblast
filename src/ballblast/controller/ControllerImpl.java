package ballblast.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ballblast.controller.files.UserManager;
import ballblast.model.Model;
import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.view.View;

/**
 * The implementation of the Controller in the MVC architecture.
 */
public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private final GameLoop gameloop;
    private final UserManager userManager;
    private String currentUser;

    /**
     * Create a new instance of Controller.
     * 
     * @param model The model of the MVC architecture.
     * @param view  The view of the MVC architecture.
     */
    public ControllerImpl(final Model model, final View view) {
        DirectoryManager.setupApplication();
        this.model = model;
        this.view = view;
        this.userManager = new UserManager();
        this.currentUser = null;
        this.gameloop = new GameLoopImpl(this.model, view);
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
    public final void gameOver() {
        this.view.setGameOver(true);
        this.gameloop.stopLoop();
    }

    @Override
    public final void sendInput(final PlayerTags tag, final InputTypes input) {
        this.gameloop.receiveInputs(tag, input);
    }

    @Override
    public final List<GameObject> getGameObjects() {
        return this.model.getGameObjects();
    }

    @Override
    public final GameData getGameData() {
        return this.model.getGameData();
    }

    @Override
    public final boolean checkLoginUser(final String username, final String password)
            throws ParserConfigurationException, SAXException, IOException {
        if (this.userManager.login(username, password).isPresent()) {
            this.currentUser = username;
            return true;
        }
        return false;
    }

    @Override
    public final boolean checkRegisterUser(final String username, final String password)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        return this.userManager.register(username, password).isPresent();
    }

    @Override
    public final String getCurrentUser() {
        return this.currentUser;
    }


}
