package ballblast.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.commons.events.EventTypes;
import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;
import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.GameStatus;
import ballblast.model.levels.Level;
import ballblast.model.levels.SinglePlayerDecorator;
import ballblast.model.levels.SurvivalLevelDecorator;

/**
 * This is the concrete implementation of the {@link Model} interface.
 */
public final class ModelImpl implements Model {
    private Level currentLevel;

    @Override
    public void startSurvival() {
        this.currentLevel = new SurvivalLevelDecorator(new SinglePlayerDecorator(new BasicLevel()));
        this.currentLevel.start();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return this.currentLevel.getGameObjectManager().getGameObjects();
    }

    @Override
    public void resolveInputs(final PlayerTags tag, final List<InputTypes> inputs) {
        this.currentLevel.getInputManager().processInputs(tag, inputs);
    }

    @Override
    public void update(final double elapsed) {
        this.currentLevel.update(elapsed);
    }

    @Override
    public GameStatus getGameStatus() {
        return this.currentLevel.getGameStatus();
    }

    @Override
    public GameData getGameData() {
        return this.currentLevel.getGameDataManager().getGameData();
    }

    @Override
    public List<EventTypes> getGameEvents() {
        final List<EventTypes> copy = ImmutableList.copyOf(this.currentLevel.getGameEvents());
        this.currentLevel.getGameEvents().clear();
        return copy;
    }
}
