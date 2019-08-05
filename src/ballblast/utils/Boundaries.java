package ballblast.utils;

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
    static {
        LEFT.position = new Point2D(0, -Model.WALL_OFFSET / 2);
        LEFT.width = Model.WORLD_WIDTH + Model.WALL_OFFSET * 2;
        LEFT.height = Model.WALL_OFFSET;

        RIGHT.position = new Point2D(0, Model.WORLD_HEIGHT + Model.WALL_OFFSET / 2);
        RIGHT.width = Model.WORLD_WIDTH + Model.WALL_OFFSET * 2;
        RIGHT.height = Model.WALL_OFFSET; 

        TOP.position = new Point2D(-(Model.WORLD_WIDTH / 2 + Model.WALL_OFFSET / 2), Model.WORLD_HEIGHT / 2);
        TOP.width = Model.WALL_OFFSET;
        TOP.height = Model.WORLD_HEIGHT;

        BOTTOM.position = new Point2D(Model.WORLD_WIDTH / 2 + Model.WALL_OFFSET / 2, Model.WORLD_HEIGHT / 2);
        BOTTOM.width = Model.WALL_OFFSET;
        BOTTOM.height = Model.WORLD_HEIGHT; 
    }
    private Point2D position;
    private double width;
    private double height;
    /**
     * Gets boundary's position.
     * @return
     *     the position of the boundary.
     */
    public Point2D getPosition() {
        return position;
    }
    /**
     * Gets boundary's width.
     * @return
     *     the width of the boundary.
     */
    public double getWidth() {
        return width;
    }
    /**
     * Gets boundary's height.
     * @return
     *     the height of the boundary.
     */
    public double getHeight() {
        return height;
    }
}
