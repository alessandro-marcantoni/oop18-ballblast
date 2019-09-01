package ballblast.model.powerups;

import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.gameobjects.Player;

/**
 * The abstract class representing a {@Link Power}.
 */
public abstract class AbstractPower extends AbstractGameObject implements Power {

    private static final double AVAILABLE_TIME = 5;

    private Player player;
    private boolean active;
    private PowerTypes powerType;
    private double elapsedTime;

    /**
     * Constructor for a generic {@Link Power}.
     * 
     * @param powerType The type of the {@Link Power}.
     */
    public AbstractPower(final PowerTypes powerType) {
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
        this.active = true;
        this.player = player;
    }

    @Override
    public final void deactivate() {
        this.stopPerforming();
        this.active = false;
    }

    @Override
    public final boolean isActive() {
        return this.active;
    }

    @Override
    public final PowerTypes getPowerType() {
        return this.powerType;
    }

    /**
     * The specific action performed by a {@Link Power}.
     */
    protected abstract void performPower();

    /**
     * Stops the performed action of the {@Link Power}.
     */
    protected abstract void stopPerforming();

    /**
     * Returns the {@Link Player} who gets the {@Link Power}.
     * 
     * @return The {@Link Player} who gets the {@Link Power}.
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
