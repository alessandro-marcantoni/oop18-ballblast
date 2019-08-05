package ballblast.model.components;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.physics.CollisionManager;
/**
 * Represents the {@link Component} which allows a {@link GameObject} to shot.
 *
 */
public class ShooterComponent extends AbstractComponent {
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private boolean shootingState;

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
        if (this.shootingState) {
            this.shoot();
            this.shootingState = false;
        }
    }

    /**
     * Sets the {@link ShooterComponent}'s state.
     * 
     * @param shootingState the boolean indicates if the {@link Shooter} has to
     *                      shot.
     */
    public void setShootingState(final boolean shootingState) {
        this.shootingState = shootingState;
    }

    /**
     * Gets the {@link ShooterComponent}'s state.
     * 
     * @return the true if the {@link Shooter} has to shot, false otherwise.
     */
    public boolean getShootingState() {
        return this.shootingState;
    }

    /**
     * Creates and adds a {@link Bullet} to the {@link GameObjectManager}.
     */
    private void shoot() {
        this.gameObjectManager.addGameObjects(
                ImmutableList.of(GameObjectFactory.createBullet(this.getParent().getPosition(), collisionManager)));
    }
}
