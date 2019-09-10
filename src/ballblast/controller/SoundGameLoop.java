package ballblast.controller;

import ballblast.controller.sound.Sound;
import ballblast.model.Model;
import ballblast.view.View;

/**
 * A {@link GameLoop} with sound effects.
 */
public class SoundGameLoop extends GameLoopImpl {

    /**
     * Creates a new instance of {@link GameLoop} with sound effects.
     * 
     * @param model     The {@link Model}.
     * @param view      The {@link View}.
     * @param frameRate The frame rate.
     */
    public SoundGameLoop(final Model model, final View view, final int frameRate) {
        super(model, view, frameRate);
    }

    @Override
    protected final void processSounds(final Model model) {
        Sound.HANDLER.handleAll(model.getGameEvents());
    }

    @Override
    protected final void gameOverSound() {
        super.gameOverSound();
        Sound.GAMEOVER.playSound();
    }

}
