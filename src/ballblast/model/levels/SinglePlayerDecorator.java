package ballblast.model.levels;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.Model;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * Represents a decorator for levels which add the player object and ends when the player is dead.
 */
public class SinglePlayerDecorator extends LevelDecorator {
    private static final Coordinate INITIAL_PLAYER_POSITION = calculatePosition();
    private static final Vector2D INITIAL_PLAYER_VELOCITY = new Vector2D(0, 0);
    private final GameObject player;

    /**
     * Creates a {@link SinglePlayerDecorator} instance.
     * 
     * @param level the {@link Level} used like decoration.
     */
    public SinglePlayerDecorator(final Level level) {
        super(level);
        this.player = this.createPlayer();
        this.addPlayer(this.player);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getGameStatus() != GameStatus.OVER) {
            super.update(elapsed);
            this.checkGameOver();
        }
    }

    private void addPlayer(final GameObject player) {
        this.getGameObjectManager().addGameObjects(ImmutableList.of(player));
    }

    private GameObject createPlayer() {
        return GameObjectFactory.createPlayer(this.getGameObjectManager(), this.getInputManager(), PlayerTags.FIRST,
                this.getCollisionManager(), INITIAL_PLAYER_VELOCITY, INITIAL_PLAYER_POSITION);
    }

    private void checkGameOver() {
        if (this.player.isDestroyed()) {
            this.setGameStatus(GameStatus.OVER);
        }
    }

    private static Coordinate calculatePosition() {
        final double playerOffSet = 9;
        final double x = Model.WORLD_WIDTH / 2;
        final double y = Model.WORLD_HEIGHT - Model.WALL_OFFSET * 2 - playerOffSet;
        return new Coordinate(x, y);
    }

}
