package ballblast.model.components;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.data.GameDataManager;
import ballblast.model.gameobjects.Bullet;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;

/**
 * Adds the shotting ability to a {@link GameObject}.
 */
public class ShooterComponent extends AbstractComponent {
    private static final Vector2D BULLET_VELOCITY = Vector2D.create(0, -50);
    private static final double SHOT_INTERVAL = 0.15;

    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final GameDataManager gameDataManager;
    private boolean isShooting;
    private double currentShotInterval;

    /**
     * Class constructor.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Bullet}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link Bullet}'s {@link CollisionComponent}.
     * @param gameDataManager   the {@link GameDataManager} used to increment the
     *                          spawned bullets counter.
     */
    public ShooterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager,
            final GameDataManager gameDataManager) {
        super(ComponentTypes.SHOOTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.gameDataManager = gameDataManager;
        this.isShooting = false;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled() && this.isShooting && this.currentShotInterval <= 0) {
            this.shoot(this.spawnBullet());
            this.currentShotInterval = SHOT_INTERVAL;
        }
        this.currentShotInterval -= elapsed;
    }

    /**
     * Enables the component to fire.
     */
    public void startShooting() {
        this.isShooting = true;
    }

    /**
     * Disables the component to fire.
     */
    public void stopShooting() {
        this.isShooting = false;
    }

    private void shoot(final GameObject bullet) {
        bullet.getComponents().forEach(Component::enable);
        this.gameObjectManager.addGameObjects(ImmutableList.of(bullet));
        this.gameDataManager.incrementSpawnedBullets();
    }

    private GameObject spawnBullet() {
        return GameObjectFactory.createBullet(this.getSpawnPosition(), BULLET_VELOCITY, collisionManager);
    }

    private Coordinate getSpawnPosition() {
        final Coordinate parentPos = this.getParent().getPosition();
        return new Coordinate(parentPos.getX() + this.getParent().getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                parentPos.getY() - Bullet.DEFAULT_HEIGHT);
    }

}
