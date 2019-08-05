package ballblast.model.components;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

/**
 * Adds the ability to move and updates the position of a {@link GameObject} based on his velocity.
 */
public class MovementComponent extends AbstractComponent {
    private final Vector2D velocity;
    /**
     * Creates {@link MovementComponent} instance.
     * @param velocity
     *     the {@link GameObject}'s velocity.
     */
    public MovementComponent(final Vector2D velocity) {
        super(ComponentTypes.MOVEMENT);
        this.velocity = velocity;
    }

    @Override
    public final void update(final double elapsed) {
        this.translate(this.velocity.multiply(elapsed), this.getParent().getPosition());
    }

    /**
     * Gets the velocity of the Object.
     * @return
     *      the velocity.
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /*
     * Sets the velocity of the Object.
     * @param velocity
     *      the new velocity of the Object.
     *
    public void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }*/

    private void translate(final Vector2D velocity, final Coordinate position) {
        this.getParent().setPosition(new Coordinate(
                position.getX() + velocity.getX(), position.getY() + velocity.getY()));
    }

}
