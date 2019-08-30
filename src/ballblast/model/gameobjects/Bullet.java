package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents a {@link Bullet} that can be shot by the {@link Player} to hit the
 * balls. It can collide only with {@link Wall}s and {@link Ball}s.
 *
 */
public final class Bullet extends AbstractGameObject { // NOPMD This class is usable only trought a Builder.
    /**
     * Default {@link Bullet}'s width.
     */
    public static final double DEFAULT_WIDTH  = 1;
    /**
     * Default {@link Bullet}'s height.
     */
    public static final double DEFAULT_HEIGHT = 1.5;

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
                .add("Position", this.getPosition().toString())
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
