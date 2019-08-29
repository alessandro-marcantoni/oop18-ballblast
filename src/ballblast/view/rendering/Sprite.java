package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
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
     * Sets the pivot of the sprite. The pivot is the point relative to the center of the image that
     * is used as the center for positioning. Coordinates in the range [-1,1] indicate a pivot inside
     * the rectangle of the image.
     * @param pivot
     *          the new pivot.
     */
    void setSourceWindow(Coordinate topLeft, Vector2D offset);
    /**
     * Returns the width of the source image in pixels.
     * @return
     *          the width.
     */
    void setSource(ImagePath source);
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
     */
    double getSourceHeight();
    /**
     * 
     * @return
     */
    double getSourceWidth();
    /**
     * 
     * @param d
     */
    void setGameObjectWidth(double width);
    /**
     * 
     * @param height
     */
    void setGameObjectHeight(double height);
    /**
     * 
     * @param position
     */
    void setGameObjectPosition(Coordinate position);
    /**
     * 
     * @return
     */
    Coordinate getGameObjectPosition();
    /**
     * 
     * @return
     */
    double getGameObjectWidth();
    /**
     * 
     * @return
     */
    double getGameObjectHeight();

}
