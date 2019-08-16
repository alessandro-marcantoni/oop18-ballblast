package ballblast.model.levels;

import java.util.Arrays;
import java.util.List;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.Component;
import ballblast.model.constants.Boundaries;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * Generic implementation of the {@link Level} interface. Defines base behavior
 * that all levels share.
 */
public final class BasicLevel implements Level {
    private static final int INITIAL_GAME_SCORE = 0;
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    /**
     * Creates a new instance of BasicLevel.
     */
    public BasicLevel() {
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new SimpleCollisionManager();
        this.initGameObjectManager();
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
        return INITIAL_GAME_SCORE;
    }

    private void addPlayer() {
        this.gameObjectManager.addGameObjects(ImmutableList
                .of(GameObjectFactory.createPlayer(gameObjectManager, collisionManager, Vector2D.create(0, 0))));
    }

    private void createBoundaries() {
        final List<GameObject> boundaries = Arrays.stream(Boundaries.values()).map(b -> GameObjectFactory
                .createWall(b.getHeight(), b.getWidth(), b.getPosition(), b.getVelocity(), collisionManager))
                .collect(ImmutableList.toImmutableList());
        this.gameObjectManager.addGameObjects(boundaries);
    }

    private void initGameObjectManager() {
        this.createBoundaries();
        this.addPlayer();
        this.gameObjectManager.getGameObjects().forEach(g -> this.activeComponents(g));
    }

    private void activeComponents(final GameObject gameObject) {
        gameObject.getComponents().forEach(Component::enable);
    }
}
