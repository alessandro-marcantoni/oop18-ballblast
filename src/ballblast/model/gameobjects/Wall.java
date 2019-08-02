package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import ballblast.model.gameobjects.Player.Builder;

/**
 * Represents a wall object used to create the level's boundaries. 
 */
public class Wall extends AbstractGameObject {
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;

    private final int height;
    private final int width;
    /**
     * Creates a {@link Wall} instance.
     */
    protected Wall() {
        super(GameObjectTypes.WALL);
        this.height = DEFAULT_HEIGHT;
        this.width = DEFAULT_WIDTH;
    }
    @Override
    public final int getHeight() {
        return this.height;
    }
    @Override
    public final int getWidth() {
        return this.width;
    }
    @Override
    public final boolean equals(final Object obj) {
       if (obj == null || getClass() != obj.getClass()) {
          return false;
       }
       final Wall other = (Wall) obj;
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
               .toString();
    }
    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Wall, Builder> {
        @Override
        protected final Wall initGameObject() {
            return new Wall();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}

