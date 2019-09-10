package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;

class TestInputs {
    private static final double SAMPLE_ELAPSED = 1;
    private static final Vector2D LEFT_VELOCITY = Vector2D.create(-50, 0);
    private static final Vector2D RIGHT_VELOCITY = Vector2D.create(50, 0);
    private GameObject player;
    private Level level;
    private InputManager inputManager;

    @BeforeEach
    public void startUp() {
        this.level = new SinglePlayerDecorator(new BasicLevel());
        this.inputManager = this.level.getInputManager();
        this.level.start();
        this.player = this.findPlayer();
    }

    @Test
    public void testMovementInput() {
        // 1) Input test (Move left).
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.MOVE_LEFT));
        Coordinate expectedPosition = this.getExpectedPosition(LEFT_VELOCITY);
        this.level.update(SAMPLE_ELAPSED);
        assertEquals(expectedPosition, this.player.getPosition());
        // 2) Input test (Move right).
        this.inputManager.processInputs(PlayerTags.FIRST,
                ImmutableList.of(InputTypes.MOVE_LEFT, InputTypes.MOVE_RIGHT));
        expectedPosition = this.getExpectedPosition(RIGHT_VELOCITY);
        this.level.update(SAMPLE_ELAPSED);
        // The first Input (MOVE_LEFT) is not considered because is replace by the
        // second input received (MOVE_RIGHT).
        assertEquals(expectedPosition, this.player.getPosition());
        // 4) Input test (persistent input).
        expectedPosition = this.getExpectedPosition(RIGHT_VELOCITY);
        this.level.update(SAMPLE_ELAPSED);
        assertEquals(expectedPosition, this.player.getPosition());
        // 5) Input test (stop moving).
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.STOP_MOVING_RIGHT));
        assertEquals(expectedPosition, this.player.getPosition());
    }

    @Test
    public void testShootInput() {
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.SHOOT));
        assertFalse(this.countBullets() > 0);
        this.level.update(SAMPLE_ELAPSED); // Bullet has been spawn.
        long expectedSpawnedBall = 1;
        assertEquals(expectedSpawnedBall, this.countBullets());
        this.level.update(SAMPLE_ELAPSED);
        this.level.update(SAMPLE_ELAPSED);
        expectedSpawnedBall = 3;
        assertEquals(expectedSpawnedBall, this.countBullets());
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.STOP_SHOOTING));
        this.level.update(SAMPLE_ELAPSED); // Shooter stops shotting.
        this.level.update(SAMPLE_ELAPSED);
        this.level.update(SAMPLE_ELAPSED);
        // Nothing is changed.
        assertEquals(expectedSpawnedBall, this.countBullets());
    }

    @Test
    public void testShootAndMovementInput() {
        // 1) Move Left and Shoot.
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.SHOOT, InputTypes.MOVE_LEFT));
        Coordinate expectedPosition = this.getExpectedPosition(LEFT_VELOCITY);
        this.level.update(SAMPLE_ELAPSED);
        long expectedSpawnedBall = 1;
        assertEquals(expectedSpawnedBall, this.countBullets());
        assertEquals(expectedPosition, this.player.getPosition());
        // 2) Move right and continue to shoot.
        this.inputManager.processInputs(PlayerTags.FIRST, ImmutableList.of(InputTypes.MOVE_RIGHT));
        expectedPosition = this.getExpectedPosition(RIGHT_VELOCITY);
        expectedSpawnedBall = 2;
        this.level.update(SAMPLE_ELAPSED);
        assertEquals(expectedSpawnedBall, this.countBullets());
        assertEquals(expectedPosition, this.player.getPosition());
        // 3) Stop moving and shooting.
        this.inputManager.processInputs(PlayerTags.FIRST,
                ImmutableList.of(InputTypes.STOP_MOVING_RIGHT, InputTypes.STOP_SHOOTING));
        //Nothing is changed.
        assertEquals(expectedSpawnedBall, this.countBullets());
        assertEquals(expectedPosition, this.player.getPosition());
    }

    private Coordinate getExpectedPosition(final Vector2D velocity) {
        return velocity.multiply(SAMPLE_ELAPSED).translate(this.player.getPosition());
    }

    private GameObject findPlayer() {
        return this.level.getGameObjectManager().getGameObjects().stream()
                .filter(g -> g.getType() == GameObjectTypes.PLAYER).findFirst().get();
    }

    private int countBullets() {
        return (int) this.level.getGameObjectManager().getGameObjects().stream()
                .filter(g -> g.getType() == GameObjectTypes.BULLET).count();
    }
}
