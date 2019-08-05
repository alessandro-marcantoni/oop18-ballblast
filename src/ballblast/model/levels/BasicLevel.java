package ballblast.model.levels;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.CollisionComponent;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.Wall;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.utils.Boundaries;

/**
 * Generic implementation of the {@link Level} interface. Defines base behavior
 * that all levels share.
 */
public final class BasicLevel implements Level {
    private static final int INITIAL_GAME_SCORE = 0;
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private int gameScore;

    /**
     * Creates a new instance of BasicLevel.
     */
    public BasicLevel() {
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new CollisionManager();
        this.gameScore = INITIAL_GAME_SCORE;
        this.createBoundaries();
        this.addPlayer();
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
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    @Override
    public int getGameScore() {
        return this.gameScore;
    }

    private void addPlayer() {
        this.gameObjectManager
                .addGameObjects(ImmutableList.of(GameObjectFactory.createPlayer(gameObjectManager, collisionManager)));
    }

    private void createBoundaries() {
        List<GameObject> boundaries = Arrays.stream(Boundaries.values())
                .map(b -> new Wall.Builder().setHeight(b.getHeight()).setWidth(b.getWidth())
                        .setPosition(b.getPosition())
                        .addComponent(new CollisionComponent(collisionManager, CollisionTag.WALL)).build())
                .collect(ImmutableList.toImmutableList());
        this.gameObjectManager.addGameObjects(boundaries);
    }
}
