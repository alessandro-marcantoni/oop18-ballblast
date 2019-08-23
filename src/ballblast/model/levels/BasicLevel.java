package ballblast.model.levels;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.inputs.InputManager;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * Generic implementation of the {@link Level} interface. Defines base behavior
 * that all levels share.
 */
public final class BasicLevel implements Level {
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final InputManager inputManager;
    private double gameTime;
    private GameStatus gameStatus;

    /**
     * Creates a new instance of BasicLevel.
     */
    public BasicLevel() {
        this.gameStatus = GameStatus.PAUSE;
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new SimpleCollisionManager();
        this.inputManager = new InputManager();
        this.createBoundaries();
    }

    @Override
    public void start() {
        this.gameStatus = GameStatus.RUNNING;
        this.initGameObjectManager();
    }

    @Override
    public void update(final double elapsed) {
        if (this.gameStatus == GameStatus.RUNNING) {
            this.increaseGameTime(elapsed);
            this.gameObjectManager.update(elapsed);
            this.collisionManager.checkLoop();
        }
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public void setGameStatus(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
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
        // TODO change the game score policy.
        return (int) this.getGameTime();
    }

    @Override
    public InputManager getInputManager() {
        return this.inputManager;
    }

    @Override
    public double getGameTime() {
        return this.gameTime;
    }

    private void createBoundaries() {
        final List<GameObject> boundaries = Arrays
                .stream(Boundaries.values()).map(b -> GameObjectFactory.createWall(b.getHeight(), b.getWidth(),
                        b.getPosition(), b.getVelocity(), this.collisionManager))
                .collect(ImmutableList.toImmutableList());
        this.gameObjectManager.addGameObjects(boundaries);
    }

    private void activeComponents(final GameObject gameObject) {
        gameObject.getComponents().forEach(Component::enable);
    }

    private void initGameObjectManager() {
        this.gameObjectManager.update(0);
        this.gameObjectManager.getGameObjects().forEach(g -> this.activeComponents(g));
    }

    private void increaseGameTime(final double elapsed) {
        this.gameTime += elapsed;
    }
}
