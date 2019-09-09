package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;
import ballblast.model.powerups.Power;
import ballblast.model.powerups.PowerFactory;

/**
 * JUnit test for {@link Power}.
 */
public class TestPower {
    private static final Coordinate POSITION = new Coordinate(100, 100);
    private static final Coordinate DEFAULT = new Coordinate(0, 0);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);

    private final GameObjectManager gameObjectManager = new GameObjectManager();
    private final CollisionManager collisionManager = new SimpleCollisionManager();
    private GameObject player;
    private Power power; //NOPMD power is used locally.
    private GameObject ball; //NOPMD ball is used locally.

    /**
     * Gets the environment ready for the tests.
     */
    @Before
    public void initializeEnv() {
        this.player = GameObjectFactory.createPlayer(this.gameObjectManager, new InputManager(), PlayerTags.FIRST,
                this.collisionManager, VELOCITY, POSITION, null);
        this.player.getComponents().stream()
            .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
            .findFirst()
            .ifPresent(Component::enable);
    }

    /**
     * Test ShieldPower.
     */
    @Test
    public void testShieldPower() {
        this.power = PowerFactory.createShieldPower(VELOCITY, DEFAULT, this.collisionManager);
        this.power.activate(this.player);
        assertTrue(this.power.isActive());
        this.ball = GameObjectFactory.createBall(BallTypes.LARGE, 1, POSITION, VELOCITY, this.collisionManager,
                this.gameObjectManager, null);
        this.ball.getComponents().stream()
            .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
            .findFirst()
            .ifPresent(Component::enable);
        this.collisionManager.checkLoop();
        assertFalse(this.player.isDestroyed());
        this.power.deactivate();
        assertFalse(this.power.isActive());
        this.collisionManager.checkLoop();
        assertTrue(this.player.isDestroyed());
    }

}
