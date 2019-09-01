package ballblast.model.powerups;

/**
 * Represents the factory Interface for creating {@link Power} in a Factory
 * Method.
 */
public interface PowerFactory {

    /**
     * Creates a new {@link Power}.
     * 
     * @return A new {@link Power}.
     */
    Power createRandomPower();

}
