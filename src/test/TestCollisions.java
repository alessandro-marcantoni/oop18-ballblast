package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.levels.Boundaries;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.Player;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * JUnit test for {@link Collidable}s.
 */
public class TestCollisions {
    /**
     * Tests {@link CollisionComponent}s.
     */
    @Test
    public void testCollisionComponent() {
        final Component collisionComponent = new CollisionComponent(new SimpleCollisionManager(), CollisionTag.PLAYER);
        final Player player = (Player) GameObjectFactory.createPlayer(new GameObjectManager(), new InputManager(),
                PlayerTags.FIRST, new SimpleCollisionManager(), Vector2D.create(new Coordinate(0, 0)), null);
        collisionComponent.setParent(player);

        assertEquals(((CollisionComponent) collisionComponent).toString(), "CollisionComponent{AttachedTo=PLAYER}");
        assertSame(((CollisionComponent) collisionComponent).getCollisionTag(), CollisionTag.PLAYER);
        assertTrue(((CollisionComponent) collisionComponent).getAttachedGameObject().get().equals(player));
    }

    /**
     * Tests {@link SimpleCollisionManager} object.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCollisionManager() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 24;
        GameObjectFactory
                .createPlayer(new GameObjectManager(), new InputManager(), PlayerTags.FIRST, manager, new Vector2D(), null)
                .getComponents().stream().filter(c -> c.getType() == ComponentTypes.COLLISION).findFirst().get()
                .enable();
        GameObjectFactory.createBall(BallTypes.SMALL, ballLife, new Coordinate(0, 0), new Vector2D(), manager)
                .getComponents().stream().filter(c -> c.getType() == ComponentTypes.COLLISION).findFirst().get()
                .enable();

        assertEquals(manager.getCollidables().size(), 2);
        assertFalse(manager.getCollidables().isEmpty());
        manager.addCollidable(new CollisionComponent(new SimpleCollisionManager(), CollisionTag.BULLET));
        assertEquals(manager.getCollidables().size(), 3);
        // Remove all the collidables from the list.
        for (final Collidable coll : manager.getCollidables()) {
            manager.removeCollidable(coll);
        }
        assertEquals(manager.getCollidables().size(), 0);
        assertTrue(manager.getCollidables().isEmpty());
        // Throws an UnsupportedOperationException because cannot remove an object from
        // an empty list.
        manager.removeCollidable(new CollisionComponent(new SimpleCollisionManager(), CollisionTag.POWERUP));
    }

    /**
     * Tests {@link CollisionHandler}s objects.
     */
    @Test
    public void testCollisionHandler() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 1;
        final int pos = 20;
        final GameObject player = GameObjectFactory.createPlayer(new GameObjectManager(), new InputManager(),
                PlayerTags.FIRST, manager, Vector2D.create(new Coordinate(0, 0)), null);
        final GameObject ball = GameObjectFactory.createBall(BallTypes.SMALL, ballLife, new Coordinate(0, 0),
                Vector2D.create(new Coordinate(0, 0)), manager);
        final GameObject bullet = GameObjectFactory.createBullet(new Coordinate(pos, pos), new Vector2D(), manager);
        player.setPosition(new Coordinate(0, 0));

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

        final GameObject balls = GameObjectFactory.createBall(BallTypes.SMALL, ballLife + 1, new Coordinate(0, 0),
                Vector2D.create(new Coordinate(0, 0)), manager);
        final GameObject bullets = GameObjectFactory.createBullet(new Coordinate(pos, pos), new Vector2D(), manager);

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
        assertEquals(((Ball) balls).getLife(), 1);
        assertEquals(manager.getCollidables().size(), 1);
    }

    /**
     * Test the correct behavior after the collision with a Wall object.
     * The Ball has to bounce correctly.
     * The Player has to stop.
     */
    @Test
    public void testBounce() {
        final int ballLife = 3;
        final double x = 5;
        final double y = 5;
        final CollisionManager manager = new SimpleCollisionManager();
        final GameObject ball = GameObjectFactory.createBall(BallTypes.SMALL, ballLife, Boundaries.BOTTOM.getPosition(), Vector2D.create(new Coordinate(x, y)), manager);
        final GameObject wall = GameObjectFactory.createWall(x, y, Boundaries.BOTTOM.getPosition(), new Vector2D(), manager);

        ball.getComponents().forEach(c -> c.enable());
        wall.getComponents().forEach(c -> c.enable());

        manager.checkLoop();
        assertTrue(ball.getVelocity().getY() == y * -1);
        assertTrue(ball.getVelocity().getX() == x);

        ball.setPosition(Boundaries.LEFT.getPosition());
        wall.setPosition(Boundaries.LEFT.getPosition());

        manager.checkLoop();
        assertTrue(ball.getVelocity().getY() == y * -1);
        assertTrue(ball.getVelocity().getX() == x * -1);
    }
}
