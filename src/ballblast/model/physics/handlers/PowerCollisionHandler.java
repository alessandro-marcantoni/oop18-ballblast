package ballblast.model.physics.handlers;

import ballblast.model.components.Component;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the handler for the behavior of a {@link Power} after a collision.
 */
public class PowerCollisionHandler implements CollisionHandler {

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        if (coll.getCollisionTag().equals(CollisionTag.PLAYER)) {
            obj.getComponents().forEach(Component::disable);
        }
    }

}
