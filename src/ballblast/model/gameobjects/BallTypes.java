package ballblast.model.gameobjects;

import java.util.Optional;
import ballblast.model.commons.Constants;

/**
 * All different types of {@link Ball}.
 */
public enum BallTypes {
    /**
     * small.
     */
    SMALL(Constants.SMALL_BALL_SIZE),
    /**
     * medium.
     */
    MEDIUM(Constants.MEDIUM_BALL_SIZE),
    /**
     * large.
     */
    LARGE(Constants.LARGE_BALL_SIZE);

    private BallTypes child;
    private double diameter;

    static {
        SMALL.child = null;
        MEDIUM.child = SMALL;
        LARGE.child = MEDIUM;
    }

    /**
     * Create a {@link BallTypes} instance.
     * 
     * @param diameter the diameter of the {@link Ball}.
     */
    BallTypes(final double diameter) {
        this.diameter = diameter;
    }

    /**
     * Gets the {@link Optional} represents the {@link BallTypes} to split into.
     * 
     * @return an empty {@link Optional} if the {@link Ball} is not divisible, an
     *         {@link BallTypes} otherwise.
     */
    public Optional<BallTypes> getChild() {
        return Optional.ofNullable(child);
    }

    /**
     * Gets the {@link Ball}'s diameter.
     * 
     * @return the diameter of the {@link Ball}.
     */
    public double getDiameter() {
        return this.diameter;
    }
}
