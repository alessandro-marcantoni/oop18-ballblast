package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents the entity used by the user, who is able to move and shoot. When
 * hit by a ball it dies.
 */
public final class Player extends AbstractGameObject { // NOPMD This class is usable only trought a Builder.
    /**
     * The static {@link Player}'s height used also inside the
     * {@link SinglePlayerDecorator} class to put the {@link Player} in the center
     * of the screen.
     */
    public static final double DEFAULT_HEIGHT = 12;
    /**
     * The static {@link Player}'s width used also inside the
     * {@link SinglePlayerDecorator} class to put the {@link Player} in the center
     * of the screen.
     */
    public static final double DEFAULT_WIDTH = 6;

    /**
     * Class constructor.
     */
    private Player() {
        super(GameObjectTypes.PLAYER);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
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
    }
}
