package ballblast.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;
import ballblast.controller.files.LeaderboardManager;
import ballblast.controller.files.UserManager;
import ballblast.controller.sound.Sound;
import ballblast.model.Model;
import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.data.Leaderboard;
import ballblast.model.data.UserData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.view.View;

/**
 * The implementation of the {@link Controller} in the MVC architecture.
 */
public class ControllerImpl implements Controller, GameLoopObserver {

    private final Model model;
    private final View view;
    private GameLoop gameloop;
    private Optional<UserData> currentUser;
    private final UserManager userManager;
    private final Leaderboard leaderboard;
    private final LeaderboardManager lbManager;

    /**
     * Create a new instance of {@link Controller}.
     * 
     * @param model The model of the MVC architecture.
     * @param view  The view of the MVC architecture.
     */
    public ControllerImpl(final Model model, final View view) {
        DirectoryManager.setupApplication();
        this.model = model;
        this.view = view;
        this.userManager = new UserManager();
        this.currentUser = Optional.empty();
        this.lbManager = new LeaderboardManager();
        this.leaderboard = this.lbManager.loadSurvivalLeaderboard().get();
        Sound.loadSounds();
    }

    @Override
    public final void startSurvivalMode() {
        this.createGameLoop();
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
    public final void notifyGameOver() {
        this.gameloop.stopLoop();
    }

    @Override
    public final void receiveInput(final PlayerTags tag, final InputTypes input) {
        this.gameloop.receiveInput(tag, input);
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
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.currentUser = this.userManager.login(username, password);
        return this.currentUser.isPresent();
    }

    @Override
    public final boolean checkRegisterUser(final String username, final String password)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        this.currentUser = this.userManager.register(username, password);
        return this.currentUser.isPresent();
    }

    @Override
    public final UserData getCurrentUser() {
        return this.currentUser.get();
    }

    @Override
    public final Leaderboard getLeaderboard() {
        return this.lbManager.loadSurvivalLeaderboard().get();
    }

    @Override
    public final void updateLeaderboard() {
        this.leaderboard.addRecord(currentUser.get().getName(), this.model.getGameData().getScore());
        this.lbManager.saveSurvivalLeaderboard(leaderboard);
        this.currentUser.get().addGameData(this.model.getGameData());
        this.userManager.updateUserData(this.currentUser.get());
    }

    private void createGameLoop() {
        this.gameloop = new GameLoopImpl(this.model, view);
        this.gameloop.addObserver(this);
    }
}
