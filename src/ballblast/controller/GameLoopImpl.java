package ballblast.controller;

import ballblast.model.Model;
import ballblast.model.levels.GameStatus;
import ballblast.view.View;

/**
 * Represents a game loop that starts in a new thread.
 */
public class GameLoopImpl extends Thread implements GameLoop {
    private static final double MS_TO_S = 0.001;
    private static final long PERIOD = 20;

    private boolean stopped;
    private boolean paused;
    private final View view;
    private final Model model;

    /**
     * Creates a new game loop instance.
     * @param view
     *      the view to render on each frame.
     * @param model
     *      the model to update the world on each frame.
     */
    public GameLoopImpl(final Model model, final View view) {
        super();
        this.setName("Game Loop");
        this.setDaemon(true);
        this.view = view;
        this.model = model;
    }

    @Override
    public final void run() {
        this.stopped = false;
        this.render();
        long lastTime = System.currentTimeMillis();
        while (!this.isStopped()) {
            final long current = System.currentTimeMillis();
            this.processInput();
            if (!this.isPaused()) {
                final long elapsed = current - lastTime;
                this.updateGame(elapsed * MS_TO_S);
                this.render();
                // In order to lock the frame rate.
                this.waitForNextFrame(current);
            }
            lastTime = current;
        }
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final synchronized void stopLoop() {
        this.stopped = true;
        //this.model.getCurrentLevel().ifPresent(l -> l.setGameStatus(GameStatus.OVER));
        this.interrupt();
    }
    @Override
    public final void pause() {
        this.paused = true;
        ///this.model.getCurrentLevel().ifPresent(l -> l.setGameStatus(GameStatus.PAUSE));
    }

    @Override
    public final void resumeLoop() {
        this.paused = false;
        //this.model.getCurrentLevel().ifPresent(l -> l.setGameStatus(GameStatus.RUNNING));
    }

    private boolean isStopped() {
        //return this.model.getCurrentLevel().isPresent() ? this.stopped || this.model.getCurrentLevel().get().getGameStatus().equals(GameStatus.OVER) : this.stopped;
        return this.stopped || model.getGameStatus() == GameStatus.OVER;
    }

    private boolean isPaused() {
        //return this.model.getCurrentLevel().isPresent() ? this.paused || this.model.getCurrentLevel().get().getGameStatus().equals(GameStatus.PAUSE) : this.paused;
        return this.paused;
    }

    private void updateGame(final double elapsed) {
        this.model.update(elapsed);
    }

    private void render() {
        this.view.render();
    }

    private void processInput() {
    }

}
