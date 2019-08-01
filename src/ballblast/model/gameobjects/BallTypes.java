package ballblast.model.gameobjects;
/**
 * All different types of {@link Ball}.
 */
public enum BallTypes {
        /**
         * small.
         */
        SMALL(false, 40, 80),
        /**
         * medium.
         */
        MEDIUM(true, 40, 70),
        /**
         * large.
         */
        LARGE(true, 40, 60);

        private final boolean divisible;
        private final int minBounce;
        private final int maxBounce;
        /**
         * Creates a BallTypes instance.
         * @param divisible
         *     the boolean specifies if the object is divisible or not.
         * @param minBounce
         *     the minimum ball's bounce.
         * @param maxBounce
         *     the maximum ball's bounce.
         */
        BallTypes(final boolean divisible, final int minBounce, final int maxBounce) {
            this.divisible = divisible;
            this.minBounce = minBounce;
            this.maxBounce = maxBounce;
        }
        /**
         * Gets the boolean specifies if the object is divisible or not.
         * @return
         *     true if the object is divisible, false otherwise.
         */
        public boolean isDivisible() {
            return this.divisible;
        }
        /**
         * Gets the minimum ball's bounce.
         * @return
         *     the the minimum ball's bounce.
         */
        public int getMinBounce() {
            return this.minBounce;
        }
        /**
         * Gets the maximum ball's bounce.
         * @return
         *     the maximum ball's bounce.
         */
        public int getMaxBounce() {
            return this.maxBounce;
        }
} 
