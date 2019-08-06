package ballblast.model.physics;

import java.util.Optional;

import org.locationtech.jts.geom.Geometry;

import ballblast.model.gameobjects.GameObject;

/**
 * Represents an object that can collide with other collidable objects.
 */
public interface Collidable {
    /**
     * Returns the shape of the object without any transformation.
     * @return
     *      the shape of the object.
     */
    Geometry generateShape();
    /**
     * Returns the {@link CollisionTag} for this collidable.
     * @return
     *      the {@link CollisionTag}.
     */
    CollisionTag getCollisionTag();
    /**
     * Returns an optional representing the {@link GameObject} where this {@link Collidable} is attached to.
     * @return
     *      the optional {@link GameObject}.
     */
    Optional<GameObject> getAttachedGameObject();
    /**
     * Notify this object that it is colliding with another object.
     * @param collision
     *      the {@link Collision} data for this collision.
     */
    void notifyCollision(Collision collision);

}
