package ballblast.model.gameobjects;

import java.security.InvalidParameterException;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/**
 * Implements the GameObject {@link Ball}. It bounces always at same height 
 * based on its size and not depending on gravity values.
 */
public final class Ball extends AbstractGameObject {
    //private static final int MAX_BOUNCE = 80;
    //private static final int MIN_BOUNCE = 50;
    private BallTypes ballType;
    private int life;
    private int diameter;
    /**
     * Creates a Ball instance.
     */
    protected Ball() {
        super(GameObjectTypes.BALL);
    }
    /**
     * Sets the {@link Ball} life.
     * @param life
     *     the {@link Ball} life.
     */
    public void setLife(final int life) {
        this.life = life;
    }
    /**
     * Gets the {@link GameObject} life.
     * @return
     *     the {@link GameObject} life.
     */
    public int getLife() {
        return this.life;
    }
    /**
     * Gets the {@link Ball}'s type.
     * @return
     *     the {@link Ball}'s type.
     */
    public Optional<BallTypes> getBallType() {
        return Optional.ofNullable(this.ballType);
    }
    /**
     * Sets the {@link Ball}'s type.
     * @param ballType
     *     the {@link Ball}'s type.
     */
    public void setBallTypes(final BallTypes ballType) {
        this.ballType = ballType;
        this.diameter = ballType.getDiameter();
    }

    @Override
    public int getHeight() {
        return this.diameter;
    }

    @Override
    public int getWidth() {
        return this.diameter;
    }

    @Override
    public boolean equals(final Object obj) {
       if (obj == null || getClass() != obj.getClass()) {
          return false;
       }
       final Ball other = (Ball) obj;
       return Objects.equal(this.getType(), other.getType())
               && Objects.equal(this.ballType, other.ballType)
               && Objects.equal(this.life, other.life)
               && Objects.equal(this.getPosition(), other.getPosition());
    }

    @Override
    public int hashCode() {
       return Objects.hashCode(this.getType(), this.ballType);
    }

    @Override
    public String toString() {
       return MoreObjects.toStringHelper(this)
               .add("GameObjectType", this.getType())
               .add("BallType", this.ballType)
               .add("Diameter", this.diameter)
               .add("Life", this.life)
               .add("Position", this.getPosition())
               .add("IsDestroyed", this.isDestroyed())
               .toString();
    }
    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Ball, Builder> {
        /**
         * Sets the {@link GameObject}.
         * @param life
         *     the {@link Ball} life.
         * @return
         *     the {@link Builder}.
         */
        public Builder setLife(final int life) {
            this.getGameObject().setLife(life);
            return this;
        }
        /**
         * Sets the {@link Ball}'s type.
         * @param ballType
         *     the {@link Ball}'s type.
         * @return
         *     the {@link Builder}.
         */
        public Builder setBallType(final BallTypes ballType) {
            this.getGameObject().setBallTypes(ballType);
            return this;
        }

        @Override
        public final Ball build() {
            if (!getGameObject().getBallType().isPresent()) {
                throw new InvalidParameterException("BallType has to be set!");
            }
            return this.getGameObject();
        }

        @Override
        protected final Ball initGameObject() {
            return new Ball();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
