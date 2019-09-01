package ballblast.model.powerups;

import java.util.Random;

/**
 * Implementation of the {@link PowerFactory} interface.
 */
public class PowerFactoryImpl implements PowerFactory {

    @Override
    public final Power createRandomPower() {
        final int powerTypePick = new Random().nextInt(PowerTypes.values().length);
        switch (powerTypePick) {
        case 1:
            return this.createShieldPower();
        case 2:
            return this.createDoubleFirePower();
        default:
            return this.createFreezePower();
        }
    }

    /**
     * Creates a shield {@link Power}.
     * 
     * @return the new shield {@link Power}.
     */
    public Power createShieldPower() {
        return null;
    }

    /**
     * Creates a double fire {@link Power}.
     * 
     * @return the new double fire {@link Power}.
     */
    public Power createDoubleFirePower() {
        return new DoubleFirePower();
    }

    /**
     * Creates a freeze {@link Power}.
     * 
     * @return the new freeze {@link Power}.
     */
    public Power createFreezePower() {
        return new FreezePower();
    }

}
