package ballblast.test;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.utils.Point2D;
/**
 * 
 *
 */
public class TestCollision {

    private GameObject ball = new Ball();
    private GameObject player = new Player());
    private CollisionManager cm = new CollisionManager();

    private Collidable c1 = new CollisionComponent(cm, ComponentTypes.COLLISION, ball, CollisionTag.BALL);
    private Collidable c2 = new CollisionComponent(cm, ComponentTypes.COLLISION, player, CollisionTag.PLAYER);

    /**
     * 
     */
    public TestCollision() {
        ball.setPosition(new Point2D(5,5));
        player.setPosition(new Point2D(-0,2));

        ball.setWidth(6);
        player.setWidth(4);

        ball.setHeight(2);
        player.setHeight(4);

        cm.checkLoop();

    }


}
