package ballblast.model.physics.handlers;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Represents the handler for the behavior of a {@link Player} after a
 * collision.
 */
public class PlayerCollisionHandler implements CollisionHandler {

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
            obj.setPosition(this.getPrevPosition(obj, coll.getAttachedGameObject().get()));
            break;
        case POWERUP:
            obj.toString();
            break;
        default:
            break;
        }
    }

    private Coordinate getPrevPosition(final GameObject player, final GameObject wall) {
        final Coordinate playerPos = player.getPosition();
        final Coordinate wallPos = wall.getPosition();

        if (wallPos.getX() < playerPos.getX()) {
            final double penetration = playerPos.distance(translateLeftWall(wall, player));
            return new Coordinate(playerPos.getX() + penetration, playerPos.getY()); // Left Wall
        }
        final double penetration = playerPos.distance(translateRightWall(wall, player));
        return new Coordinate(playerPos.getX() - penetration, playerPos.getY()); // Rigth Wall
    }

    private Coordinate translateLeftWall(final GameObject wall, final GameObject player) {
        return new Coordinate(wall.getPosition().getX(),
                wall.getHeight() - player.getHeight() - wall.getWidth());
    }

    private Coordinate translateRightWall(final GameObject wall, final GameObject player) {
        return new Coordinate(wall.getPosition().getX() + wall.getWidth() - player.getWidth(),
                wall.getHeight() - player.getHeight() - wall.getWidth());
    }
}
