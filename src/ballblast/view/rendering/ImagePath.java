package ballblast.view.rendering;
/**
 * Identifies an image stored on the disk and its path.
 */
public enum ImagePath {
    /**
     * The image prova.
     */
    PROVA("/prove/prova.png");
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
