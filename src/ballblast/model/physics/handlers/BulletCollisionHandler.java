package ballblast.model.physics.handlers;

import java.util.Map;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableMap;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;
import ballblast.model.physics.CollisionTag;

/**
 * Represents the handler for the behavior of a {@link Bullet} after a
 * collision.
 */
public class BulletCollisionHandler implements CollisionHandler {

    private static final Map<CollisionTag, BiConsumer<Collidable, GameObject>> BULLET_MAP;

    static {
        BULLET_MAP = ImmutableMap.<CollisionTag, BiConsumer<Collidable, GameObject>>builder()
                .put(CollisionTag.BALL, BulletCollisionHandler::bulletCollision)
                .put(CollisionTag.WALL, BulletCollisionHandler::bulletCollision)
                .build();
    }

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Bullet object.
        if (BULLET_MAP.containsKey(coll.getCollisionTag())) {
            BULLET_MAP.get(coll.getCollisionTag()).accept(coll, obj);
        }
    }

    private static void bulletCollision(final Collidable coll, final GameObject obj) {
        obj.destroy();
    }

}
