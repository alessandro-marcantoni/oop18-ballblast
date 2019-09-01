package ballblast.model.levels;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.Model;
import ballblast.model.components.Component;
import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;

/**
 * Represents a concrete implementation of {@link LevelDecorator}.
 */
public class SurvivalLevelDecorator extends LevelDecorator {
    private static final int SPAWN_TIME = 10;
    private static final int ENABLE_TIME = 2;
    private static final int MIN_BALL_LIFE = 4;
    private static final int MAX_BALL_LIFE = 200;
    private static final double HEIGHT_OFFSET = 4;
    private static final double LIFE_MULTIPLIER = 0.25;
    private static final double SCORE_MULTIPLIER = 0.8;
    private static final Vector2D BALL_VELOCITY = Vector2D.create(8, 0);

    private double currentSpawnTime;
    private double currentEnableTime;
    private Optional<GameObject> spawnedBall;

    /**
     * Creates a {@link SurvivalLevelDecorator} instance.
     * 
     * @param level the level used like a decoration.
     */
    public SurvivalLevelDecorator(final Level level) {
        super(level);
        this.spawnedBall = Optional.empty();
        this.currentEnableTime = ENABLE_TIME;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getGameStatus() == GameStatus.RUNNING) {
            super.update(elapsed);
            this.tryToEnable(elapsed);
            this.tryToSpawn(elapsed);
            this.updateScore();
        }
    }

    private static double generateRandomDouble(final double min, final double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

    private void spawnBall() {
        this.spawnedBall = Optional.of(GameObjectFactory.createBall(BallTypes.LARGE, this.calculateBallLife(),
                this.getRandomPosition(), BALL_VELOCITY, this.getCollisionManager(), this.getGameObjectManager(),
                this.getGameDataManager()));
        this.getGameObjectManager().addGameObjects(ImmutableList.of(this.spawnedBall.get()));
    }

    private Coordinate getRandomPosition() {
        return new Coordinate(
                generateRandomDouble(Boundaries.LEFT.getWidth() + BallTypes.LARGE.getDiameter(),
                        Model.WORLD_WIDTH - Boundaries.RIGHT.getWidth() - BallTypes.LARGE.getDiameter()),
                Boundaries.TOP.getHeight() + HEIGHT_OFFSET);

    }

    private int calculateBallLife() {
        final int life = (int) (this.getGameDataManager().getGameData().getTime() * LIFE_MULTIPLIER) + MIN_BALL_LIFE;
        return life > MAX_BALL_LIFE ? MAX_BALL_LIFE : life;
    }

    private void updateScore() {
        final GameData gameData = this.getGameDataManager().getGameData();
        this.getGameDataManager()
                .calculateScore((int) (gameData.getTime() + gameData.getDestroyedBalls() * SCORE_MULTIPLIER));
    }

    private void tryToSpawn(final double elapsed) {
        this.currentSpawnTime -= elapsed;
        if (this.currentSpawnTime <= 0) {
            this.spawnBall();
            this.currentSpawnTime = SPAWN_TIME;
        }
    }

    private void tryToEnable(final double elapsed) {
        if (this.spawnedBall.isPresent()) {
            this.currentEnableTime -= elapsed;
            if (this.currentEnableTime <= 0) {
                this.spawnedBall.get().getComponents().forEach(Component::enable);
                this.spawnedBall = Optional.empty();
                currentEnableTime = ENABLE_TIME;
            }
        }
    }
}
