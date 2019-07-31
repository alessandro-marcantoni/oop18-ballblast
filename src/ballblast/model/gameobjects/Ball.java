package ballblast.model.gameobjects;
/**
 * Implements the GameObject Ball. It bounces always at same height 
 * based on its size and not depending on gravity values.
 */
public final class Ball extends AbstractGameObject {
    private int life;
    /**
     * Creates a Ball instance.
     * @param type
     *     the type of {@link GameObject}.
     */
    private Ball(final GameObjectTypes type) {
        super(type);
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
            super.gameObject.setLife(life);
            return this;
        }
        @Override
        protected final Ball getGameObject() {
            return new Ball(GameObjectTypes.BALL);
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
