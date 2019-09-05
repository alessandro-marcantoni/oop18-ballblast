package ballblast.model.powerups;

import ballblast.model.gameobjects.GameObject;

/**
 * The interface representing a powerup.
 */
public interface Power {

    /**
     * Activates the powerup on a specific player.
     * 
     * @param player The {@link Player} who gets the powerup.
     */
    void activate(GameObject player);

    /**
     * Deactivates the powerup.
     */
    void deactivate();

    /**
     * Returns the enhancement active state.
     * 
     * @return The enhancement active state.
     */
    boolean isActive();

    /**
     * Returns the {@link PowerTypes} of the powerup.
     * 
     * @return The {@link PowerTypes} of the powerup.
     */
    PowerTypes getPowerType();

}
