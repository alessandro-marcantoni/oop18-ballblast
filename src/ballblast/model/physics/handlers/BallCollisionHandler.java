package ballblast.model.physics.handlers;

import ballblast.model.levels.Boundaries;

import java.util.Map;
import java.util.function.BiConsumer;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.utilities.Bounce;

/**
 * Represents the handler for the behavior of a {@link Ball} after a collision.
 */
public class BallCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> BALL_MAP;
    private static final int DEC_LIFE = 1;

    static {
        BALL_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>builder()
                .put(CollisionTag.WALL, (coll, obj) -> {
                    final Coordinate boundaryPos = coll.getAttachedGameObject().getPosition();
                    if (Boundaries.isFloor(boundaryPos)) {
                        obj.setPosition(new Coordinate(obj.getPosition().getX(),
                                Boundaries.BOTTOM.getPosition().getY() - obj.getHeight()));
                        Bounce.floorBounce(obj);
                    } else if (Boundaries.isRoof(boundaryPos)) {
                        obj.setPosition(new Coordinate(obj.getPosition().getX(),
                                Boundaries.TOP.getPosition().getY() + Boundaries.TOP.getHeight()));
                        Bounce.floorBounce(obj);
                    } else {
                        Bounce.wallBounce(obj);
                    }
                })
                .put(CollisionTag.BULLET, (coll, obj) -> {
                    decrementLife(obj, DEC_LIFE);
                    if (((Ball) obj).getCurrentLife() <= 0) {
                        obj.destroy();
                    }
                })
                .put(CollisionTag.PLAYER, (coll, obj) -> { })
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Ball object.
        BALL_MAP.get(coll.getCollisionTag()).accept(coll, obj);
    }

    private static void decrementLife(final GameObject ball, final int decrementBy) {
        ((Ball) ball).setCurrentLife(((Ball) ball).getCurrentLife() - decrementBy);
    }
}
