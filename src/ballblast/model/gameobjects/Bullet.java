package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a bullet that can be shot by the player to hit the balls.
 * It can collide whit walls and balls, not with the player.
 *
 */
public class Bullet extends AbstractGameObject {
    private static final double DEFAULT_WIDTH = 12;
    private static final double DEFAULT_HEIGHT = 15;

    private final double height;
    private final double width;
    /**
     * Create a {@link Bullet} instance.
     */
    protected Bullet() {
        super(GameObjectTypes.BULLET);
        this.height = DEFAULT_HEIGHT;
        this.width = DEFAULT_WIDTH;
    }

    @Override
    public final double getHeight() {
        return this.height;
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final boolean equals(final Object obj) {
       if (obj == null || getClass() != obj.getClass()) {
          return false;
       }
       final Bullet other = (Bullet) obj;
       return Objects.equal(this.getType(), other.getType())
               && Objects.equal(this.getPosition(), other.getPosition());
    }

    @Override
    public final int hashCode() {
       return Objects.hashCode(this.getType());
    }

    @Override
    public final String toString() {
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
