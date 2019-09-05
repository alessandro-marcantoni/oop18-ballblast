package ballblast.model.powerups;

import ballblast.model.commons.Constants;
import ballblast.model.components.ComponentTypes;
import ballblast.model.components.ShooterComponent;
import ballblast.model.gameobjects.AbstractGameObject;

/**
 * The class representing the double fire {@link Power}.
 */
public final class DoubleFirePower extends AbstractPower {

    private static final double DOUBLE_SHOT_INTERVAL = 0.055;

    private DoubleFirePower() {
        super(PowerTypes.DOUBLEFIRE);
    }

    @Override
    protected void performPower() {
        this.setShotInterval(DOUBLE_SHOT_INTERVAL);
    }

    @Override
    protected void stopPerforming() {
        this.setShotInterval(Constants.DEFAULT_SHOT_INTERVAL);
    }

    private void setShotInterval(final double shotInterval) {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.SHOOTER))
        .findFirst()
        .ifPresent(c -> ((ShooterComponent) c).setShotInterval(shotInterval));
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<DoubleFirePower, Builder> {
        @Override
        public final DoubleFirePower build() {
            return this.getGameObject();
        }

        @Override
        protected final DoubleFirePower initGameObject() {
            return new DoubleFirePower();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }

}
