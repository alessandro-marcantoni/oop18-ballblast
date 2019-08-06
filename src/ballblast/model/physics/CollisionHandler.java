package ballblast.model.physics;

import ballblast.model.gameobjects.GameObject;

/**
 * The interface to select the correct behavior for the collision events.
 * @param <G>
 *      the {@link GameObject} that implements the right behavior after a collision.
 */
public interface CollisionHandler<G extends GameObject> {

    /**
     * The functional method to execute the correct behavior.
     * @param coll
     *       the {@link Collidable} component that loses out the collision.
     * @param obj
     *       the {@link GameObject} that collides.
     */
    void execute(Collidable coll, G obj);

}
