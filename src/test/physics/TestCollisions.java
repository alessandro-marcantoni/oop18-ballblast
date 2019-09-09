package test.physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.data.GameDataManager;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.Boundaries;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.gameobjects.Player;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * JUnit test for {@link Collidable}s.
 */
public class TestCollisions {

    private static final CollisionManager COLLISION_MANAGER = new SimpleCollisionManager();
    private static final GameObjectManager GAME_OBJECT_MANAGER = new GameObjectManager();
    private static final InputManager INPUT_MANAGER = new InputManager();
    private static final GameDataManager GAME_DATA_MANAGER = new GameDataManager();
    private static final Coordinate DEFAULT_POSITION = new Coordinate(0, 0);
    private static final Vector2D DEFAULT_VELOCITY = new Vector2D(0, 0);
    private static final Level DEFAULT_LEVEL = new SinglePlayerDecorator(new BasicLevel());

    /**
     * Tests {@link CollisionComponent}s.
     */
    @Test
    public void testCollisionComponent() {
        final Component collisionComponent = new CollisionComponent(COLLISION_MANAGER, CollisionTag.PLAYER);
        final Player player = (Player) GameObjectFactory.createPlayer(GAME_OBJECT_MANAGER, INPUT_MANAGER,
                PlayerTags.FIRST, COLLISION_MANAGER, DEFAULT_VELOCITY, DEFAULT_POSITION, GAME_DATA_MANAGER);
        collisionComponent.setParent(player);

        assertEquals(((CollisionComponent) collisionComponent).toString(), "CollisionComponent{AttachedTo=PLAYER}");
        assertSame(((CollisionComponent) collisionComponent).getCollisionTag(), CollisionTag.PLAYER);
        assertTrue(((CollisionComponent) collisionComponent).getAttachedGameObject().equals(player));
    }

    /**
     * Tests {@link SimpleCollisionManager} object.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCollisionManager() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 24;
        GameObjectFactory
                .createPlayer(GAME_OBJECT_MANAGER, INPUT_MANAGER, PlayerTags.FIRST, manager, DEFAULT_VELOCITY,
                        DEFAULT_POSITION, GAME_DATA_MANAGER)
                .getComponents().stream().filter(c -> c.getType() == ComponentTypes.COLLISION).findFirst().get()
                .enable();
        GameObjectFactory
                .createBall(BallTypes.SMALL, ballLife, DEFAULT_POSITION, DEFAULT_VELOCITY, manager,
                        GAME_OBJECT_MANAGER, GAME_DATA_MANAGER)
                .getComponents().stream().filter(c -> c.getType() == ComponentTypes.COLLISION).findFirst().get()
                .enable();

        assertEquals(manager.getCollidables().size(), 2);
        assertFalse(manager.getCollidables().isEmpty());
        manager.addCollidable(new CollisionComponent(COLLISION_MANAGER, CollisionTag.BULLET));
        assertEquals(manager.getCollidables().size(), 3);
        // Remove all the collidables from the list.
        for (final Collidable coll : manager.getCollidables()) {
            manager.removeCollidable(coll);
        }
        assertEquals(manager.getCollidables().size(), 0);
        assertTrue(manager.getCollidables().isEmpty());
        // Throws an UnsupportedOperationException because cannot remove an object from
        // an empty list.
        manager.removeCollidable(new CollisionComponent(COLLISION_MANAGER, CollisionTag.POWERUP));
    }

    /**
     * Tests {@link CollisionHandler}s objects.
     */
    @Test
    public void testCollisionHandler() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 1;
        final int pos = 25;
        final GameObject player = GameObjectFactory.createPlayer(GAME_OBJECT_MANAGER, INPUT_MANAGER,
                PlayerTags.FIRST, manager, DEFAULT_VELOCITY, DEFAULT_POSITION, GAME_DATA_MANAGER);
        final GameObject ball = GameObjectFactory.createBall(BallTypes.SMALL, ballLife, DEFAULT_POSITION,
                DEFAULT_VELOCITY, manager, GAME_OBJECT_MANAGER, GAME_DATA_MANAGER);
        final GameObject bullet = GameObjectFactory.createBullet(new Coordinate(pos, pos), DEFAULT_VELOCITY, manager);

        // Enable all the game object's components.
        player.getComponents().forEach(c -> c.enable());
        ball.getComponents().forEach(c -> c.enable());
        bullet.getComponents().forEach(c -> c.enable());

        assertEquals(manager.getCollidables().size(), 3);
        assertFalse(player.isDestroyed());
        assertFalse(ball.isDestroyed());
        assertFalse(bullet.isDestroyed());
        // Check if there are collision between game objects.
        manager.checkLoop();
        // Expected a collision between the player and the ball.
        // Player ---> destroy.
        assertTrue(player.isDestroyed());
        assertFalse(ball.isDestroyed());
        assertFalse(bullet.isDestroyed());
        assertEquals(manager.getCollidables().size(), 2);
        ball.setPosition(new Coordinate(pos, pos));
        manager.checkLoop();
        // Expected a collision between the bullet and the ball.
        // Ball ---> decrement life and destroy if life = 0.
        // Bullet ---> destroy.
        assertTrue(bullet.isDestroyed());
        assertTrue(ball.isDestroyed());
        assertEquals(manager.getCollidables().size(), 0);

        // Game objects destroyed, have to create a new ones.
        final GameObject balls = GameObjectFactory.createBall(BallTypes.SMALL, ballLife + 1, DEFAULT_POSITION,
                DEFAULT_VELOCITY, manager, GAME_OBJECT_MANAGER, GAME_DATA_MANAGER);
        final GameObject bullets = GameObjectFactory.createBullet(new Coordinate(pos, pos), DEFAULT_VELOCITY, manager);

        balls.getComponents().forEach(c -> c.enable());
        bullets.getComponents().forEach(c -> c.enable());

        assertEquals(manager.getCollidables().size(), 2);
        manager.checkLoop();
        assertFalse(balls.isDestroyed());
        assertFalse(bullets.isDestroyed());
        // Move the Ball forward the Bullet.
        balls.setPosition(new Coordinate(pos, pos));
        manager.checkLoop();
        // Expected a collision between the ball and the bullet.
        // Bullet ---> destroy.
        // Ball ---> decrement life but still alive.
        assertTrue(bullets.isDestroyed());
        assertFalse(balls.isDestroyed());
        // The Ball object is still alive but his life has been decremented.
        assertEquals(((Ball) balls).getCurrentLife(), 1);
        assertEquals(manager.getCollidables().size(), 1);
    }

    /**
     * Tests the correct behavior after the collision with a Wall object. The Ball
     * has to bounce correctly.
     */
    @Test
    public void testBounce() {
        final int ballLife = 3;
        final double x = 5;
        final double y = 5;
        final int neg = -1;
        final CollisionManager manager = new SimpleCollisionManager();
        final GameObject ball = GameObjectFactory.createBall(BallTypes.SMALL, ballLife, Boundaries.BOTTOM.getPosition(),
                Vector2D.create(new Coordinate(x, y)), manager, GAME_OBJECT_MANAGER, GAME_DATA_MANAGER);
        final GameObject wall = GameObjectFactory.createWall(x, y, Boundaries.BOTTOM.getPosition(), DEFAULT_VELOCITY,
                manager);

        ball.getComponents().forEach(c -> c.enable());
        wall.getComponents().forEach(c -> c.enable());

        manager.checkLoop();
        // Expected floor bounce.
        assertEquals(Double.valueOf(ball.getVelocity().getY()), Double.valueOf(y * neg));
        assertEquals(Double.valueOf(ball.getVelocity().getX()), Double.valueOf(x));

        ball.setPosition(Boundaries.LEFT.getPosition());
        wall.setPosition(Boundaries.LEFT.getPosition());

        manager.checkLoop();
        // Expected wall bounce.
        assertEquals(Double.valueOf(ball.getVelocity().getY()), Double.valueOf(y * neg));
        assertEquals(Double.valueOf(ball.getVelocity().getX()), Double.valueOf(x * neg));

    }

    /**
     * Tests the correct behavior after the collision with a Wall object. The Player
     * hasn't to change his position.
     */
    @Test
    public void testStop() {
        final Level lvl = DEFAULT_LEVEL;
        lvl.start();
        lvl.getInputManager().processInputs(InputManager.PlayerTags.FIRST, ImmutableList.of(InputTypes.MOVE_RIGHT));
        final int steps = 50;
        final double elapsed = 0.1;
        final GameObject player = lvl.getGameObjectManager().getGameObjects().stream()
                .filter(obj -> obj.getType() == GameObjectTypes.PLAYER).findFirst().get();

        for (int i = 0; i < steps; i++) {
            final Coordinate pos = player.getPosition();
            lvl.update(elapsed);
            assertTrue(pos.getX() <= Boundaries.RIGHT.getPosition().getX());
        }
    }
}
