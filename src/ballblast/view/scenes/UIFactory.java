package ballblast.view.scenes;

import ballblast.model.powerups.PowerTypes;
import ballblast.view.rendering.ImageLoader;
import ballblast.view.rendering.ImagePath;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Factory which creates User Interface object.
 */
public class UIFactory {
    private static final double PADDING_INTERNAL = 5;
    private static final double PADDIN_EXTERNAL = 10;
    private static final double PERCENT_WIDTH_BAR = 50;
    private static final double PERCENT_WIDTH_BUTTON = 35;
    private static final double POWER_BLOCK_HEIGHT = 50;
    /**
     * 
     * @param power
     *          The power to select the icon.
     * @return
     *          A icon for the given power.
     */
    public ImageView createPowerIcon(final PowerTypes power) {
        final ImageView imageView = new ImageView();
        final Image image = ImageLoader.getLoader().getImage(ImagePath.POWERS);
        final double cellWidth = image.getWidth() / 3;
        final double cellHeight = image.getHeight() / 2;
        switch (power) {
        case SHIELD:
            imageView.setViewport(new Rectangle2D(cellWidth, 0, cellWidth, cellHeight));
            break;
        case DOUBLEFIRE:
            imageView.setViewport(new Rectangle2D(0, 0, cellWidth, cellHeight));
            break;
        case FREEZE:
            imageView.setViewport(new Rectangle2D(cellWidth * 2, 0, cellWidth, cellHeight));
            break;
        default: break;
        }
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
