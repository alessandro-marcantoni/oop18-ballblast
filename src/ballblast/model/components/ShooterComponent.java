package ballblast.model.components;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;

/**
 * Represents the {@link Component} which allows a {@link GameObject} to shot.
 *
 */
public class ShooterComponent extends AbstractComponent {
    private static final Vector2D BULLET_VELOCITY = Vector2D.create(0, -70);
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private boolean isShooting;

    /**
     * Creates a {@link ShooterComponent} instance.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Bullet}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link Bullet}'s {@link CollisionComponent}.
     */
    public ShooterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager) {
        super(ComponentTypes.SHOOTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.isShooting = false;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled() && this.isShooting) {
            this.shoot(this.spawnBullet());
        }
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
    }

    private GameObject spawnBullet() {
        return GameObjectFactory.createBullet(this.getParent().getPosition(), BULLET_VELOCITY, collisionManager);
    }

}
