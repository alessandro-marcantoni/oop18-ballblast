package ballblast.model.components;

import org.locationtech.jts.math.Vector2D;

/**
 * Adds the ability to move and update the position of a {@link GameObject} based on gravity.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D STANDARD_GRAVITY = Vector2D.create(0, -100);

    /**
     * Creates a new GravityComponent instance.
     */
    public GravityComponent() {
        super(ComponentTypes.GRAVITY);
    }

    @Override
    public void update(final double elapsed) {

    }

}
