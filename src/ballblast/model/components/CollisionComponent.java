package ballblast.model.components;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import com.google.common.base.MoreObjects;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.Collision;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the collision component of a {@link GameObject}.
 */
public class CollisionComponent extends AbstractComponent implements Collidable {

    private final CollisionTag collisionTag;
    private final CollisionManager manager;

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
    }

    @Override
    public final Geometry generateShape() {
        final Coordinate pos = this.getParent().getPosition();
        final GameObject parent = this.getParent();
        return new GeometryFactory().toGeometry(new Envelope(pos.getX(), pos.getX() + parent.getWidth(), pos.getY(), pos.getY() + parent.getHeight()))
                .getEnvelope();
    }

    @Override
    public final CollisionTag getCollisionTag() {
        return this.collisionTag;
    }

    @Override
    public final Optional<GameObject> getAttachedGameObject() {
        return Optional.of(this.getParent());
    }

    @Override
    public final void enable() {
        super.enable();
        this.manager.addCollidable(this);
    }

    @Override
    public final void disable() {
        super.disable();
        this.manager.removeCollidable(this);
    }

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
        if (this.getAttachedGameObject().get().equals(coll.getObj().getAttachedGameObject().get())) {
            this.getParent().handleCollision(coll.getOther());
        }
    }
}
