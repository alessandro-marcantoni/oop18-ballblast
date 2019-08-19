package ballblast.model.levels;

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
        /*TODO
        LEFT.position = new Coordinate(0, 0);
        LEFT.width = Model.WALL_OFFSET;
        LEFT.height = Model.WORLD_HEIGHT;

        RIGHT.position = new Coordinate(Model.WORLD_WIDTH - Model.WALL_OFFSET, 0);
        RIGHT.width = Model.WALL_OFFSET;
        RIGHT.height = Model.WORLD_HEIGHT; 

        TOP.position = new Coordinate(Model.WALL_OFFSET, 0);
        TOP.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 2;
        TOP.height = Model.WALL_OFFSET; 

        BOTTOM.position = new Coordinate(Model.WALL_OFFSET, Model.WORLD_HEIGHT - Model.WALL_OFFSET);
        BOTTOM.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 2;
        BOTTOM.height = Model.WALL_OFFSET; 
        */
        LEFT.position = new Coordinate(Model.WALL_OFFSET, Model.WORLD_HEIGHT / 2);
        LEFT.width = Model.WALL_OFFSET * 2;
        LEFT.height = Model.WORLD_HEIGHT;

        RIGHT.position = new Coordinate(Model.WORLD_WIDTH - Model.WALL_OFFSET, Model.WORLD_HEIGHT / 2);
        RIGHT.width = Model.WALL_OFFSET * 2;
        RIGHT.height = Model.WORLD_HEIGHT; 

        TOP.position = new Coordinate(Model.WORLD_WIDTH / 2, Model.WALL_OFFSET);
        TOP.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 4;
        TOP.height = Model.WALL_OFFSET * 2; 

        BOTTOM.position = new Coordinate(Model.WORLD_WIDTH / 2, Model.WORLD_HEIGHT - Model.WALL_OFFSET);
        BOTTOM.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 4;
        BOTTOM.height = Model.WALL_OFFSET * 2; 
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
