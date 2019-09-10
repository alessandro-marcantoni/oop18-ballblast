package ballblast.model.components;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.commons.events.EventTypes;
import ballblast.model.data.GameDataManager;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.physics.CollisionManager;

/**
 * Adds the shotting ability to a {@link GameObject}.
 */
public class ShooterComponent extends AbstractComponent {
    private static final double DEFAULT_SHOT_INTERVAL = 0.15;
    private static final Vector2D BULLET_VELOCITY = Vector2D.create(0, -50);

    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final GameDataManager gameDataManager;
    private final List<EventTypes> events;
    private boolean isShooting;
    private double shotInterval;
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
     * @param events            the game event list.
     */
    public ShooterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager,
            final GameDataManager gameDataManager, final List<EventTypes> events) {
        super(ComponentTypes.SHOOTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.gameDataManager = gameDataManager;
        this.isShooting = false;
        this.shotInterval = DEFAULT_SHOT_INTERVAL;
        this.events = events;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled() && this.isShooting && this.currentShotInterval <= 0) {
            this.shoot(this.spawnBullet());
            this.currentShotInterval = this.shotInterval;
            this.events.add(EventTypes.SHOT);
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

    /**
     * Sets a custom shot interval.
     * 
     * @param interval The custom shot interval.
     */
    public void setShotInterval(final double interval) {
        this.shotInterval = interval;
    }

    /**
     * Gets the current shot interval.
     * 
     * @return The current shot interval.
     */
    public double getShotInterval() {
        return this.shotInterval;
    }

    private void shoot(final GameObject bullet) {
        bullet.getComponents().forEach(Component::enable);
        this.gameObjectManager.addGameObjects(ImmutableList.of(bullet));
        this.gameDataManager.incrementSpawnedBullets();
    }

    private GameObject spawnBullet() {
        return GameObjectHelper.createBullet(this.getSpawnPosition(), BULLET_VELOCITY, collisionManager);
    }

    private Coordinate getSpawnPosition() {
        final double halfBulletWidth = 0.7;
        final double middleY = this.getParent().getPosition().getY() - 1;
        final double middleX = this.getParent().getPosition().getX() + (this.getParent().getWidth() / 2 - halfBulletWidth);
        return new Coordinate(middleX, middleY);
    }

}
