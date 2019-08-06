package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.Bullet;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.gameobjects.Player;
import ballblast.model.gameobjects.Wall;
import ballblast.model.physics.CollisionManager;
/**
 * JUnit test for {@link GameObject}s.
 */
public class TestGameObjects {
    private GameObjectManager gameObjectManager;
    private CollisionManager collisionManager;
    /**
     * Initializes attributes.
     */
    @Before
    public void initTest() {
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new CollisionManager();
    }
    /**
     * Tests {@link Player}.
     */
    @Test
    public void testPlayer() {
        GameObjectFactory.createPlayer(gameObjectManager, collisionManager);
        final Player player = new Player.Builder().setPosition(new Coordinate(0, 0)).build();
        assertEquals(player.toString(), "Player{GameObjectType=PLAYER, Position=Point2D{X=0.0, Y=0.0}, IsDestroyed=false}");
        assertTrue(player.getComponents().isEmpty());
        assertTrue(player.getHeight() != 0);
        assertTrue(player.getWidth() != 0);
        //Tries to remove a component which is not attached to the player.
        player.removeComponent(ComponentTypes.GRAVITY);
    }
    /**
     * Test {@link Ball}.
     */
    @Test(expected = InvalidParameterException.class)
    public void testBall() {
        final Ball ball = new Ball.Builder().setBallType(BallTypes.LARGE).setLife(4).setPosition(new Coordinate(0, 0)).build();
        assertEquals(ball.toString(), "Ball{GameObjectType=BALL, BallType=LARGE, Diameter=30, Life=4, Position=Point2D{X=0.0, Y=0.0}, IsDestroyed=false}");
        assertTrue(ball.getComponents().isEmpty());
        assertTrue(ball.getHeight() != 0);
        assertTrue(ball.getWidth() == ball.getHeight());
        //Throws InvalidParameterException because the BallType has to be set.
        new Ball.Builder().build();
    }
    /**
     * Tests {@link Bullet}.
     */
    @Test
    public void testBullet() {
        final Bullet bullet = new Bullet.Builder().setPosition(new Coordinate(0, 0)).build();
        assertEquals(bullet.toString(), "Bullet{GameObjectType=BULLET, Position=Point2D{X=0.0, Y=0.0}, IsDestroyed=false}");
        assertTrue(bullet.getComponents().isEmpty());
        assertTrue(bullet.getHeight() != 0);
        assertTrue(bullet.getWidth() != 0);
        //Tries to remove a component which is not attached to the bullet.
        bullet.removeComponent(ComponentTypes.INPUT);
    }
    /**
     * Tests {@link Wall}.
     */
    @Test
    public void testWall() {
        final Wall wall = new Wall.Builder().setPosition(new Coordinate(0, 0)).build();
        assertEquals(wall.toString(), "Wall{GameObjectType=WALL, Position=Point2D{X=0.0, Y=0.0}, IsDestroyed=false}");
        assertTrue(wall.getComponents().isEmpty());
        assertTrue(wall.getHeight() != 0);
        assertTrue(wall.getWidth() != 0);
        //Tries to remove a component which is not attached to the wall.
        wall.removeComponent(ComponentTypes.INPUT);
    }
    /**
     * Tests {@link GameObjectManager}.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGameObjectManager() {
        final GameObjectManager objManager = new GameObjectManager();
        assertTrue(objManager.getGameObjects().isEmpty());
        //Adds some GameObject to the GameObjectManager.
        objManager.addGameObjects(ImmutableList.of(
                new Ball.Builder().setBallType(BallTypes.LARGE).build(),
                new Ball.Builder().setBallType(BallTypes.LARGE).build(),
                new Ball.Builder().setBallType(BallTypes.MEDIUM).build(),
                new Wall.Builder().build()));
        //Add new GameObject inside the available list.
        objManager.update(0);
        assertTrue(objManager.getGameObjects().size() > 0);
        //Variable to avoid magic number.
        final int totObjects = 6;
        objManager.addGameObjects(ImmutableList.of(
                new Ball.Builder().setBallType(BallTypes.MEDIUM).build(),
                new Wall.Builder().build()));
        objManager.update(0);
        final List<GameObject> gameObjects = objManager.getGameObjects();
        assertTrue(gameObjects.size() == totObjects);
        final int totBalls = 4;
        assertTrue(gameObjects.stream().filter(g -> g.getType() == GameObjectTypes.BALL).count() == totBalls);
        //Throws  UnsupportedOperationException because the List of GameObjects is a ImmutableList.
        gameObjects.remove(0);
    }
}
