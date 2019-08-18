package ballblast.view.states;

import java.util.Map;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableBiMap;

import ballblast.controller.Controller;
import ballblast.model.inputs.InputTypes;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * Class for the in game state.
 * It is the state which comes when the player is interacting with the game.
 */
public class InGameState extends GUIState {
    private static final Map<KeyCode, Consumer<GUIState>> INPUT_MAP;

    static {
        INPUT_MAP = ImmutableBiMap.of(
                KeyCode.LEFT,   g -> g.getController().sendInput(PlayerTags.FIRST, InputTypes.MOVE_LEFT),
                KeyCode.RIGHT,  g -> g.getController().sendInput(PlayerTags.FIRST, InputTypes.MOVE_RIGHT),
                KeyCode.SPACE,  g -> g.getController().sendInput(PlayerTags.FIRST, InputTypes.SHOOT),
                KeyCode.P,      g -> g.getGUI().setState(g.getGUI().getPausedState()),
                KeyCode.ESCAPE, g -> g.getGUI().setState(g.getGUI().getPausedState())
        );
    }
    /**
     * Initialize a new in game state.
     * @param gui
     *          The {@link GUISceneContrller}.
     * @param controller
     *          The {@link Controller} of the game.
     */
    public InGameState(final GUISceneController gui, final Controller controller) {
        super(gui, controller);
    }

    @Override
    public void onStateEntry() {

    }

    @Override
    public void onStateExit() {

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        INPUT_MAP.get(event.getCode()).accept(this);
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        //TODO?
    }

}
