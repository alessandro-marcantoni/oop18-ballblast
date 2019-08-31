package ballblast.model.components;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.data.GameDataManager;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;

/**
 * Adds the ability to a {@link GameObject} to be split when destroyed.
 */
public class SplitterComponent extends AbstractComponent {
    private static final double GRAVITY_Y = -80;
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final GameDataManager gameDataManager;

    /**
     * Class constructor.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Ball}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link Ball}'s {@link CollisionComponent}.
     * @param gameDataManager   the {@link GameDataManager} used to increment the
     *                          destroyed balls counter.
     */
    public SplitterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager,
            final GameDataManager gameDataManager) {
        super(ComponentTypes.SPLITTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.gameDataManager = gameDataManager;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getParent().isDestroyed()) {
            this.tryToSplitParent();
            this.gameDataManager.incrementDestroyedBalls();
        }
    }

    /**
     * Tries to add two new instance of {@link Ball} inside the
     * {@link GameObjectManager}.
     */
    public void tryToSplitParent() {
        ((Ball) this.getParent()).getBallType().getChild().ifPresent(this::addParentChilds);
    }

    private GameObject generateChildBall(final BallTypes type, final int life, final double xVelocity,
            final Coordinate position) {
        final GameObject ball = GameObjectFactory.createBall(type, life, position,
                Vector2D.create(xVelocity, GRAVITY_Y / 2), this.collisionManager, this.gameObjectManager,
                this.gameDataManager);
        ball.getComponents().forEach(Component::enable);
        return ball;
    }

    private void addParentChilds(final BallTypes type) {
        this.gameObjectManager.addGameObjects(ImmutableList.of(
                this.generateChildBall(type, this.getChildLife(),  this.getChildXvelocity(), this.getChildPosition()),
                this.generateChildBall(type, this.getChildLife(), -this.getChildXvelocity(), this.getChildPosition()))
        );
    }

    private double getChildXvelocity() {
        return Math.abs(this.getParent().getVelocity().getX());
    }

    private int getChildLife() {
        return ((Ball) this.getParent()).getInitialLife() / 2;
    }

    private Coordinate getChildPosition() {
        return this.getParent().getPosition();
    }
}
