package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.settings.FpsSet;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
/**
 * 
 * A simple controller for the settings scene.
 */
public class SettingsSceneController extends AbstractSubSceneController {

    @FXML
    private ToggleButton btnADC;

    @FXML
    private ToggleButton btnLRS;

    @FXML
    private ComboBox<String> cbFPS;

    @FXML
    private CheckBox chkMusic;

    @FXML
    private CheckBox chkSound;

//    private KeyCodeSet commands;
//    private boolean isMusicOn = true;
//    private boolean isEffectsOn = true;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        final ToggleGroup group = new ToggleGroup();
        btnLRS.setToggleGroup(group);
        btnADC.setToggleGroup(group);
//        btnLRS.selectedProperty().addListener(b -> this.setLRSCommands());
//        btnADC.selectedProperty().addListener(b -> this.setADCCommands());
        btnLRS.setSelected(true); // Da sostituire con, verosimilmente...
//        if (checkCommandInUse().equals(KeyCodeSet.SET_ONE)) {
//            btnLRS.setSelected(true);
//        } else {
//            btnADC.setSelected(true);
//        }

        cbFPS = new ComboBox<>();
        cbFPS.getItems().clear();
        cbFPS.getItems().addAll(FpsSet.FPS_25.getFPS(), FpsSet.FPS_30.getFPS(), FpsSet.FPS_60.getFPS(), FpsSet.FPS_120.getFPS());
        cbFPS.getSelectionModel().select(0); // Da sostituire con, verosimilmente...
//        cbFPS.getSelectionModel().select(this.getController().getCurrentUser().getCurrentFPS().toString());

//        chkMusic.selectedProperty().addListener(c -> this.setMusic());
//        chkMusic.selectedProperty().addListener(c -> this.setSoundEffects());
    }


    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

//    private void setLRSCommands() {
//        if (btnLRS.isSelected() && !this.commands.equals(KeyCodeSet.SET_ONE)) {
//            this.getController().getCurrentUser().setCommand(KeyCodeSet.SET_ONE);
//        }
//    }

//    private void setADCCommands() {
//        if (btnADC.isSelected() && !this.commands.equals(KeyCodeSet.SET_TWO)) {
//            this.getController().getCurrentUser().setCommand(KeyCodeSet.SET_TWO);
//        }
//    }

//    private KeyCodeSet checkCommandInUse() {
//        KeyCodeSet command = this.getController().getCurrentUser().getCommand();
//        this.commands = command;
//        return command;
//    }

//    private void setMusic() {
//        if (this.isMusicOn) {
//            this.isMusicOn = false;
//            this.getController().getCurrentUser().setMusic(false);
//        } else {
//            this.isMusicOn = true;
//            this.getController().getCurrentUser().setMusic(true);
//        }
//    }

//    private void setSoundEffects() {
//        if (this.isEffectsOn) {
//        this.isEffectsOn = false;
//        this.getController().getCurrentUser().setSoundEffects(false);
//        } else {
//            this.isEffectsOn = true;
//            this.getController().getCurrentUser().setSoundEffects(true);
//        }
//    }
}
