package ballblast.model.levels;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.commons.Constants;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * Represents a decorator for levels which add the player object and ends when
 * the player is dead.
 */
public class SinglePlayerDecorator extends LevelDecorator {
    private final GameObject player;

    /**
     * Class constructor.
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
        if (this.getGameStatus() == GameStatus.RUNNING) {
            super.update(elapsed);
            this.checkGameOver();
        }
    }

    private void addPlayer(final GameObject player) {
        this.getGameObjectManager().addGameObjects(ImmutableList.of(player));
    }

    private GameObject createPlayer() {
        return GameObjectFactory.createPlayer(
                this.getGameObjectManager(), this.getInputManager(), PlayerTags.FIRST, 
                this.getCollisionManager(), Vector2D.create(0, 0), Constants.PLAYER_POSITION, 
                this.getGameDataManager());
    }

    private void checkGameOver() {
        if (this.player.isDestroyed()) {
            this.setGameStatus(GameStatus.OVER);
        }
    }
}
