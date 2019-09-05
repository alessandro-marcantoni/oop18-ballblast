package test.component;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.components.GravityComponent;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * JUnit test for {@link GravityComponent}.
 */
public class TestGravity {

    private static final Coordinate POSITION = new Coordinate(100, 100);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);
    private static final Vector2D GRAVITY = new Vector2D(0, 12);

    private final GameObjectManager gameObjectManager = new GameObjectManager();
    private final CollisionManager collisionManager = new SimpleCollisionManager();
    private GameObject ball;

    /**
     * Initializes the needed objects.
     */
    @Before
    public void initializeEnv() {
        this.ball = GameObjectFactory.createBall(BallTypes.LARGE, 1, POSITION, VELOCITY, this.collisionManager,
                this.gameObjectManager, null);
    }

    /**
     * Tests the {@link GravityComponent}.
     */
    @Test
    public void testGravity() {
        assertEquals(this.ball.getPosition(), POSITION);
        this.ball.getComponents().stream()
            .filter(c -> c.getType().equals(ComponentTypes.MOVEMENT) 
                    || c.getType().equals(ComponentTypes.GRAVITY))
            .forEach(Component::enable);
        this.ball.getComponents().stream()
            .filter(c -> c.getType().equals(ComponentTypes.GRAVITY))
            .findFirst().ifPresent(c -> ((GravityComponent) c).setGravity(GRAVITY)); 
        this.ball.update(5);
        System.out.println(this.ball.getVelocity());
        System.out.println(this.ball.getPosition());
    }

}
