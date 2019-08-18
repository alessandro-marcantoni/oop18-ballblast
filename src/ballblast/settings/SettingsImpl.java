package ballblast.settings;

import java.util.Objects;

import javafx.stage.Screen;
import javafx.util.Pair;
/**
 * 
 * A simple implementation of {@link Settings} interface.
 * 
 */
public class SettingsImpl implements Settings {

    private static final int PREF_X_RES = 1920;
    private static final int PREF_Y_RES = 1080;
    private static final int DEFAULT_X_RES = (int) Screen.getPrimary().getBounds().getWidth();
    private static final int DEFAULT_Y_RES = (int) Screen.getPrimary().getBounds().getHeight();
    private static final Pair<Integer, Integer> DEFAULT_RES = new Pair<>(DEFAULT_X_RES, DEFAULT_Y_RES);

    private static final Pair<Integer, Integer> SCREEN_RES = DEFAULT_RES;

    private final Pair<Integer, Integer> selectedResolution = new Pair<>(SettingsImpl.SCREEN_RES.getKey(), 
                                                                         SettingsImpl.SCREEN_RES.getValue());

    private boolean fullScreen = true;

    private static SettingsImpl singleton;

    /**
     * 
     * @return
     *          Instance of this object, only once.
     */
    public static SettingsImpl getSettings() {
        if (Objects.isNull(singleton)) {
            singleton = new SettingsImpl();
        }
        return singleton;
    }

    @Override
    public final Pair<Integer, Integer> getDefaultResolution() {
        return DEFAULT_RES;
    }

    @Override
    public final Pair<Integer, Integer> getSelectedResolution() {
        return this.selectedResolution;
    }

    @Override
    public final double getScaleFactor() {
        return Math.min((double) this.selectedResolution.getValue() / SettingsImpl.PREF_Y_RES,
                        (double) this.selectedResolution.getKey() / SettingsImpl.PREF_X_RES);
    }

    @Override
    public final boolean isFullScreen() {
        return this.fullScreen;
    }

    @Override
    public final void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

}
