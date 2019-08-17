package ballblast.view.states;

import ballblast.controller.Controller;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * Class for the in game state.
 * It is the state which comes when the player is interacting with the game.
 */
public class InGameState extends GUIState {

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
        if (event.getCode() == KeyCode.LEFT) {
            // Moving left
            // this.getController().sendCommand(...)
        } else if (event.getCode() == KeyCode.RIGHT) {
            // Moving right
            // this.getController().sendCommand(...)
        } else if (event.getCode() == KeyCode.P) {
            // Sets the pause state
            this.getGUI().setState(this.getGUI().getPausedState());
        } else if (event.getCode() == KeyCode.ESCAPE) {
            // Sets the pause state
            this.getGUI().setState(this.getGUI().getPausedState());
        } 
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            // Moving left
            // this.getController().sendCommand(...)
        } else if (event.getCode() == KeyCode.RIGHT){
            // Moving right
            // this.getController().sendCommand(...)
        }
    }

}
