package ballblast.view.states;

import java.util.Map;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableMap;

import ballblast.controller.Controller;
import ballblast.model.inputs.InputTypes;
import ballblast.model.inputs.InputManager.PlayerTags;
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
    private static final Map<KeyCode, Consumer<GUIState>> RELEASED_INPUT_MAP;

    static {
        PRESSED_INPUT_MAP = ImmutableMap.of(
                KeyCode.P,      g -> g.getGUI().setState(g.getGUI().getInGameState()),
                KeyCode.ESCAPE, g -> g.getGUI().setState(g.getGUI().getInGameState())
        );
        RELEASED_INPUT_MAP = ImmutableMap.of(
        );
    }
    /**
     * Initialize the paused state.
     * @param gui
     *          the {@link GUISceneController}.
     * @param controller
     *          the {@link Controller} of the game.
     * @param pausePane
     *          the {@link Pane} to be showed during the pause.
     */
    public PausedState(final GUISceneController gui, final Controller controller, final Pane pausePane) {
        super(gui, controller);
        this.pausePane = pausePane;
    }

    @Override
    public final void onStateEntry() {
        //
          // Annulla tutti i movimenti in atto
          // Toglie lo shooting
        //
        // this.getController().sendCommand(noInput);

        this.getController().pauseGame();
        this.pausePane.setVisible(true);
    }

    @Override
    public final void onStateExit() {
        this.getController().resume();
        this.pausePane.setVisible(true);
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
