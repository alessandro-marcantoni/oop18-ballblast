package ballblast.view.rendering;

import ballblast.view.rendering.gameobject.BallColors;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image for {@link Ball}. String empty to use randomColor in the
     * {@link ImageLoader}.
     */
    BALL(BallColors.getRandomColor()),
    /**
     * The image for {@link Player}.
     */
    PLAYER("/view/players/cannon.png"),
    /**
     * The image for {@link Bullet}.
     */
    BULLET("/view/bullets/bullet.png"),
    /**
     * The image for speed {@link Power}.
     */
    POWERUP_SPEED("/view/powers/freeze.png"),
    /**
     * The image for shield {@link Power}.
     */
    POWERUP_SHIELD("/view/powers/shield.png"),
    /**
     * The image for double fire {@link Power}.
     */
    POWERUP_DOUBLEFIRE("/view/powers/doublefire.png"),
    /**
     * The image for {@link Wall} horizontal.
     */
    WALL_HORIZONTAL("/view/walls/wall_horizontal.png"),
    /**
     * The image for {@link Wall} vertical.
     */
    WALL_VERTICAL("/view/walls/wall_vertical.png");
    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }

    /**
     * 
     * @return the path of the image.
     */
    public String getPath() {
        return this.path;
    }
}
