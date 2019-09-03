package ballblast.model.powerups;

import ballblast.model.commons.Constants;
import ballblast.model.components.ComponentTypes;
import ballblast.model.components.ShooterComponent;
import ballblast.model.gameobjects.AbstractGameObject;

/**
 * The class representing the double shot {@Link Power}.
 */
public final class DoubleFirePower extends AbstractPower {

    private static final double DOUBLE_SHOT_INTERVAL = 0.055;

    private DoubleFirePower() {
        super(PowerTypes.DOUBLEFIRE);
    }

    @Override
    public void performPower() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.SHOOTER))
        .findFirst()
        .ifPresent(c -> ((ShooterComponent) c).setShotInterval(DOUBLE_SHOT_INTERVAL));
    }

    @Override
    public void stopPerforming() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.SHOOTER))
        .findFirst()
        .ifPresent(c -> ((ShooterComponent) c).setShotInterval(Constants.DEFAULT_SHOT_INTERVAL));
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
