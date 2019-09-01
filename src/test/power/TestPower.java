package test.power;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.SimpleCollisionManager;
import ballblast.model.powerups.PowerFactoryImpl;

/**
 * JUnit test for {@link Power}.
 */
public class TestPower {
    private static final Coordinate POSITION = new Coordinate(30, 30);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);

    private PowerFactoryImpl factory = new PowerFactoryImpl();
    private Level level;
    private GameObject player;

    /**
     * Gets the environment ready for the tests.
     */
    @Before
    public void initializeEnv() {
        this.level = new SinglePlayerDecorator(new BasicLevel());
        this.player = GameObjectFactory.createPlayer(this.level.getGameObjectManager(), null, PlayerTags.FIRST,
                this.level.getCollisionManager(), VELOCITY, POSITION, null);
        this.level.getGameObjectManager().getGameObjects().add(this.player);
        final Component collisionComponent = new CollisionComponent(new SimpleCollisionManager(), CollisionTag.PLAYER);
        collisionComponent.setParent(this.player);
        System.out.println(this.level.getGameObjectManager().getGameObjects());
    }

    /**
     * Test ShieldPower.
     */
    @Test
    public void testShieldPower() {

    }

}
