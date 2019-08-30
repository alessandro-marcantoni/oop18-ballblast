package ballblast.model.physics;

/**
 * Represents a simple collision between two {@link GameObject}s.
 */
public class Collision {

    private final Collidable obj;
    private final Collidable other;

    /**
     * The class constructor.
     * @param object
     *       the {@link CollisionComponent} of the game object that collides.
     * @param other
     *       the {@link CollisionComponent} of the game object that loses out the collision.
     */
    public Collision(final Collidable object, final Collidable other) {
        this.obj = object;
        this.other = other;
    }

    /**
     * The getter for the {@link GameObject} that collides.
     * @return
     *       the {@link Collidable} that collides.
     */
    public Collidable getObj() {
        return this.obj;
    }

    /**
     * The getter for the {@link GameObject} that loses out the {@link Collision}.
     * @return
     *       the {@link Collidable} that lose out the collision.
     */
    public Collidable getOther() {
        return this.other;
    }

}
