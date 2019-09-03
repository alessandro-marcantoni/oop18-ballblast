package ballblast.model.powerups;

import ballblast.model.commons.Constants;
import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.Player;

/**
 * The class representing the speed {@Link Power}.
 */
public final class SpeedPower extends AbstractPower {

    private static final int SPEED = 90;

    private SpeedPower() {
        super(PowerTypes.SPEED);
    }

    @Override
    public void performPower() {
        ((Player) this.getPlayer()).setSpeed(SPEED);
    }

    @Override
    protected void stopPerforming() {
        ((Player) this.getPlayer()).setSpeed(Constants.PLAYER_SPEED);
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<SpeedPower, Builder> {
        @Override
        public final SpeedPower build() {
            return this.getGameObject();
        }
 
        @Override
        protected final SpeedPower initGameObject() {
            return new SpeedPower();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }

}
