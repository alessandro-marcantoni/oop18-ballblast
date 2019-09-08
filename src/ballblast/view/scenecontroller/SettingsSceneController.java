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

    private static final String KEYSET_ONE = "KEYSET_ONE";
//    private static final String KEYSET_TWO = "KEYSET_TWO";

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
//    private boolean isMusicOn = true;
//    private boolean isEffectsOn = true;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);

        // Buttons for key set settings.
        final ToggleGroup group = new ToggleGroup();
        this.btnLRS.setToggleGroup(group);
        this.btnADC.setToggleGroup(group);
        btnLRS.selectedProperty().addListener(b -> this.setKeySetOne());
        btnADC.selectedProperty().addListener(b -> this.setKeySetTwo());
        if (this.checkKeySetInUse().equals(KeyCodeSet.SET_ONE)) {
            btnLRS.setSelected(true);
        } else if (this.checkKeySetInUse().equals(KeyCodeSet.SET_TWO)){
            btnADC.setSelected(true);
        }

        // Combo box for FPS settings.
        this.cbFPS.getItems().clear();
        this.cbFPS.getItems().addAll(Framerates.FPS_30.getFPS(), Framerates.FPS_60.getFPS(),
                Framerates.FPS_120.getFPS());
        if (this.checkFramerateInUse().equals(Framerates.FPS_30)) {
            this.cbFPS.getSelectionModel().select(0);
        } else if (this.checkFramerateInUse().equals(Framerates.FPS_60)) {
            this.cbFPS.getSelectionModel().select(1);
        } else {
            this.cbFPS.getSelectionModel().select(2);
        }
        cbFPS.getSelectionModel().selectedItemProperty().addListener(c -> this.setFramerate());

        // Check box for music settings.
//        chkMusic.selectedProperty().addListener(c -> this.setMusic());
//        chkSound.selectedProperty().addListener(c -> this.setSoundEffects());
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
        if (btnLRS.isSelected() && !this.keyset.equals(KeyCodeSet.SET_ONE)) {
//            this.getController().getCurrentUser().setKeySetting(KeyCodeSet.SET_ONE.toString());
            System.out.println("Sono in setKeySetOne");
        }
    }

    private void setKeySetTwo() {
//        if (btnADC.isSelected() && !this.keyset.equals(KeyCodeSet.SET_TWO)) {
            this.getController().getCurrentUser().setKeySetting(KeyCodeSet.SET_TWO.toString());
            System.out.println("Sono in setKeySetTwo");
//        }
    }

    private KeyCodeSet checkKeySetInUse() {
        KeyCodeSet currentKeySet;
        if (this.getController().getCurrentUser().getKeySetting().equals(KEYSET_ONE)) {
//            this.keyset = KeyCodeSet.SET_ONE;
            currentKeySet = KeyCodeSet.SET_ONE;
        } else {
//            this.keyset = KeyCodeSet.SET_TWO;
            currentKeySet = KeyCodeSet.SET_TWO;
        }
        return currentKeySet;
    }

    private Framerates checkFramerateInUse() {
        Framerates currentFramerate;
        if (this.getController().getCurrentUser().getFramesPerSecond() == Framerates.FPS_30.getFPS()) {
            currentFramerate = Framerates.FPS_30;
        } else if (this.getController().getCurrentUser().getFramesPerSecond() == Framerates.FPS_60.getFPS()) {
            currentFramerate = Framerates.FPS_60;
        } else {
            currentFramerate = Framerates.FPS_120;
        }
        return currentFramerate;
    }

    private void setFramerate() {
        this.getController().getCurrentUser().setFramesPerSecond(this.cbFPS.getSelectionModel().getSelectedItem().intValue());
    }

//    private void setMusic() {
//        if (this.isMusicOn) {
//            this.isMusicOn = false;
//            this.getController().getCurrentUser().setMusic(false);
//            System.out.println("Sono in setMusic -- false");
//        } else {
//            this.isMusicOn = true;
//            this.getController().getCurrentUser().setMusic(true);
//            System.out.println("Sono in setMusic -- true");
//        }
//    }
//
//    private void setFPS() {
//        final Integer selectedFPS = this.cbFPS.getSelectionModel().getSelectedItem();
//        this.getController().getCurrentUser().setFramesPerSecond(selectedFPS);
//        System.out.println("Sono in setFPS");
//    }
//
//    private void setSoundEffects() {
//        if (this.isEffectsOn) {
//            this.isEffectsOn = false;
//        this.getController().getCurrentUser().setSoundEffects(false);
//            System.out.println("Sono in setSoundEffects -- false");
//        } else {
//            this.isEffectsOn = true;
//            this.getController().getCurrentUser().setSoundEffects(true);
//            System.out.println("Sono in setSoundEffects -- true");
//        }
//    }
}
