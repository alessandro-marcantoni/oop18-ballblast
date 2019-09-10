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
    private final Map<KeyCode, KeyCodeTranslator> pressedInputMap;
    private final Map<KeyCode, KeyCodeTranslator> releasedInputMap;

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
                KeyCode.P,             () -> this.translatePauseKeyCode(), 
                KeyCode.ESCAPE,        () -> this.translatePauseKeyCode());
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
        this.releasedInputMap.forEach((k, f) -> f.translate());
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (this.pressedInputMap.containsKey(event.getCode())) {
            this.pressedInputMap.get(event.getCode()).translate();
        }
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        if (this.releasedInputMap.containsKey(event.getCode())) {
            this.releasedInputMap.get(event.getCode()).translate();
        }
    }

    private void translateKeyCode(final PlayerTags tag, final InputTypes input) {
        this.getController().receiveInput(tag, input);
    }

    private void translatePauseKeyCode() {
        this.getGUI().setState(this.getGUI().getPausedState());
    }

    //Functional interface used to translate a keyCode to its corresponding action.
    private interface KeyCodeTranslator {
        void translate();
    }
}
