package ballblast.model.components;

import java.util.List;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;

/**
 * 
 */
public class SplitterComponent extends AbstractComponent {
    private static final double GRAVITY_Y = -100;
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;

    /**
     * Creates a {@link SplitterComponent} instance.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Ball}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link Ball}'s {@link CollisionComponent}.
     */
    public SplitterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager) {
        super(ComponentTypes.SPLITTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getParent().isDestroyed()) {
            this.tryToSplitParent();
        }
    }

    /**
     * Tries to add two new {@link Ball}s instance inside the
     * {@link GameObjectManager}.
     */
    public void tryToSplitParent() {
        final Ball ball = (Ball) this.getParent();

        ball.getBallType().getChild()
            .ifPresent(c -> this.gameObjectManager
                .addGameObjects(this.generateBalls(c, ball.getLife() / 2, Math.abs(ball.getVelocity().getX())))
        );
    }

    private List<GameObject> generateBalls(final BallTypes type, final int life, final double xVelocity) {
        return ImmutableList.of(
                GameObjectFactory.createBall(type, life, this.getParent().getPosition(),
                        Vector2D.create(-xVelocity, GRAVITY_Y / 2), collisionManager, gameObjectManager),
                GameObjectFactory.createBall(type, life, this.getParent().getPosition(),
                        Vector2D.create(-xVelocity, GRAVITY_Y / 2), collisionManager, gameObjectManager)
               );
    }
}
