package ballblast.model.components;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import com.google.common.base.MoreObjects;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.Collision;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the collision component of a {@link GameObject}.
 */
public class CollisionComponent extends AbstractComponent implements Collidable {

    private final CollisionTag collisionTag;
    private CollisionManager manager;
    /**
     * The constructor for the CollisionComponent.
     * @param man
     *       the collision manager.
     * @param tag
     *       the type of the collision component, chosen by the game object type.
     */
    public CollisionComponent(final CollisionManager man, final CollisionTag tag) {
        super(ComponentTypes.COLLISION);
        this.collisionTag = tag;
        this.manager = man;
        this.manager.addCollidable(this);
    }

    @Override
    public final Geometry generateShape() {
        final Coordinate coordinateX = this.generateCoordinateX(this.getParent());
        final Coordinate coordinateY = this.generateCoordinateY(this.getParent());
        return new GeometryFactory().toGeometry(new Envelope(coordinateX.getX(), coordinateX.getY(), coordinateY.getX(), coordinateY.getY()))
                                    .getEnvelope();
    }

    private Coordinate generateCoordinateX(final GameObject obj) {
        return new Coordinate(obj.getPosition().getX() - obj.getWidth() / 2, obj.getPosition().getX() + obj.getWidth() / 2);
    }

    private Coordinate generateCoordinateY(final GameObject obj) {
        return new Coordinate(obj.getPosition().getY() - obj.getHeight() / 2, obj.getPosition().getY() + obj.getHeight() / 2);
    }

    @Override
    public final CollisionTag getCollisionTag() {
        return this.collisionTag;
    }

    @Override
    public final Optional<GameObject> getAttachedGameObject() {
        return Optional.of(this.getParent());
    }

    // da capire come e dove usarla
//    private void removeComponent() {
//        if (this.getAttachedGameObject().get().isDestroyed()) {
//            manager.removeCollidable(this);
//        }
//    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("AttachedTo", this.getCollisionTag())
                          .toString();
    }

    @Override
    public void update(final double elapsed) {

    }

    @Override
    public final void notifyCollision(final Collision coll) {
        // TODO
        /*
        Collidable col1 = coll.getObj();
        Collidable col2 = coll.getOther();

        CollisionHandler behavior = () -> Optional.empty();

        if ((col1.getCollisionTag() == CollisionTag.BALL && col2.getCollisionTag() == CollisionTag.PLAYER)
         || (col2.getCollisionTag() == CollisionTag.BALL && col1.getCollisionTag() == CollisionTag.PLAYER)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides with " + col2.getCollisionTag().toString() + "\n");

                };
        }
        /*
        if (col1.getCollisionTag() == CollisionTag.BALL
                && col2.getCollisionTag() == CollisionTag.BULLET) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag() == CollisionTag.POWERUP
                && col2.getCollisionTag() == CollisionTag.PLAYER) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag() == CollisionTag.WALL
                && col2.getCollisionTag() == CollisionTag.PLAYER) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag() == CollisionTag.BALL
                && col2.getCollisionTag() == CollisionTag.WALL) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        behavior.execute();
        */

    }
}
