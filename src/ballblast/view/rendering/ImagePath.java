package ballblast.view.rendering;
/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image for balls.
     */
    BALL("/balls/balls.png"),
    /**
     * The image for cannon.
     */
    PLAYER("/players/cannon.png"),
    /**
     * The image for bullet.
     */
    BULLET("/bullets/bullet.png");
    private static final String IMAGE_PATH = "/images";
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
