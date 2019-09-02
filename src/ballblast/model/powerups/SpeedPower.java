package ballblast.model.powerups;

/**
 * The class representing the speed {@Link Power}.
 */
public class SpeedPower extends AbstractPower {

    /**
     * Creates a new instance of speed {@Link Power}.
     */
    public SpeedPower() {
        super(PowerTypes.SPEED);
    }

    @Override
    public void performPower() {

    }

    @Override
    protected void stopPerforming() {

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
