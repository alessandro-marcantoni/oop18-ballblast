package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/**
 * Represents a wall object used to create the level's boundaries. 
 */
public class Wall extends AbstractGameObject {
    //To remove
    private static final int DEFAULT_SIZE = 5;
    private double height;
    private double width;
    /**
     * Creates a {@link Wall} instance.
     */
    protected Wall() {
        super(GameObjectTypes.WALL);
        //To remove
        this.height = DEFAULT_SIZE;
        this.width = DEFAULT_SIZE;
    }
    /**
     * Sets the {@link Wall}'s height.
     * @param height
     *     the height of the {@link Wall}.
     */
    public void setHeight(final double height) {
        this.height = height;
    }
    /**
     * Sets the {@link Wall}'s width.
     * @param width
     *     the width of the {@link Wall}.
     */
    public void setWidth(final double width) {
        this.width = width;
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
               .add("IsDestroyed", this.isDestroyed())
               .toString();
    }
    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Wall, Builder> {
        /**
         * Sets the {@link Wall}'s height.
         * @param height
         *     the height of the {@link Wall}.
         * @return
         *     the {@link Builder}.
         */
        public Builder setHeight(final double height) {
            this.getGameObject().setHeight(height);
            return this;
        }
        /**
         * Sets the {@link Wall}'s width.
         * @param width
         *     the width of the {@link Wall}.
         * @return
         *     the {@link Builder}.
         */
        public Builder setWidth(final double width) {
            this.getGameObject().setWidth(width);
            return this;
        }

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

