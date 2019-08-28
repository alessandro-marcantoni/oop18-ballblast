package ballblast.model.gameobjects;

import java.util.Optional;
import com.google.common.base.MoreObjects;

/**
 * Implements the GameObject {@link Ball}.
 */
public final class Ball extends AbstractGameObject {
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
     * 
     * @param life the {@link Ball} life.
     */
    public void setLife(final int life) {
        this.life = life;
    }

    /**
     * Gets the {@link GameObject} life.
     * 
     * @return the {@link GameObject} life.
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Gets the {@link Ball}'s type.
     * 
     * @return the {@link Ball}'s type.
     */
    public BallTypes getBallType() {
        return this.ballType;
    }

    /**
     * Sets the {@link Ball}'s type.
     * 
     * @param ballType the {@link Ball}'s type.
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
                .add("Position", this.getPosition().toString())
                .add("IsDestroyed", this.isDestroyed())
                .toString();
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Ball, Builder> {
        /**
         * Sets the {@link GameObject}.
         * 
         * @param life the {@link Ball} life.
         * @return the {@link Builder}.
         */
        public Builder setLife(final int life) {
            this.getGameObject().setLife(life);
            return this;
        }

        /**
         * Sets the {@link Ball}'s type.
         * 
         * @param ballType the {@link Ball}'s type.
         * @return the {@link Builder}.
         */
        public Builder setBallType(final BallTypes ballType) {
            this.getGameObject().setBallTypes(ballType);
            return this;
        }

        @Override
        public final Ball build() {
            if (!Optional.ofNullable(getGameObject().getBallType()).isPresent()) {
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
