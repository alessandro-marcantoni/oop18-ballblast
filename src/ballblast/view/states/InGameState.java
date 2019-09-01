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

/**
 * Class for the in game state. It is the state which comes when the player is
 * interacting with the game.
 */
public class InGameState extends GUIState {
    private static final Map<KeyCode, Consumer<GUIState>> PRESSED_INPUT_MAP;
    private static final Map<KeyCode, Consumer<GUIState>> RELEASED_INPUT_MAP;

    static {
        PRESSED_INPUT_MAP = ImmutableMap.of(KeyCode.LEFT,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.MOVE_LEFT), KeyCode.RIGHT,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.MOVE_RIGHT), KeyCode.SPACE,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.SHOOT), KeyCode.P,
                g -> g.getGUI().setState(g.getGUI().getPausedState()), KeyCode.ESCAPE,
                g -> g.getGUI().setState(g.getGUI().getPausedState()));
        RELEASED_INPUT_MAP = ImmutableMap.of(KeyCode.LEFT,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.STOP_MOVING), KeyCode.RIGHT,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.STOP_MOVING), KeyCode.SPACE,
                g -> g.getController().receiveInput(PlayerTags.FIRST, InputTypes.STOP_SHOOTING));
    }

    /**
     * Initialize a new in game state.
     * 
     * @param gui        The {@link GUISceneContrller}.
     * @param controller The {@link Controller} of the game.
     */
    public InGameState(final GUISceneController gui, final Controller controller) {
        super(gui, controller);
    }

    @Override
    public void onStateEntry() {

    }

    @Override
    public final void onStateExit() {
        this.getController().receiveInput(PlayerTags.FIRST, InputTypes.STOP_MOVING);
        this.getController().receiveInput(PlayerTags.FIRST, InputTypes.STOP_SHOOTING);
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (PRESSED_INPUT_MAP.containsKey(event.getCode())) {
            PRESSED_INPUT_MAP.get(event.getCode()).accept(this);
        }
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        if (RELEASED_INPUT_MAP.containsKey(event.getCode())) {
            RELEASED_INPUT_MAP.get(event.getCode()).accept(this);
        }
    }
}
