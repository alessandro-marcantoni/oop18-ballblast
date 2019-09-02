package ballblast.model.physics.handlers;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.powerups.Power;

/**
 * Represents the handler for the behavior of a {@link Power} after a collision.
 */
public class PowerCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        switch (coll.getCollisionTag()) {
        case PLAYER:
            obj.getComponents().forEach(Component::disable);
            ((Power) obj).activate(coll.getAttachedGameObject());
            break;
        case WALL:
            final GameObject floor = coll.getAttachedGameObject();
            this.stopFloor(floor, obj);
            break;
        default:
            break;
        }
    }

    private void stopFloor(final GameObject floor, final GameObject obj) {
        if (Boundaries.isFloor(floor.getPosition())) {
            obj.setPosition(new Coordinate(obj.getPosition().getX(), floor.getPosition().getY() - obj.getHeight()));
        }
    }

}
