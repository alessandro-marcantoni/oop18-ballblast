package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * This class implements the Player object. It represents the entity controlled by the user 
 * which can shoot to destroy objects and dies when hit by a ball.
 */
public class Player extends AbstractGameObject {
    private static final double DEFAULT_WIDTH = 12;
    private static final double DEFAULT_HEIGHT = 15;
    /**
     * Creates a Player instance.
     */
    protected Player() {
        super(GameObjectTypes.PLAYER);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
    }

    @Override
    public final boolean equals(final Object obj) {
       if (obj == null || getClass() != obj.getClass()) {
          return false;
       }
       final Player other = (Player) obj;
       return Objects.equal(this.getType(), other.getType());
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
