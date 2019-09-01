package ballblast.view.rendering;

import java.io.FileNotFoundException;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import javafx.scene.image.Image;
/**
 * Represents an image that can be rendered on the screen at a certain position on the screen
 * and with some rotation. 
 * The position specifies the pixel at which the pivot of the sprite will be positioned
 * relative to the bottom left corner of the canvas.
 * The pivot is the point relative to the center of the image that is used as the center for positioning.
 * It can be set as a vector starting from the center of the image.
 * Coordinates in the range [-1,1] indicate a pivot inside the rectangle of the image.
 * The image source is specified as an {@link ImagePath}.
 * 
 */
public interface Sprite extends Renderer {
    /**
     * Sets the position of the sprite in pixel coordinates relative to the pivot.
     * @param coordinate
     *          the position to be set.
     */
    void setPosition(Coordinate coordinate);
    /**
     * Returns the position of the sprite in pixel coordinates relative to the pivot.
     * @return
     *          the position.
     */
    Coordinate getPosition();
    /**
     * 
     * @return
     *          the {@link Image} source.
     */
    Image getImageSource();
    /**
     * 
     * @param topLeft
     *          The top left corner position. Default is (0, 0)
     * @param offset
     *          The offset of the bottom right corner from the top left one.
     */
    void setSourceWindow(Coordinate topLeft, Vector2D offset);
    /**
     * Sets the source image of the sprite.
     * @param source
     *          the new source image identifier.
     * @throws FileNotFoundException 
     */
    void setSource(ImagePath source) throws FileNotFoundException;
    /**
     * Sets the opacity of the sprite. Values [0,1].
     * @param alpha
     *          the new alpha value.
     */
    void setAlpha(double alpha);
    /**
     * Returns the opacity of the sprite.
     * @return
     *          the alpha value.
     */
    double getAlpha();
    /**
     * 
     * @return
     *          return the image source height.
     */
    double getSourceHeight();
    /**
     * 
     * @return
     *          return the image source width.
     */
    double getSourceWidth();
    /**
     * Sets the width on the screen of the sprite, 
     * according to the {@link GameObject} width.
     * @param width
     *          the new width.
     */
    void setGameObjectWidth(double width);
    /**
     * 
     * Sets the width on the screen of the sprite, 
     * according to the {@link GameObject} height.
     * @param height
     *          the new height.
     */
    void setGameObjectHeight(double height);
    /**
     * Sets the position on the screen of the sprite, 
     * according to the {@link GameObject} position.
     * @param position
     *          the new position
     */
    void setGameObjectPosition(Coordinate position);
    /**
     * 
     * @return
     *          the position of the {@link GameObject}.
     */
    Coordinate getGameObjectPosition();
    /**
     * 
     * @return
     *          the width of the {@link GameObject}.
     */
    double getGameObjectWidth();
    /**
     * 
     * @return
     *          the height of the {@link GameObject}.
     */
    double getGameObjectHeight();

}
