package ballblast.model.powerups;

import ballblast.model.gameobjects.Player;

/**
 * The class representing the speed {@Link Power}.
 */
public class SpeedPower extends AbstractPower {

    private static final int SPEED = 90;

    /**
     * Creates a new instance of speed {@Link Power}.
     */
    public SpeedPower() {
        super(PowerTypes.SPEED);
    }

    @Override
    public final void performPower() {
        ((Player) this.getPlayer()).setSpeed(SPEED);
    }

    @Override
    protected final void stopPerforming() {
        ((Player) this.getPlayer()).setSpeed(Player.DEFAULT_SPEED);
    }

    /**
     * Concrete implementation of {@link AbstractPower.Builder}.
     */
    public static class Builder extends AbstractPower.Builder {

        @Override
        protected final AbstractPower initGameObject() {
            return new SpeedPower();
        }

    }

}
