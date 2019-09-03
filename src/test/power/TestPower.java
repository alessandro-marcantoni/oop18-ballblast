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
/**
 * JUnit test for {@link Power}.
 */
public class TestPower {
    private static final Coordinate POSITION = new Coordinate(30, 30);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);
    /**
     * Gets the environment ready for the tests.
     */
    @Before
    public void initializeEnv() {
        final Level level = new SinglePlayerDecorator(new BasicLevel());
        final GameObject player = GameObjectFactory.createPlayer(level.getGameObjectManager(), null, PlayerTags.FIRST,
                level.getCollisionManager(), VELOCITY, POSITION, null);
        level.getGameObjectManager().getGameObjects().add(player);
        final Component collisionComponent = new CollisionComponent(new SimpleCollisionManager(), CollisionTag.PLAYER);
        collisionComponent.setParent(player);
        System.out.println(level.getGameObjectManager().getGameObjects());
    }

    /**
     * Test ShieldPower.
     */
    @Test
    public void testShieldPower() {

    }

}
