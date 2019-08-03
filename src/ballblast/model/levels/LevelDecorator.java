package ballblast.model.levels;

import ballblast.model.gameobjects.GameObjectManager;
/**
 * Represents an abstraction for all level decorators. Every method of the {@link Level} 
 * interface is implemented by delegating to the decorated instance of level.
 */
 
public abstract class LevelDecorator implements Level {
    private final Level innerLevel;
    /**
     * Creates a {@link LevelDecorator} instance.
     * @param level
     *     the level used like decoration.
     */
    public LevelDecorator(final Level level) {
        this.innerLevel = level;
    }
    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void update(final double elapsed) {
        this.innerLevel.update(elapsed);
    }
    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public final GameObjectManager getGameObjectManager() {
        return this.innerLevel.getGameObjectManager();
    }
    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public final int getGameScore() {
        return this.innerLevel.getGameScore();
    }

}
