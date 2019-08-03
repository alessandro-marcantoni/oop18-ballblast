package ballblast.model.levels;

import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.Player;
/**
 * Generic implementation of the {@link Level} interface.
 * Defines base behavior that all levels share.
 */
public final class BasicLevel implements Level {
    private final GameObjectManager gameObjectManager;
    private int gameScore;

    /**
     * Creates a new instance of BasicLevel.
     */
    public BasicLevel() {
        this.gameObjectManager = new GameObjectManager(new Player.Builder().build());
        this.gameScore = 0;
    }

    @Override
    public void update(final double elapsed) {
        this.gameObjectManager.getGameObjects().forEach(o -> o.update(elapsed));
    }

    @Override
    public GameObjectManager getGameObjectManager() {
        return this.gameObjectManager;
    }

    @Override
    public int getGameScore() {
        return this.gameScore;
    }
}
