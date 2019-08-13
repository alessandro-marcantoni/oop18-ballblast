package ballblast.model.constants;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

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
        LEFT.position = new Coordinate(0, -Model.WALL_OFFSET / 2);
        LEFT.width = Model.WORLD_WIDTH + Model.WALL_OFFSET * 2;
        LEFT.height = Model.WALL_OFFSET;

        RIGHT.position = new Coordinate(0, Model.WORLD_HEIGHT + Model.WALL_OFFSET / 2);
        RIGHT.width = Model.WORLD_WIDTH + Model.WALL_OFFSET * 2;
        RIGHT.height = Model.WALL_OFFSET; 

        TOP.position = new Coordinate(-(Model.WORLD_WIDTH / 2 + Model.WALL_OFFSET / 2), Model.WORLD_HEIGHT / 2);
        TOP.width = Model.WALL_OFFSET;
        TOP.height = Model.WORLD_HEIGHT;

        BOTTOM.position = new Coordinate(Model.WORLD_WIDTH / 2 + Model.WALL_OFFSET / 2, Model.WORLD_HEIGHT / 2);
        BOTTOM.width = Model.WALL_OFFSET;
        BOTTOM.height = Model.WORLD_HEIGHT; 
    }

    private final Vector2D velocity = Vector2D.create(0, 0);
    private Coordinate position;
    private double width;
    private double height;
    /**
     * Gets boundary's position.
     * @return
     *     the position of the boundary.
     */
    public Coordinate getPosition() {
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
    /**
     * Gets boundary's velocity.
     * @return
     *     the velcoity of the boundary.
     */
    public Vector2D getVelocity() {
        return velocity;
    }
}
