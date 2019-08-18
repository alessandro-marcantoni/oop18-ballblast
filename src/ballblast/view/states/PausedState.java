package ballblast.view.states;

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
        //final Command noInput = e -> {
          // Annulla tutti i movimenti in atto
          // Toglie lo shooting
        //};
        // this.getController().sendCommand(noInput);

        // this.getController().pauseGame();
        this.pausePane.setVisible(true);
    }

    @Override
    public final void onStateExit() {
        // this.getController().resume();
        this.pausePane.setVisible(true);
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            this.getGUI().setState(this.getGUI().getInGameState());
        } else if (event.getCode() == KeyCode.Q) {
            this.getController();
            // this.getController().closeGameSession();
        }

    }

    @Override
    public void onKeyReleased(final KeyEvent event) {

    }

}
