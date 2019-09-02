package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents the entity used by the user, who is able to move and shoot. When
 * hit by a ball it dies.
 */
public final class Player extends AbstractGameObject { // NOPMD This class is usable only trought a Builder.
    /**
     * The default {@link Player}'s height.
     */
    public static final double DEFAULT_HEIGHT = 12;
    /**
     * The default {@link Player}'s width.
     */
    public static final double DEFAULT_WIDTH = 6;
    /**
     * The default {@link Player}'s speed.
     */
    public static final double DEAULT_SPEED = 45;

    private double currentSpeed;

    /**
     * Class constructor.
     */
    private Player() {
        super(GameObjectTypes.PLAYER);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
        this.currentSpeed = DEAULT_SPEED;
    }

    /**
     * Sets {@link Player}'s speed.
     * 
     * @param speed the current {@link Player}'s speed.
     */
    public void setSpeed(final double speed) {
        this.currentSpeed = speed;
    }

    /**
     * Gets {@link Player}'s speed.
     * 
     * @return the current {@link Player}'s speed.
     */
    public double getSpeed() {
        return this.currentSpeed;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("GameObjectType", this.getType())
                .add("Position", this.getPosition().toString())
                .add("IsDestroyed", this.isDestroyed())
                .toString();
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Player, Builder> {
        @Override
        protected final Player initGameObject() {
            return new Player();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }

        /**
         * Sets the {@link Player}'s speed.
         * 
         * @param speed the {@link Player}'s speed to be set.
         * @return the concrete {@link Builder}.
         */
        public Builder setSpeed(final double speed) {
            this.getGameObject().setSpeed(speed);
            return this;
        }
    }
}
