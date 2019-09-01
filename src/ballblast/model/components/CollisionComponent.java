package ballblast.model.components;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.GeometricShapeFactory;

import com.google.common.base.MoreObjects;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
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
     * Class constructor.
     * 
     * @param man the {@link CollisionManager}.
     * @param tag the type of the collision component, chosen by the game object
     *            type.
     */
    public CollisionComponent(final CollisionManager man, final CollisionTag tag) {
        super(ComponentTypes.COLLISION);
        this.collisionTag = tag;
        this.manager = man;
    }

    @Override
    public final Geometry generateShape() {
        final GameObject parent = this.getParent();
        final Coordinate pos = parent.getPosition();

        final GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setBase(new Coordinate(pos.getX(), pos.getY()));
        shapeFactory.setHeight(parent.getHeight());
        shapeFactory.setWidth(parent.getWidth());
        if (parent.getType() == GameObjectTypes.BALL) {
            return shapeFactory.createCircle();
        } else {
            return shapeFactory.createRectangle();
        }
    }

    @Override
    public final CollisionTag getCollisionTag() {
        return this.collisionTag;
    }

    @Override
    public final GameObject getAttachedGameObject() {
        return this.getParent();
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
        return MoreObjects.toStringHelper(this).add("AttachedTo", this.getCollisionTag()).toString();
    }

    @Override
    public void update(final double elapsed) {

    }

    @Override
    public final void notifyCollision(final Collision coll) {
        if (this.getAttachedGameObject().equals(coll.getObj().getAttachedGameObject())) {
            this.getParent().handleCollision(coll.getOther());
        }
    }

}
