package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.settings.Framerates;
import ballblast.settings.KeyCodeSet;
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
    private ComboBox<Integer> cbFPS;

    @FXML
    private CheckBox chkMusic;

    @FXML
    private CheckBox chkSound;

    private KeyCodeSet keyset;
    private boolean isMusicOn = true;
    private boolean isEffectsOn = true;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        final ToggleGroup group = new ToggleGroup();
        this.btnLRS.setToggleGroup(group);
        this.btnADC.setToggleGroup(group);
        btnLRS.selectedProperty().addListener(b -> this.setKeySetOne());
        btnADC.selectedProperty().addListener(b -> this.setKeySetTwo());
        this.btnLRS.setSelected(true); // Da sostituire con, verosimilmente...
//        if (checkCommandInUse().equals(KeyCodeSet.SET_ONE)) {
//            btnLRS.setSelected(true);
//        } else {
//            btnADC.setSelected(true);
//        }
        this.cbFPS.getItems().clear();
        this.cbFPS.getItems().addAll(Framerates.FPS_30.getFPS(),
                                Framerates.FPS_60.getFPS(), Framerates.FPS_120.getFPS());
        this.cbFPS.getSelectionModel().select(0); // Da sostituire con, verosimilmente...
//        cbFPS.getSelectionModel().select(this.getController().getCurrentUser().getCurrentFPS().toString());
        cbFPS.getSelectionModel().selectedItemProperty().addListener(c -> this.setFPS());

        chkMusic.selectedProperty().addListener(c -> this.setMusic());
        chkSound.selectedProperty().addListener(c -> this.setSoundEffects());
    }


    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    private void setKeySetOne() {
//        if (btnLRS.isSelected() && !this.keyset.equals(KeyCodeSet.SET_ONE)) {
//            this.getController().getCurrentUser().setKeySet(KeyCodeSet.SET_ONE);
            System.out.println("Sono in setKeySetOne");
//        }
    }

    private void setKeySetTwo() {
//        if (btnADC.isSelected() && !this.keyset.equals(KeyCodeSet.SET_TWO)) {
//            this.getController().getCurrentUser().setKeySet(KeyCodeSet.SET_TWO);
            System.out.println("Sono in setKeySetTwo");
//        }
    }

    private KeyCodeSet checkKeySetInUse() {
//        KeyCodeSet currentKeySet = this.getController().getCurrentUser().getKeySet();
//        this.keyset = currentKeySet;
//        return currentKeySet;
        System.out.println("Sono in checkKeySetInUse");
        return null;
    }

    private void setMusic() {
        if (this.isMusicOn) {
            this.isMusicOn = false;
//            this.getController().getCurrentUser().setMusic(false);
            System.out.println("Sono in setMusic -- false");
        } else {
            this.isMusicOn = true;
//            this.getController().getCurrentUser().setMusic(true);
            System.out.println("Sono in setMusic -- true");
        }
    }
    
    private void setFPS() {
        final Integer selectedFPS = this.cbFPS.getSelectionModel().getSelectedItem();
//        this.getController().getCurrentUser().setFramerate(selectedFPS);
        System.out.println("Sono in setFPS");
    }

    private void setSoundEffects() {
        if (this.isEffectsOn) {
        this.isEffectsOn = false;
//        this.getController().getCurrentUser().setSoundEffects(false);
        System.out.println("Sono in setSoundEffects -- false");
        } else {
            this.isEffectsOn = true;
//            this.getController().getCurrentUser().setSoundEffects(true);
            System.out.println("Sono in setSoundEffects -- true");
        }
    }
}
