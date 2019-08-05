package ballblast.model.levels;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.utils.Point2D;

/**
 * Represents a concrete implementation of {@link LevelDecorator}.
 */
public class SurvivalLevelDecorator extends LevelDecorator {
    private static final int SPAWN_TIME = 10;
    //private static final int ENABLE_TIME = 2;
    private static final Point2D ENEMY_SPAWN_POSITION = Point2D.ZERO;

    //TODO private int totalTime; used to increase the survival's difficult.
    private int currentSpawnTime;
    /**
     * Creates a {@link SurvivalLevelDecorator} instance.
     * @param level
     *     the level used like a decoration.
     */
    public SurvivalLevelDecorator(final Level level) {
        super(level);
        this.currentSpawnTime = SPAWN_TIME;
    }

    @Override
    public final void update(final double elapsed) {
        super.update(elapsed);
        this.currentSpawnTime -= elapsed;
        if (this.currentSpawnTime <= 0) {
            this.spawnBall();
            this.currentSpawnTime = SPAWN_TIME;
        }
    }

    private void spawnBall() {
        final GameObject ball = new Ball.Builder()
                .setBallType(BallTypes.LARGE)
                .setPosition(ENEMY_SPAWN_POSITION)
                .build();
        this.getGameObjectManager().addGameObjects(ImmutableList.of(ball));
    }

}
