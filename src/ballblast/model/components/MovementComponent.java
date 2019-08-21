package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Adds the ability to move and updates the position of a {@link GameObject} based on his velocity.
 */
public class MovementComponent extends AbstractComponent {
    /**
     * Creates a {@link MovementComponent} instance.
     */
    public MovementComponent() {
        super(ComponentTypes.MOVEMENT);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            //this.translate(this.getParent().getVelocity().multiply(elapsed), this.getParent().getPosition());
            this.updatePosition(elapsed);
        }
    }

    private void updatePosition(final double elapsed) {
        final GameObject parent = this.getParent();
        parent.getVelocity().multiply(elapsed);
        parent.setPosition(parent.getVelocity().translate(parent.getPosition()));
    }
    /*private void translate(final Vector2D velocity, final Coordinate position) {
        this.getParent().setPosition(new Coordinate(
                position.getX() + velocity.getX(), position.getY() + velocity.getY()));
    }*/

}
