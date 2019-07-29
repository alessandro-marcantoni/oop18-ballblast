package ballblast.model.levels;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.GameObject;
/**
 * The BasicLevel class implements the Level interface.
 */
public class BasicLevel implements Level {

    private ImmutableList<GameObject> gameObjects;
    private int gameScore;

    /**
     * Creates a new instance of BasicLevel.
     */
    public BasicLevel() {
        this.gameObjects = ImmutableList.of();
        this.gameScore = 0;
    }

    @Override
    public final void update(final double elapsed) {
        this.gameObjects.forEach(o -> o.update(elapsed));
    }

    @Override
    public final List<GameObject> getGameObjects() {
        return ImmutableList.copyOf(gameObjects);
    }

    @Override
    public final int getGameScore() {
        return this.gameScore;
    }
}
