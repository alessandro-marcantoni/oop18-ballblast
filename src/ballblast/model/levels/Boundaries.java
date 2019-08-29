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

    private static final double VERTICAL_WALL_WIDTH = 5;
    private static final double VERTICAL_WALL_HEIGHT = Model.WORLD_HEIGHT;
    private static final double HORIZONTAL_WALL_WIDTH = Model.WORLD_WIDTH - 2 * VERTICAL_WALL_WIDTH;
    private static final double HORIZONTAL_WALL_HEIGHT = VERTICAL_WALL_WIDTH;
    private final Vector2D velocity = Vector2D.create(0, 0);
    private Coordinate position;
    private double width;
    private double height;
    static {
        /*
         * TODO LEFT.position = new Coordinate(0, 0); LEFT.width = Model.WALL_OFFSET *
         * 2; LEFT.height = Model.WORLD_HEIGHT;
         * 
         * RIGHT.position = new Coordinate(Model.WORLD_WIDTH - Model.WALL_OFFSET, 0);
         * RIGHT.width = Model.WALL_OFFSET * 2; RIGHT.height = Model.WORLD_HEIGHT;
         * 
         * TOP.position = new Coordinate(Model.WALL_OFFSET, 0); TOP.width =
         * Model.WORLD_WIDTH - Model.WALL_OFFSET * 4; TOP.height = Model.WALL_OFFSET;
         * 
         * BOTTOM.position = new Coordinate(Model.WALL_OFFSET, Model.WORLD_HEIGHT -
         * Model.WALL_OFFSET); BOTTOM.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 4;
         * BOTTOM.height = Model.WALL_OFFSET;
         */
//        LEFT.position = new Coordinate(Model.WALL_OFFSET * 4, Model.WORLD_HEIGHT / 2 + Model.WALL_OFFSET * 4);
//        LEFT.width = Model.WALL_OFFSET * 2;
//        LEFT.height = Model.WORLD_HEIGHT + Model.WORLD_HEIGHT / 2;
//
//        RIGHT.position = new Coordinate(Model.WORLD_WIDTH + Model.WALL_OFFSET * 2, Model.WORLD_HEIGHT / 2 + Model.WALL_OFFSET * 4);
//        RIGHT.width = Model.WALL_OFFSET * 2;
//        RIGHT.height = Model.WORLD_HEIGHT + Model.WORLD_HEIGHT / 2;
//
//        TOP.position = new Coordinate(Model.WORLD_WIDTH, Model.WORLD_HEIGHT - Model.WALL_OFFSET);
//        TOP.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 4;
//        TOP.height = Model.WALL_OFFSET * 2;
//
//        BOTTOM.position = new Coordinate(Model.WORLD_WIDTH, 0);
//        BOTTOM.width = Model.WORLD_WIDTH - Model.WALL_OFFSET * 4;
//        BOTTOM.height = Model.WALL_OFFSET * 2;

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

    /**
     * Gets boundary's velocity.
     * 
     * @return the velcoity of the boundary.
     */
    public Vector2D getVelocity() {
        return velocity;
    }
}
