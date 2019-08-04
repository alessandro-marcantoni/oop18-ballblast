package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ballblast.model.components.CollisionComponent;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.Player;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.utils.Point2D;

/**
 * JUnit test for {@link Collidable}s.
 */
public class TestCollision {

    /**
     * Tests {@link CollisionComponent}s.
     */
    @Test
    public void testCollisionComponent() {
        final Player player = new Player.Builder().setPosition(Point2D.ZERO).build();
        final Collidable collisionComponent = new CollisionComponent(new CollisionManager(), CollisionTag.PLAYER);

        assertEquals(collisionComponent.toString(), "CollisionComponent{AttachedTo=PLAYER}");
        assertTrue(collisionComponent.getCollisionTag() == CollisionTag.PLAYER);
        assertFalse(!collisionComponent.getAttachedGameObject().get().equals(player));
    }

    /**
     * Tests {@link CollisionManager} object.
     */
    @Test
    public void testCollisionManager() {
        final CollisionManager manager = new CollisionManager();
        final Player player = new Player.Builder().build();
        final Collidable collisionComponentP = new CollisionComponent(manager, CollisionTag.PLAYER);
        final Ball ball = new Ball.Builder().setBallType(BallTypes.LARGE).build();


    }
}
