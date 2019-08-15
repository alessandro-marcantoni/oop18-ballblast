package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.Player;
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
        final Player player = (Player) GameObjectFactory.createPlayer(new GameObjectManager(), new SimpleCollisionManager(), Vector2D.create(new Coordinate(0, 0)));
        collisionComponent.setParent(player);

        assertEquals(((CollisionComponent) collisionComponent).toString(), "CollisionComponent{AttachedTo=PLAYER}");
        assertTrue(((CollisionComponent) collisionComponent).getCollisionTag() == CollisionTag.PLAYER);
        assertFalse(!((CollisionComponent) collisionComponent).getAttachedGameObject().get().equals(player));
    }

    /**
     * Tests {@link SimpleCollisionManager} object.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCollisionManager() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 24;
        GameObjectFactory.createPlayer(new GameObjectManager(), manager, new Vector2D())
                         .getComponents()
                         .stream()
                         .filter(c -> c.getComponentType() == ComponentTypes.COLLISION)
                         .findFirst()
                         .get()
                         .enable();
        GameObjectFactory.createBall(BallTypes.SMALL, ballLife, new Coordinate(0, 0), new Vector2D(), manager)
                         .getComponents()
                         .stream()
                         .filter(c -> c.getComponentType() == ComponentTypes.COLLISION)
                         .findFirst()
                         .get()
                         .enable();

        assertTrue(manager.getCollidables().size() == 2);
        assertFalse(manager.getCollidables().isEmpty());
        manager.addCollidable(new CollisionComponent(new SimpleCollisionManager(), CollisionTag.BULLET));
        assertTrue(manager.getCollidables().size() == 3);
        // Remove all the collidables from the list.
        for (Collidable coll : manager.getCollidables()) {
            manager.removeCollidable(coll);
        }
        assertTrue(manager.getCollidables().size() == 0);
        assertTrue(manager.getCollidables().isEmpty());
        // Throws an UnsupportedOperationException because cannot remove an object from an empty list.
        manager.removeCollidable(new CollisionComponent(new SimpleCollisionManager(), CollisionTag.POWERUP));
    }

    /**
     * Tests {@link CollisionHandler}s objects.
     */
    @Test
    public void testCollisionHandler() {
        final CollisionManager manager = new SimpleCollisionManager();
        final int ballLife = 1;
        GameObject player = GameObjectFactory.createPlayer(new GameObjectManager(), manager, Vector2D.create(new Coordinate(0, 0)));
        GameObject ball = GameObjectFactory.createBall(BallTypes.SMALL, ballLife, new Coordinate(0, 0), Vector2D.create(new Coordinate(0, 0)), manager);
        GameObject bullet = GameObjectFactory.createBullet(new Coordinate(0, 0), new Vector2D(), manager);
        player.setPosition(new Coordinate(0, 0));

        // Enable all the game object's components.
        player.getComponents().forEach(c -> c.enable());
        ball.getComponents().forEach(c -> c.enable());
        bullet.getComponents().forEach(c -> c.enable());

        assertTrue(manager.getCollidables().size() == 3);
        assertFalse(player.isDestroyed());
        assertFalse(ball.isDestroyed());
        assertFalse(bullet.isDestroyed());
        manager.checkLoop();
        assertTrue(player.isDestroyed());
        assertFalse(ball.isDestroyed());
        assertTrue(manager.getCollidables().size() == 2);
        manager.checkLoop();
        assertTrue(bullet.isDestroyed());
        assertTrue(ball.isDestroyed());
    }
}
