package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * This class implements the Player object. It represents the entity controlled by the user 
 * which can shoot to destroy objects and dies when hit by a ball.
 */
public final class Player extends AbstractGameObject { // NOPMD This class is usable only trought a Builder.
    private static final double DEFAULT_WIDTH = 12;
    private static final double DEFAULT_HEIGHT = 16;
    /**
     * Creates a Player instance.
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
               .add("Position", this.getPosition())
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
