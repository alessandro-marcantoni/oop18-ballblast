package ballblast.view.states;

import ballblast.controller.Controller;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
/**
 * Class for the idle state of the game.
 * It is active for default at the start of a new game.
 */
public class IdleState extends GUIState {
    
    private boolean keyPressed;
    private final Pane message;
    /**
     * Initialize a new idle state object.
     * @param gui
     *          The {@link GUISceneController}.
     * @param controller
     *          The {@link Controller} of the game.
     * @param message
     *          The {@link Pane} containing the starting message for the player.
     */
    public IdleState(final GUISceneController gui, final Controller controller, final Pane message) {
        super(gui, controller);
        this.keyPressed = false;
        this.message = message;
    }
    @Override
    public void onStateEntry() {
        //this.getController().pauseGame();
        this.message.setVisible(true);
    }

    @Override
    public void onStateExit() {
        //this.getController().resume();
        this.message.setVisible(false);
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        this.keyPressed = true;
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        if (this.keyPressed) {
            this.getGUI().setState(this.getGUI().getInGameState());
        }
    }

}
