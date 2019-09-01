package ballblast.model.powerups;

import ballblast.model.components.ComponentTypes;
import ballblast.model.components.ShooterComponent;

/**
 * The class representing the double shot {@Link Power}.
 */
public class DoubleFirePower extends AbstractPower {

    private static final double DOUBLE_SHOT_INTERVAL = 0.075;

    /**
     * Create a new instance of double shot {@Link Power}.
     */
    public DoubleFirePower() {
        super(PowerTypes.DOUBLEFIRE);
    }

    @Override
    public final void performPower() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.SHOOTER))
        .findFirst()
        .ifPresent(c -> ((ShooterComponent) c).setShotInterval(DOUBLE_SHOT_INTERVAL));
    }

    @Override
    public final void stopPerforming() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.SHOOTER))
        .findFirst()
        .ifPresent(c -> ((ShooterComponent) c).setDefaultShotInterval());
    }

}
