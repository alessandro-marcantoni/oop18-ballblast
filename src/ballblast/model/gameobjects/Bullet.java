package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents a bullet that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 *
 */
public final class Bullet extends AbstractGameObject {
    private static final double DEFAULT_WIDTH = 4;
    private static final double DEFAULT_HEIGHT = 4;
    /**
     * Create a {@link Bullet} instance.
     */
    private Bullet() {
        super(GameObjectTypes.BULLET);
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
    public static class Builder extends AbstractGameObject.AbstractBuilder<Bullet, Builder> {
        @Override
        protected final Bullet initGameObject() {
            return new Bullet();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
