package ballblast.model.gameobjects;

import java.util.Optional;

import org.locationtech.jts.math.Vector2D;

import com.google.common.base.MoreObjects;
/**
 * Implements the GameObject {@link Ball}. It bounces always at same height 
 * based on its size and not depending on gravity values.
 */
public final class Ball extends AbstractGameObject {
    /**
     * the {@link Ball}'s velocity.
     */
    public static final Vector2D VELOCITY = new Vector2D();
    private BallTypes ballType;
    private int life;
    /**
     * Creates a Ball instance.
     */
    private Ball() {
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
    private void setBallTypes(final BallTypes ballType) {
        this.ballType = ballType;
        this.setHeight(ballType.getDiameter());
        this.setWidth(ballType.getDiameter());
    }

    @Override
    public String toString() {
       return MoreObjects.toStringHelper(this)
               .add("GameObjectType", this.getType())
               .add("BallType", this.ballType)
               .add("Diameter", this.ballType.getDiameter())
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
                throw new IllegalStateException("BallType unset!");
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
