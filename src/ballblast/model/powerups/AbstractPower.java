package ballblast.model.powerups;

import ballblast.model.gameobjects.Player;

/**
 * The abstract class representing a powerup.
 */
public abstract class AbstractPower implements Power {

    private Player player;
    private boolean isActive;
    private final PowerType powerType;

    /**
     * Constructor taking the type of the powerup.
     * @param powerType
     *      The type of the powerup.
     */
    public AbstractPower(final PowerType powerType) {
        this.powerType = powerType;
    }

    /**
     * May be extended in subclasses.
     */
    @Override
    public void activate(final Player player) {
        this.isActive = true;
        this.player = player;
    }

    @Override
    public final void deactivate() {
        this.isActive = false;
    }

    @Override
    public final boolean isActive() {
        return this.isActive;
    }

    @Override
    public final PowerType getPowerTag() {
        return this.powerType;
    }

    /**
     * Returns the player who gets the powerup.
     * @return
     *       The player who gets the powerup.
     */
    public Player getPlayer() {
        return this.player;
    }

}
