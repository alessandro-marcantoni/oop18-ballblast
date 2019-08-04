package ballblast.model.components;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

import com.google.common.base.MoreObjects;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Behavior;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.Collision;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.utils.Point2D;

/**
 * Represents the collision component of a {@link GameObject}.
 */
public class CollisionComponent extends AbstractComponent implements Collidable {

    private CollisionTag collisionTag;
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
    public final Envelope generateShape() {
        Coordinate coordinateX = this.generateCoordinateX(this.getParent().getPosition(), this.getParent());
        Coordinate coordinateY = this.generateCoordinateY(this.getParent().getPosition(), this.getParent());
        return new Envelope(coordinateX.x, coordinateX.y, coordinateY.x, coordinateY.y);
    }

    private Coordinate generateCoordinateY(final Point2D pos, final GameObject obj) {
        return new Coordinate(pos.getX() - obj.getWidth() / 2, pos.getX() + obj.getWidth() / 2);
    }

    private Coordinate generateCoordinateX(final Point2D pos, final GameObject obj) {
        return new Coordinate(pos.getY() - obj.getHeight() / 2, pos.getY() + obj.getHeight() / 2);
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
    public final void notifyCollision(final Collision coll) {

        Collidable col1 = coll.getObj();
        Collidable col2 = coll.getOther();
        Behavior behavior = () -> Optional.empty();

        if (col1.getCollisionTag().equals(CollisionTag.BALL)
                && col2.getCollisionTag().equals(CollisionTag.PLAYER)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().getX() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().getX() + "\n");
                };
        }
        if (col1.getCollisionTag().equals(CollisionTag.BALL)
                && col2.getCollisionTag().equals(CollisionTag.BULLET)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag().equals(CollisionTag.POWERUP)
                && col2.getCollisionTag().equals(CollisionTag.PLAYER)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag().equals(CollisionTag.WALL)
                && col2.getCollisionTag().equals(CollisionTag.PLAYER)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }
        if (col1.getCollisionTag().equals(CollisionTag.BALL)
                && col2.getCollisionTag().equals(CollisionTag.WALL)) {
                behavior = () -> {
                    System.out.println(col1.getCollisionTag().toString() + " collides in position " + col1.getAttachedGameObject().get().getPosition().toString() + "\n");
                    System.out.println(col2.getCollisionTag().toString() + " collides in position " + col2.getAttachedGameObject().get().getPosition().toString() + "\n");
                };
        }

        behavior.execute();
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("AttachedTo", this.getAttachedGameObject().get())
                          .toString();
    }

    @Override
    public void update(final double elapsed) {

    }
}