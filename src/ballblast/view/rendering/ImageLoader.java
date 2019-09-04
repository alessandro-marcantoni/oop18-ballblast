package ballblast.view.rendering;

import java.util.EnumMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * 
 * 
 */
public class ImageLoader {
    private static final ImageLoader SINGLETON = new ImageLoader();
    private final Map<ImagePath, Image> imageMap;
//    private final Map<BallColors, Image> ballMap;

    /**
     * Simple constructor.
     */
    public ImageLoader() {
        this.imageMap = new EnumMap<>(ImagePath.class);
//        this.ballMap = new EnumMap<>(BallColors.class);
    }

    /**
     * 
     * @return the imageLoader.
     */
    public static ImageLoader getLoader() {
        return SINGLETON;
    }

    /**
     * 
     * @param imagePath the path of the image to get.
     * @return the image of the object required.
     */
    public Image getImage(final ImagePath imagePath) {
        if (!this.imageMap.containsKey(imagePath)) {
            final Image img = this.loadImage(imagePath);
            this.imageMap.put(imagePath, img);
            return img;
        } else {
            return this.imageMap.get(imagePath);
        }
    }

    /**
     * 
     * @param imagePath the path of the image to be loaded.
     * @return the Image.
     */
    public Image loadImage(final ImagePath imagePath) {
        return new Image(ImageLoader.class.getResourceAsStream(imagePath.getPath()));
    }
}
