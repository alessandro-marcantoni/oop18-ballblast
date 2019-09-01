package ballblast.view.states;

import java.util.Map;
import java.util.function.Consumer;
import com.google.common.collect.ImmutableMap;
import ballblast.controller.Controller;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Class for the paused state.
 */
public class PausedState extends GUIState {

    private final Pane pausePane;
    private static final Map<KeyCode, Consumer<GUIState>> PRESSED_INPUT_MAP;

    static {
        PRESSED_INPUT_MAP = ImmutableMap.of(
                KeyCode.P, g -> g.getGUI().setState(g.getGUI().getInGameState()),
                KeyCode.ESCAPE, g -> g.getGUI().setState(g.getGUI().getInGameState()), 
                KeyCode.Q, g -> {
                    g.getGUI().getController().gameOver();
                    g.getGUI().backScene();
                });
    }

    /**
     * Initialize the paused state.
     * 
     * @param gui        the {@link GUISceneController}.
     * @param controller the {@link Controller} of the game.
     * @param pausePane  the {@link Pane} to be showed during the pause.
     */
    public PausedState(final GUISceneController gui, final Controller controller, final Pane pausePane) {
        super(gui, controller);
        this.pausePane = pausePane;
    }

    @Override
    public final void onStateEntry() {
        this.getController().pauseGame();
        this.pausePane.setVisible(true);
    }

    @Override
    public final void onStateExit() {
        this.getController().resume();
        this.pausePane.setVisible(false);
        this.getGUI().render();
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (PRESSED_INPUT_MAP.containsKey(event.getCode())) {
            PRESSED_INPUT_MAP.get(event.getCode()).accept(this);
        }
    }

    @Override
    public void onKeyReleased(final KeyEvent event) {

    }

}
