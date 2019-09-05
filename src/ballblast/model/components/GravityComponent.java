package ballblast.model.components;

import org.locationtech.jts.math.Vector2D;

/**
 * Adds the ability to move and update the position of a {@link GameObject}
 * based on gravity.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D STANDARD_GRAVITY = Vector2D.create(0, 100);
    private static final Vector2D UPWARDS_GRAVITY = Vector2D.create(0, 98.5);

    private Vector2D gravity;

    /**
     * Creates a new GravityComponent instance.
     */
    public GravityComponent() {
        super(ComponentTypes.GRAVITY);
        this.gravity = STANDARD_GRAVITY;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.gravity = this.isGoingUpwards() ? UPWARDS_GRAVITY : STANDARD_GRAVITY;
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

}
