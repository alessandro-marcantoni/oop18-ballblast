package ballblast.model.physics.handlers;

import java.util.Map;
import java.util.function.BiConsumer;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.collect.ImmutableMap;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.levels.Boundaries;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;
import ballblast.model.powerups.Power;

/**
 * Represents the handler for the behavior of a {@link Power} after a collision.
 */
public class PowerCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> POWER_MAP;

    static {
        POWER_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>builder()
                .put(CollisionTag.PLAYER, (coll, obj) -> {
                    obj.getComponents().forEach(Component::disable);
                    ((Power) obj).activate(coll.getAttachedGameObject());
                })
                .put(CollisionTag.WALL, (coll, obj) -> {
                    final GameObject floor = coll.getAttachedGameObject();
                    stopFloor(floor, obj);
                    obj.getComponents().stream().filter(c -> c.getType().equals(ComponentTypes.GRAVITY)
                            || c.getType().equals(ComponentTypes.MOVEMENT)).forEach(Component::disable);
                })
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Power object.
        if (POWER_MAP.containsKey(coll.getCollisionTag())) {
            POWER_MAP.get(coll.getCollisionTag()).accept(coll, obj);
        }
    }

    private static void stopFloor(final GameObject floor, final GameObject obj) {
        if (Boundaries.isFloor(floor.getPosition())) {
            obj.setPosition(new Coordinate(obj.getPosition().getX(), floor.getPosition().getY() - obj.getHeight()));
        }
    }

}
