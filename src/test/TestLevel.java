package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.GameStatus;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;

class TestLevel {
    private static final double SAMPLE_ELAPSED = 0.1;

    @Test
    public void basicLevelTest() {
        final Level level = new BasicLevel();
        assertTrue(level.getGameObjectManager().getGameObjects().isEmpty());
        level.start();
        level.update(SAMPLE_ELAPSED);
        assertFalse(level.getGameObjectManager().getGameObjects().isEmpty());
    }

    @Test
    public void singlePlayerLevelTest() {
        final Level level = new SinglePlayerDecorator(new BasicLevel());
        assertTrue(level.getGameStatus() == GameStatus.PAUSE);
        assertTrue(level.getGameObjectManager().getGameObjects().isEmpty());
        level.start();
        level.update(SAMPLE_ELAPSED);
        assertFalse(level.getGameObjectManager().getGameObjects().isEmpty());
        assertTrue(this.findPlayer(level).isPresent());
        assertTrue(level.getGameStatus() == GameStatus.RUNNING);
        this.findPlayer(level).get().destroy();
        level.update(SAMPLE_ELAPSED);
        assertTrue(level.getGameStatus() == GameStatus.OVER);
    }

    private Optional<GameObject> findPlayer(final Level level) {
        return level.getGameObjectManager().getGameObjects().stream()
                .filter(g -> g.getType() == GameObjectTypes.PLAYER).findFirst();
    }
}