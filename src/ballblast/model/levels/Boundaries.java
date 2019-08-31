package ballblast.model.levels;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.Model;

/**
 * Represents the game's boundaries.
 */
public enum Boundaries {
    /**
     * the left boundary.
     */
    LEFT,
    /**
     * the right boundary.
     */
    RIGHT,
    /**
     * the top boundary.
     */
    TOP,
    /**
     * the bottom boundary.
     */
    BOTTOM;

    private static final double VERTICAL_WALL_WIDTH = 5;
    private static final double VERTICAL_WALL_HEIGHT = Model.WORLD_HEIGHT;
    private static final double HORIZONTAL_WALL_WIDTH = Model.WORLD_WIDTH - 2 * VERTICAL_WALL_WIDTH;
    private static final double HORIZONTAL_WALL_HEIGHT = VERTICAL_WALL_WIDTH;

    private Coordinate position;
    private double width;
    private double height;

    static {
        LEFT.width = VERTICAL_WALL_WIDTH;
        LEFT.height = VERTICAL_WALL_HEIGHT;
        LEFT.position = new Coordinate(0, 0);

        RIGHT.width = VERTICAL_WALL_WIDTH;
        RIGHT.height = VERTICAL_WALL_HEIGHT;
        RIGHT.position = new Coordinate(Model.WORLD_WIDTH - VERTICAL_WALL_WIDTH, 0);

        TOP.width = HORIZONTAL_WALL_WIDTH;
        TOP.height = HORIZONTAL_WALL_HEIGHT;
        TOP.position = new Coordinate(VERTICAL_WALL_WIDTH, 0);

        BOTTOM.width = HORIZONTAL_WALL_WIDTH;
        BOTTOM.height = HORIZONTAL_WALL_HEIGHT;
        BOTTOM.position = new Coordinate(VERTICAL_WALL_WIDTH, Model.WORLD_HEIGHT - HORIZONTAL_WALL_HEIGHT);
    }


    /**
     * Gets boundary's position.
     * 
     * @return the position of the boundary.
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Gets boundary's width.
     * 
     * @return the width of the boundary.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets boundary's height.
     * 
     * @return the height of the boundary.
     */
    public double getHeight() {
        return height;
    }
}
