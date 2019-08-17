package ballblast.model.levels;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * 
 */
public class SinglePlayerDecorator extends LevelDecorator {
    private static final Coordinate INITIAL_PLAYER_POSITION = new Coordinate(100, 86);
    private static final Vector2D INITIAL_PLAYER_VELOCITY = new Vector2D(0, 0);

    /**
     * Creates a {@link SinglePlayerDecorator} instance.
     * 
     * @param level the {@link Level} used like decoration.
     */
    public SinglePlayerDecorator(final Level level) {
        super(level);
        this.addPlayer();
    }

    private void addPlayer() {
        final GameObject player = GameObjectFactory.createPlayer(this.getGameObjectManager(), this.getInputManager(),
                PlayerTags.FIRST, this.getCollisionManager(), INITIAL_PLAYER_VELOCITY, INITIAL_PLAYER_POSITION);
        this.getGameObjectManager().addGameObjects(ImmutableList.of(player));
    }

}
