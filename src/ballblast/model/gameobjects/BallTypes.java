package ballblast.model.gameobjects;

import java.util.Optional;

/**
 * All different types of {@link Ball}.
 */
public enum BallTypes {
    /**
     * small.
     */
    SMALL(15),
    /**
     * medium.
     */
    MEDIUM(20),
    /**
     * large.
     */
    LARGE(25);

    private BallTypes child;
    private int diameter;

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
    BallTypes(final int diameter) {
        this.diameter = diameter;
    }

    /**
     * Gets the {@link Optional} represents the {@link BallTypes} to split into.
     * 
     * @return an empty {@link Optional} if the {@link Ball} is not divisible, an
     *         {@link BallTypes} otherwise.
     */
    public Optional<BallTypes> getChild() {
        return Optional.of(child);
    }

    /**
     * Gets the {@link Ball}'s diameter.
     * 
     * @return the diameter of the {@link Ball}.
     */
    public int getDiameter() {
        return this.diameter;
    }
}
