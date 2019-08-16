package ballblast.controller;

import ballblast.model.Model;
import ballblast.view.View;

/**
 * Represents a game loop that starts in a new thread.
 */
public class GameLoopImpl extends Thread implements GameLoop {
    private static final double MS_TO_S = 0.001;

    private boolean stopped;
    private final View view;
    private final Model model;

    /**
     * Creates a new game loop instance.
     * @param view
     *      the view to render on each frame.
     * @param model
     *      the model to update the world on each frame.
     */
    public GameLoopImpl(final View view, final Model model) {
        super();
        this.setName("Game Loop");
        this.setDaemon(true);
        this.view = view;
        this.model = model;
    }

    @Override
    public final void run() {
        this.updateGame(0);
        this.render();
        long lastTime = System.currentTimeMillis();
        while (!this.stopped) {
            final long current = System.currentTimeMillis();
            this.processInput();
            this.updateGame((current - lastTime) * MS_TO_S);
            this.render();
            lastTime = current;
        }
    }

    @Override
    public final synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    private void updateGame(final double elapsed) {
        this.model.getCurrentLevel().get().update(elapsed);
    }

    private void render() {
        this.view.render();
    }

    private void processInput() {

    }

}
