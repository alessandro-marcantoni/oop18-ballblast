package ballblast.model.physics;

/**
 * Represents a simple collision between two {@link GameObject}.
 */
public class Collision {

    private Collidable obj;
    private Collidable other;

    /**
     * The costructor for the collision.
     * @param object
     *       the {@link Collidable} of the game object that collides.
     * @param other
     *       the component of the game object that lose out the collision.
     */
    public Collision(final Collidable object, final Collidable other) {
        this.obj = object;
        this.other = other;
    }

    /**
     * The getter for the object that collides.
     * @return
     *       the {@link Collidable} object that collides.
     */
    public Collidable getObj() {
        return this.obj;
    }

    /**
     * The getter for the other game object that collides.
     * @return
     *       the {@link Collidable} that lose out the collision.
     */
    public Collidable getOther() {
        return this.other;
    }
}
