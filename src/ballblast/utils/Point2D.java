package ballblast.utils;

import com.google.common.base.MoreObjects;

/**
 * Defines a point representing a location in (x,y) coordinate space.
 */
public class Point2D {
    /**
     * Default {@link Point2D} position.
     */
    public static final Point2D ZERO = new Point2D(0.0, 0.0);
    private final double x;
    private final double y;
    /**
     * Creates a new instance of Point2D.
     * @param x
     *       the x coordinate of the point.
     * @param y
     *       the y coordinate of the point.
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Gets the x coordinate.
     * @return
     *      the x coordinate.
     */
    public double getX() {
        return x;
    }
    /**
     * Gets the y coordinate.
     * @return
     *      the y coordinate.
     */
    public double getY() {
        return y;
    }

    @Override
    public final String toString() {
       return MoreObjects.toStringHelper(this)
               .add("X", this.getX())
               .add("Y", this.getY())
               .toString();
    }
}
