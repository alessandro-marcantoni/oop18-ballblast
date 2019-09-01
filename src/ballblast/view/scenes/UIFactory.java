package ballblast.view.scenes;

import java.io.FileNotFoundException;

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
     * @param power The power to select the icon.
     * @return A icon for the given power.
     * @throws FileNotFoundException the file not found.
     */
    public ImageView createPowerIcon(final PowerTypes power) throws FileNotFoundException {
        final ImageView imageView = new ImageView();
        final Image imageDoublefire = ImageLoader.getLoader().getImage(ImagePath.POWERUP_DOUBLEFIRE);
        final Image imageFreeze = ImageLoader.getLoader().getImage(ImagePath.POWERUP_FREEZE);
        final Image imageShield = ImageLoader.getLoader().getImage(ImagePath.POWERUP_SHIELD);
        switch (power) {
        case SHIELD:
            imageView.setViewport(
                    new Rectangle2D(imageShield.getWidth(), 0, imageShield.getWidth(), imageShield.getHeight()));
            imageView.setImage(imageShield);
            break;
        case DOUBLEFIRE:
            imageView.setViewport(new Rectangle2D(0, 0, imageDoublefire.getWidth(), imageDoublefire.getHeight()));
            imageView.setImage(imageDoublefire);
            break;
        case FREEZE:
            imageView.setViewport(
                    new Rectangle2D(imageFreeze.getWidth(), 0, imageFreeze.getWidth(), imageFreeze.getHeight()));
            imageView.setImage(imageFreeze);
            break;
        default:
            break;
        }

        imageView.setPreserveRatio(true);
        return imageView;
    }
}
