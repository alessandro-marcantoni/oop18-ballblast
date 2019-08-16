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
    /**
     * Creates a {@link ShooterComponent} instance.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Bullet}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link CollisionComponent}.
     */
    public ShooterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager) {
        super(ComponentTypes.SHOOTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.shoot();
            this.disable();
        }
    }

    /**
     * Creates and adds a {@link Bullet} to the {@link GameObjectManager}.
     */
    private void shoot() {
        final GameObject bullet = GameObjectFactory.createBullet(this.getParent().getPosition(), BULLET_VELOCITY,
                collisionManager);
        bullet.getComponents().forEach(c -> c.enable());
        this.gameObjectManager.addGameObjects(ImmutableList.of(bullet));
    }
}
