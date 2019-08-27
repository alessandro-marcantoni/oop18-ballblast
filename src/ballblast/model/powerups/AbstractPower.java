package ballblast.model.powerups;

import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.gameobjects.Player;

/**
 * The abstract class representing a powerup.
 */
public abstract class AbstractPower extends AbstractGameObject implements Power {

    private static final double AVAILABLE_TIME = 5;

    private Player player;
    private boolean isActive;
    private final PowerType powerType;
    private double elapsedTime = 0;

    /**
     * Constructor taking the type of the powerup.
     * @param powerType
     *      The type of the powerup.
     */
    public AbstractPower(final PowerType powerType) {
        super(GameObjectTypes.POWERUP);
        this.powerType = powerType;
    }

    @Override
    public final void update(final double elapsed) {
        super.update(elapsed);
        if (this.isActive()) {
            this.elapsedTime += elapsed;
            this.checkTime();
        }
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

    private void checkTime() {
        if (this.elapsedTime >= AVAILABLE_TIME) {
            this.deactivate();
            this.destroy();
        }
    }

}
