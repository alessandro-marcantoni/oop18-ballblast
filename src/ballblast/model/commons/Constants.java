package ballblast.model.commons;

import org.locationtech.jts.geom.Coordinate;
/**
 * Groups all the {@link Model}'s constants.
 */
public final class Constants {
    private Constants() { }
    /**
     * Default width for the levels.
     */
    public static final double WORLD_WIDTH = 200;
    /**
     * Default height for the levels.
     */
    public static final double WORLD_HEIGHT = 100;
    /**
     * Default {@link ShooterComponent}'s shot interval.
     */
    public static final double DEFAULT_SHOT_INTERVAL = 0.15;
    /**
     * Default size for small {@link Ball}.
     */
    public static final double SMALL_BALL_SIZE = 8;
    /**
     * Default size for medium {@link Ball}.
     */
    public static final double MEDIUM_BALL_SIZE = 12;
    /**
     * Default size for large {@link Ball}.
     */
    public static final double LARGE_BALL_SIZE = 16;
    /**
     * Default {@link Bullet}'s width.
     */
    public static final double BULLET_WIDTH = 1.5;
    /**
     * Default {@link Bullet}'s height.
     */
    public static final double BULLET_HEIGHT = 2;
    /**
     * Default {@link Player}'s height.
     */
    public static final double PLAYER_HEIGHT = 12;
    /**
     * Default {@link Player}'s width.
     */
    public static final double PLAYER_WIDTH = 6;
    /**
     * Default {@link Player}'s speed.
     */
    public static final double PLAYER_SPEED = 45;
    /**
     * Default initial {@link Player}'s position.
     */
    public static final Coordinate PLAYER_POSITION = new Coordinate(WORLD_WIDTH / 2,
            WORLD_HEIGHT - (5.1 + PLAYER_HEIGHT));
    /**
     * Default {@link Power}'s spawn time.
     */
    public static final double POWER_SPAWN_TIME = 20.0;
    /**
     * Default {@link Ball}'s spawn time.
     */
    public static final double BALL_SPAWN_TIME = 10;

}
