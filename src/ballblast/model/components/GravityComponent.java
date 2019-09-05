package ballblast.model.components;

import org.locationtech.jts.math.Vector2D;

/**
 * Adds the ability to move and update the position of a {@link GameObject}
 * thanks to gravity.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D STANDARD_GRAVITY = Vector2D.create(0, 100);
    private static final Vector2D UPWARDS_SHIFT = Vector2D.create(0, -2);

    private Vector2D gravity;

    /**
     * Creates a new GravityComponent instance.
     */
    public GravityComponent() {
        super(ComponentTypes.GRAVITY);
        this.gravity = STANDARD_GRAVITY;
    }

    /**
     * Creates a new GravityComponent instance with specified gravity acceleration.
     * 
     * @param gravity the gravity acceleration.
     */
    public GravityComponent(final Vector2D gravity) {
        // May be used to make the game more difficult by having balls with different
        // gravity settings.
        super(ComponentTypes.GRAVITY);
        this.gravity = gravity;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.gravity = this.isGoingUpwards() ? this.gravity.add(UPWARDS_SHIFT) : this.gravity;
            final Vector2D dV = gravity.multiply(elapsed);
            this.getParent().setVelocity(this.getParent().getVelocity().add(dV));
        }
    }

    private boolean isGoingUpwards() {
        return this.getParent().getVelocity().getY() <= 0;
    }

    /**
     * Gets the gravity value.
     * 
     * @return the gravity value.
     */
    public Vector2D getGravity() {
        return this.gravity;
    }

    /**
     * Sets a new gravity value.
     * 
     * @param gravity the gravity vector.
     */
    public void setGravity(final Vector2D gravity) {
        // May be used to slow down the balls with a powerup.
        this.gravity = gravity;
    }

}
