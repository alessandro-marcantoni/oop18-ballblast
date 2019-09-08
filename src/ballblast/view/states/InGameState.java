package ballblast.view.states;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import ballblast.controller.Controller;
import ballblast.model.inputs.InputTypes;
import ballblast.settings.KeyCodeSet;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Class for the in game state. It is the state which comes when the player is
 * interacting with the game.
 */
public class InGameState extends GUIState {
    private final Map<KeyCode, KeyCodeProcessor> pressedInputMap;
    private final Map<KeyCode, KeyCodeProcessor> releasedInputMap;

    /**
     * Initialize a new in game state.
     * 
     * @param gui        The {@link GUISceneContrller}.
     * @param controller The {@link Controller} of the game.
     */
    public InGameState(final GUISceneController gui, final Controller controller) {
        super(gui, controller);
        final KeyCodeSet keySet = KeyCodeSet.valueOf(controller.getCurrentUser().getKeySetting());
        pressedInputMap = ImmutableMap.of(
                keySet.getMoveLeft(),  () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.MOVE_LEFT), 
                keySet.getMoveRight(), () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.MOVE_RIGHT), 
                keySet.getShoot(),     () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.SHOOT), 
                KeyCode.P,             () -> this.translateKeyCode(), 
                KeyCode.ESCAPE,        () -> this.translateKeyCode());
        releasedInputMap = ImmutableMap.of(
                keySet.getMoveLeft(),  () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.STOP_MOVING_LEFT), 
                keySet.getMoveRight(), () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.STOP_MOVING_RIGHT), 
                keySet.getShoot(),     () -> this.translateKeyCode(PlayerTags.FIRST, InputTypes.STOP_SHOOTING));
    }

    @Override
    public void onStateEntry() {
    }

    @Override
    public final void onStateExit() {
        this.releasedInputMap.forEach((k, f) -> f.process());
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (pressedInputMap.containsKey(event.getCode())) {
            pressedInputMap.get(event.getCode()).process();
        }
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        if (releasedInputMap.containsKey(event.getCode())) {
            releasedInputMap.get(event.getCode()).process();
        }
    }

    private void translateKeyCode(final PlayerTags tag, final InputTypes input) {
        this.getController().receiveInput(tag, input);
    }

    private void translateKeyCode() {
        this.getGUI().setState(this.getGUI().getPausedState());
    }

    @FunctionalInterface
    private interface KeyCodeProcessor {
        void process();
    }
}
