package ballblast.view.rendering;

/**
 * 
 * A wrapper class for layer constants.
 */
public final class Layers {
    /**
     * The layer of the {@link Wall} {@link GameObject}.
     */
    public static final int WALL_LAYER = 1;
    /**
     * The layer of the {@link Bullet} {@link GameObject}.
     */
    public static final int BULLET_LAYER = 2;
    /**
     * The layer of the {@link Player} {@link GameObject}.
     */
    public static final int PLAYER_LAYER = 3;
    /**
     * The layer of the {@link Ball} {@link GameObject}.
     */
    public static final int BALL_LAYER = 4;
    /**
     * The layer of the {@link Power} {@link GameObject}.
     */
    public static final int POWERUP_LAYER = 5;

    private Layers() {
        // Can't create a Layer object.
    }
}
