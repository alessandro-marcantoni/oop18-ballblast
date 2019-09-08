package ballblast.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ballblast.controller.sound.Sound;
import ballblast.model.Model;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.model.levels.GameStatus;
import ballblast.view.View;

/**
 * Represents a game loop that starts in a new thread.
 */
public class GameLoopImpl extends Thread implements GameLoop {
    private static final double MS_TO_S = 0.001;

    private final List<GameLoopObserver> observers = new ArrayList<GameLoopObserver>();
    private final Map<PlayerTags, List<InputTypes>> inputs;
    private final View view;
    private final Model model;
    private final long frameRate;
    private boolean stopped;
    private boolean paused;

    /**
     * Creates a new game loop instance.
     * 
     * @param view  the view to render on each frame.
     * @param model the model to update the world on each frame.
     * @param frameRate the refresh rate of the loop.
     */
    public GameLoopImpl(final Model model, final View view, final int frameRate) {
        super();
        this.setName("Game Loop");
        this.setDaemon(true);
        this.view = view;
        this.model = model;
        this.frameRate = (long) (1 / (frameRate * MS_TO_S));
        this.inputs = ImmutableMap.of(PlayerTags.FIRST, new ArrayList<>(), PlayerTags.SECOND, new ArrayList<>());
    }

    @Override
    public final void run() {
        this.startTheme();
        this.stopped = false;
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
        this.view.setGameOver(true);
        this.updateLeaderboard();
        this.gameOverSound();
    }

    @Override
    public final synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    @Override
    public final synchronized void pause() {
        this.paused = true;
    }

    @Override
    public final synchronized void resumeLoop() {
        this.paused = false;
    }

    @Override
    public final synchronized void addInput(final PlayerTags tag, final InputTypes input) {
        this.inputs.get(tag).add(input);
    }

    @Override
    public final void addObserver(final GameLoopObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public final void updateLeaderboard() {
        this.observers.forEach(GameLoopObserver::updateLeaderboard);
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < this.frameRate) {
            try {
                Thread.sleep(this.frameRate - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void processInput() {
        this.inputs.forEach((k, v) -> {
            if (!v.isEmpty()) {
                this.model.resolveInputs(k, ImmutableList.copyOf(v));
                v.clear();
            }
        });
    }

    private boolean isStopped() {
        return this.stopped || model.getGameStatus().equals(GameStatus.OVER);
    }

    private boolean isPaused() {
        return this.paused || model.getGameStatus().equals(GameStatus.PAUSE);
    }

    private void updateGame(final double elapsed) {
        this.model.update(elapsed);
    }

    private void render() {
        this.view.render();
    }

    private void startTheme() {
        Sound.THEME.loopSound();
    }

    private void gameOverSound() {
        Sound.THEME.stopSound();
        Sound.GAMEOVER.playSound();
    }

}
