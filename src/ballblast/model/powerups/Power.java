package ballblast.model.powerups;

import ballblast.model.gameobjects.Player;

/**
 * The interface representing a powerup.
 */
public interface Power {

    /**
     * Activates the powerup on a specific player.
     * @param player
     *      The player who gets the powerup.
     */
    void activate(Player player);

    /**
     * Deactivates the powerup.
     */
    void deactivate();

    /**
     * Returns the enhancement active state.
     * @return
     *       The enhancement active state.
     */
    boolean isActive();

    /**
     * Returns the type of the powerup.
     * @return
     *       The type of the powerup.
     */
    PowerType getPowerTag();

    /**
     * Updates the state of the powerup following the elapsed time.
     * @param elapsed
     *      The elapsed time.
     */
    void update(double elapsed);
}
