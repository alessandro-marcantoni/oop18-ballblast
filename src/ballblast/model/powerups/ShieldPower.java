package ballblast.model.powerups;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.ComponentTypes;

/**
 * The class representing the shield {@Link Power}.
 */
public class ShieldPower extends AbstractPower {

    /**
     * Create a new instance of shield {@Link Power}.
     */
    public ShieldPower() {
        super(PowerTypes.SHIELD);
    }

    @Override
    public final void performPower() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
        .findFirst()
        .ifPresent(c -> ((CollisionComponent) c).disable());
    }

    @Override
    protected final void stopPerforming() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
        .findFirst()
        .ifPresent(c -> ((CollisionComponent) c).enable());
    }

    /**
     * Concrete implementation of {@link AbstractPower.Builder}.
     */
    public static class Builder extends AbstractPower.Builder {

        @Override
        protected final AbstractPower initGameObject() {
            return new ShieldPower();
        }

    }

}
