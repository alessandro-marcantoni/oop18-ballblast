package ballblast.model.levels;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.Model;
import ballblast.model.components.Component;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;

/**
 * Represents a concrete implementation of {@link LevelDecorator}.
 */
public class SurvivalLevelDecorator extends LevelDecorator {
    private static final int SPAWN_TIME = 5;
    private static final int ENABLE_TIME = 2;
    private static final double SPAWN_HEIGHT = 90;
    private static final Vector2D BALL_VELOCITY = Vector2D.create(16, 0);
    private static final int MIN_BALL_LIFE = 3;
    private static final int MAX_BALL_LIFE = 200;
    private static final double LIFE_MULTIPLIER = 0.25;

    // TODO private int totalTime; used to increase the survival's difficult.
    private int currentSpawnTime;
    private int currentEnableTime;
    private Optional<GameObject> spawnedBall;

    /**
     * Creates a {@link SurvivalLevelDecorator} instance.
     * 
     * @param level the level used like a decoration.
     */
    public SurvivalLevelDecorator(final Level level) {
        super(level);
        this.spawnedBall = Optional.empty();
        this.currentSpawnTime = SPAWN_TIME;
        this.currentEnableTime = ENABLE_TIME;
    }

    @Override
    public final void update(final double elapsed) {
        super.update(elapsed);
        if (this.spawnedBall.isPresent()) {
            this.currentEnableTime -= elapsed;
            if (this.currentEnableTime <= 0) {
                this.spawnedBall.get().getComponents().forEach(Component::enable);
                this.spawnedBall = Optional.empty();
                currentEnableTime = ENABLE_TIME;
            }
        }
        this.currentSpawnTime -= elapsed;
        if (this.currentSpawnTime <= 0) {
            this.spawnBall();
            this.currentSpawnTime = SPAWN_TIME;
        }
    }

    private void spawnBall() {
        this.spawnedBall = Optional.of(GameObjectFactory.createBall(BallTypes.LARGE, this.calculateBallLife(),
                this.getRandomPosition(), BALL_VELOCITY, this.getCollisionManager(), this.getGameObjectManager()));
        this.getGameObjectManager().addGameObjects(ImmutableList.of(this.spawnedBall.get()));
    }

    private Coordinate getRandomPosition() {
        return new Coordinate(
                this.generateRandomDouble(Model.WALL_OFFSET * 2, Model.WORLD_WIDTH - Model.WALL_OFFSET * 2),
                SPAWN_HEIGHT);
    }

    private double generateRandomDouble(final double min, final double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

    private int calculateBallLife() {
        final int life = (int) (this.getGameTime() * LIFE_MULTIPLIER) + MIN_BALL_LIFE;
        return life > MAX_BALL_LIFE ? MAX_BALL_LIFE : life;
    }
}
