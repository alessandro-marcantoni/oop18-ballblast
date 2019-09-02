package ballblast.model.levels;

import java.util.Arrays;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.Model;
import ballblast.model.components.Component;
import ballblast.model.data.GameDataManager;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.inputs.InputManager;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;
import ballblast.model.powerups.PowerFactory;

/**
 * Generic implementation of the {@link Level} interface. Defines base behavior
 * that all levels share.
 */
public final class BasicLevel implements Level {
    private static final double POWER_SPAWN_TIME = 20.0;
    private static final double SPAWN_OFFSET = 4.0;
    private static final double SPAWN_X = Model.WORLD_WIDTH / 2;
    private static final double SPAWN_Y = Boundaries.TOP.getHeight() + SPAWN_OFFSET;

    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final InputManager inputManager;
    private final GameDataManager gameDataManager;
    private GameStatus gameStatus;
    private double currentSpawnTime;

    /**
     * Class constructor.
     */
    public BasicLevel() {
        this.gameStatus = GameStatus.PAUSE;
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new SimpleCollisionManager();
        this.inputManager = new InputManager();
        this.gameDataManager = new GameDataManager();
        this.currentSpawnTime = POWER_SPAWN_TIME;
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
            this.gameDataManager.updateGameTime(elapsed);
            this.gameObjectManager.update(elapsed);
            this.collisionManager.checkLoop();
            this.tryToSpawn(elapsed);
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
    public InputManager getInputManager() {
        return this.inputManager;
    }

    @Override
    public GameDataManager getGameDataManager() {
        return this.gameDataManager;
    }

    private void createBoundaries() {
        final List<GameObject> boundaries = Arrays.stream(Boundaries.values())
                .map(this::convertToWall)
                .collect(ImmutableList.toImmutableList());
        this.gameObjectManager.addGameObjects(boundaries);
    }

    private GameObject convertToWall(final Boundaries b) {
        return GameObjectFactory.createWall(b.getHeight(), b.getWidth(), 
                b.getPosition(), Vector2D.create(0, 0), this.collisionManager);
    }

    private void activeComponents(final GameObject gameObject) {
        gameObject.getComponents().forEach(Component::enable);
    }

    private void initGameObjectManager() {
        this.gameObjectManager.update(0);
        this.gameObjectManager.getGameObjects().forEach(this::activeComponents);
    }

    private void tryToSpawn(final double elapsed) {
        this.currentSpawnTime -= elapsed;
        if (this.currentSpawnTime <= 0) {
            this.spawnPowerUp();
            this.currentSpawnTime = POWER_SPAWN_TIME;
        }
    }

    private void spawnPowerUp() {
        final GameObject power = ((GameObject) PowerFactory.createRandomPower(
                Vector2D.create(0, 0), new Coordinate(SPAWN_X, SPAWN_Y), this.collisionManager));
        this.activeComponents(power);
        this.getGameObjectManager().addGameObjects(ImmutableList.of(power));
    }
}
