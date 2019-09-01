package ballblast.view.rendering;

/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image for balls.
     */
    BALL("/balls/blue.png"),
    /**
     * The image for the cannon.
     */
    PLAYER("/players/cannon.png"),
    /**
     * The image for the bullet.
     */
    BULLET("/bullets/bullet.png"),
    /**
     * The image for powers.
     */
    POWERS("/powers/powers.png"),
    /**
     * The image for the wall.
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
