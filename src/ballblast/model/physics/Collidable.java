package ballblast.model.physics;

import java.util.Optional;

import org.locationtech.jts.geom.Geometry;

import ballblast.model.gameobjects.GameObject;

/**
 * Represents an object that can collide with other collidable objects.
 */
public interface Collidable {

    /**
     * Returns the bound shape of the parent object.
     * @return
     *      the geometric limits of the {@link GameObject}.
     */
    Geometry generateShape();

    /**
     * Returns the {@link CollisionTag} for this collidable.
     * @return
     *      the {@link CollisionTag}.
     */
    CollisionTag getCollisionTag();

    /**
     * Returns the {@link GameObject} where this {@link Collidable} is attached to, if present.
     * @return
     *      an {@link Optional} {@link GameObject}, empty if the component has no parent.
     */
    Optional<GameObject> getAttachedGameObject();

    /**
     * Notify the component parent that it is colliding with another object.
     * @param collision
     *      the {@link Collision} data for this collision.
     */
    void notifyCollision(Collision collision);

}
