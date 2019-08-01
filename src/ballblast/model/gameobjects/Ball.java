package ballblast.model.gameobjects;

/**
 * Implements the GameObject Ball. It bounces always at same height 
 * based on its size and not depending on gravity values.
 */
public final class Ball extends AbstractGameObject {
    private static final BallTypes DEFAULT_BALL_TYPE = BallTypes.LARGE;
    private BallTypes ballType;
    private int life;
    /**
     * Creates a Ball instance.
     */
    protected Ball() {
        super(GameObjectTypes.BALL);
        this.ballType = DEFAULT_BALL_TYPE;
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
    public BallTypes getBallType() {
        return this.ballType;
    }
    /**
     * Sets the {@link Ball}'s type.
     * @param ballType
     *     the {@link Ball}'s type.
     */
    public void setBallTypes(final BallTypes ballType) {
        this.ballType = ballType;
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
        protected final Ball initGameObject() {
            return new Ball();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
