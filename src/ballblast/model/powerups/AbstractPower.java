package ballblast.model.powerups;

import ballblast.model.gameobjects.AbstractGameObject;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;

/**
 * The abstract class representing a {@Link Power}.
 */
public abstract class AbstractPower extends AbstractGameObject implements Power {

    private static final double AVAILABLE_TIME = 10;
    private static final double LIFE_TIME = 5;
    private static final double DEFAULT_HEIGHT = 6;
    private static final double DEFAULT_WIDTH = 6;

    private GameObject player;
    private boolean active;
    private final PowerTypes powerType;
    private double lifeTime;
    private double availableTime;

    /**
     * Constructor for a generic {@Link Power}.
     * 
     * @param powerType The type of the {@Link Power}.
     */
    public AbstractPower(final PowerTypes powerType) {
        super(GameObjectTypes.POWERUP);
        this.powerType = powerType;
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
    }

    @Override
    public final void update(final double elapsed) {
        super.update(elapsed);
        if (this.isActive()) {
            this.lifeTime += elapsed;
            this.checkLife();
        } else {
            this.availableTime += elapsed;
            this.checkAvailable();
        }
    }

    /**
     * May be extended in subclasses.
     */
    @Override
    public void activate(final GameObject player) {
        if (!this.isActive()) {
            this.active = true;
            this.player = player;
            this.performPower();
        }
    }

    @Override
    public final void deactivate() {
        if (this.isActive()) {
            this.stopPerforming();
            this.active = false;
        }
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
    public GameObject getPlayer() {
        return this.player;
    }

    private void checkLife() {
        if (this.lifeTime >= LIFE_TIME) {
            this.deactivate();
            this.destroy();
        }
    }

    private void checkAvailable() {
        if (this.availableTime >= AVAILABLE_TIME) {
            this.destroy();
        }
    }

    /**
     * Implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public abstract static class Builder extends AbstractGameObject.AbstractBuilder<AbstractPower, Builder> {

        @Override
        protected abstract AbstractPower initGameObject();

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
