package ballblast.view.rendering;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image for {@link Ball}.
     * String empty to use randomColor in the {@link ImageLoader}.
     */
    BALL(""),
    /**
     * The image for {@link Player}.
     */
    PLAYER("/players/cannon.png"),
    /**
     * The image for {@link Bullet}.
     */
    BULLET("/bullets/bullet.png"),
    /**
     * The image for freeze {@link Power}.
     */
    POWERUP_FREEZE("/powers/freeze.png"),
    /**
     * The image for shield {@link Power}.
     */
    POWERUP_SHIELD("/powers/shield.png"),
    /**
     * The image for double fire {@link Power}.
     */
    POWERUP_DOUBLEFIRE("/powers/doublefire.png"),
    /**
     * The image for {@link Wall}.
     */
    WALL("/walls/wall.png");
    private static final String IMAGE_PATH = "/view";
    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }
    /**
     * 
     * @return
     *          the path of the image.
     */
    public String getPath() {
        return IMAGE_PATH + this.path;
    }
}
