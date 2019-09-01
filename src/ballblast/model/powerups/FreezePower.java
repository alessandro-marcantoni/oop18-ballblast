package ballblast.model.powerups;

/**
 * The class representing the freeze {@Link Power}.
 */
public class FreezePower extends AbstractPower {

    /**
     * Creates a new instance of freeze {@Link Power}.
     */
    public FreezePower() {
        super(PowerTypes.FREEZE);
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
            return new FreezePower();
        }

    }

}
