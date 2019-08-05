package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.Component;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.Player;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;

/**
 * JUnit test for {@link Collidable}s.
 */
public class TestCollision {

    /**
     * Tests {@link CollisionComponent}s.
     */
    @Test
    public void testCollisionComponent() {
        final Player player = new Player.Builder().addComponent(new CollisionComponent(new CollisionManager(), CollisionTag.PLAYER)).build();
        final Component collisionComponent = new CollisionComponent(new CollisionManager(), CollisionTag.PLAYER);
        //final Component collisionComponent = new CollisionComponent(new CollisionManager(), CollisionTag.PLAYER);
        //Component collisionComponent = player.getComponents().stream().filter(c -> c.getComponentType() == ComponentTypes.COLLISION).findFirst().get();
        collisionComponent.setParent(player);

        assertEquals(((CollisionComponent) collisionComponent).toString(), "CollisionComponent{AttachedTo=PLAYER}");
        assertTrue(((CollisionComponent) collisionComponent).getCollisionTag() == CollisionTag.PLAYER);
        assertFalse(!((CollisionComponent) collisionComponent).getAttachedGameObject().get().equals(player));
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
