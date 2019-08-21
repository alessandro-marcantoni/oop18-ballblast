package ballblast.model.physics.handlers;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of the {@link Player} after a collision.
 */
public class PlayerCollisionHandler implements CollisionHandler {

    private Optional<Coordinate> prevPosition = Optional.empty();

    @Override
    public final void execute(final Collidable coll, final GameObject obj) {
        // obj is a Player object.
        switch (coll.getCollisionTag()) {
            case BALL:
                // Destroy the Player and finish the game session.
                obj.destroy();
                // TODO metodo endGameSession()
                break;
            case WALL:
                prevPosition.ifPresent(pos -> obj.setPosition(pos));
                break;
            case POWERUP:
                obj.toString();
                break;
            default:
                break;
        }
        this.prevPosition = Optional.of(obj.getPosition());
    }
}
