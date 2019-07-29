package ballblast.model;

import java.util.Optional;

import ballblast.model.levels.BasicLevel;
import ballblast.model.levels.Level;

/**
 * This is the concrete implementation of model interface.
 */
public final class ModelImpl implements Model {
    private Level currentLevel;

    @Override
    public Optional<Level> getCurrentLevel() {
        return Optional.of(currentLevel);
    }

    @Override
    public void startSurvival() {
        this.currentLevel = new BasicLevel();
    }

}
