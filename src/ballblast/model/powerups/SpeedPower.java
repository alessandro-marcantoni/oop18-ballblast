package ballblast.model.powerups;

import ballblast.model.commons.Constants;
import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.Player;

/**
 * The class representing the speed {@Link Power}.
 */
public final class SpeedPower extends AbstractPower {

    private static final double ENHANCED_SPEED = 90;

    private SpeedPower() {
        super(PowerTypes.SPEED);
    }

    @Override
    protected void performPower() {
        this.setSpeed(ENHANCED_SPEED);
    }

    @Override
    protected void stopPerforming() {
        this.setSpeed(Constants.PLAYER_SPEED);
    }

    private void setSpeed(final double speed) {
        ((Player) this.getPlayer()).setSpeed(speed);
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
