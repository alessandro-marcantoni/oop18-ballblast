package ballblast.view.rendering;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javafx.scene.image.Image;

/**
 * 
 * 
 */
public class ImageLoader {
    private static final ImageLoader SINGLETON = new ImageLoader();
    /**
     * 
     * @return
     *          the imageLoader.
     */
    public static ImageLoader getLoader() {
        return SINGLETON;
    }
    /**
     * 
     * @param id
     *          the ID of the image to be loaded.
     * @return
     *          the Image.
     */
    public Image getImage(final ImagePath id) {
        Path path = Paths.get(id.getPath());
        return new Image(ImageLoader.class.getResourceAsStream(id.getPath()));
    }
    /**
     * 
     */
    public void loadAll() {
        Arrays.stream(ImagePath.values()).forEach(this::getImage);
    }
}
