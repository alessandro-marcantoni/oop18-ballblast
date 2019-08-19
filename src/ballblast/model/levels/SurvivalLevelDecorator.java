package ballblast.model.levels;

import java.util.Optional;
import java.util.Random;

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
    private static final Vector2D INITIAL_BALL_VELOCITY = Vector2D.create(16, 0);
    private static final double SPAWN_HEIGHT = 90;
    private static final double WORLD_OFFSET = 20;

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
            //currentSpawnTime = SPAWN_TIME * Math.pow(DECREASE_RATE, this.ballNumber) + MIN_WAIT_TIME;
            this.currentSpawnTime = SPAWN_TIME;
        }
    }

    private void spawnBall() {
        this.spawnedBall = Optional.of(GameObjectFactory.createBall(BallTypes.LARGE, 100, this.getRandomPosition(),
                INITIAL_BALL_VELOCITY, this.getCollisionManager()));
        this.getGameObjectManager().addGameObjects(ImmutableList.of(this.spawnedBall.get()));
    }

    private Coordinate getRandomPosition() {
        final Random rand = new Random();
        final double xvalue = (rand.nextDouble() * 2 - 1) * (Model.WORLD_WIDTH / 2 - WORLD_OFFSET);
        return new Coordinate(xvalue, SPAWN_HEIGHT);
    }

    /*private int generateBallLife() {
        return 0;
    }*/
}
