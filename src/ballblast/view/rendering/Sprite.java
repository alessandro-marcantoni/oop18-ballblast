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
    void setPivot(Vector2D pivot);
    /**
     * Returns the pivot of the sprite. The pivot is the point relativce to the center of the image
     * that is used as the center for positioning. Coordinates in the range [-1,1] indicate a pivot inside
     * the rectangle of the image.
     * @return
     *          the pivot.
     */
    Vector2D getPivot();
    /**
     * Sets the width on the screen of the Sprite.
     * @param width 
     *          the new width.
     */
    void setWidth(double width);
    /**
     * Sets the height on the screen of the Sprite.
     * @param height
     *          the new height.
     */
    void setHeight(double height);
    /**
     * Returns the width on the screen of the rectangle on the canvas.
     * @return
     *          the width.
     */
    double getWidth();
    /**
     * Returns the height on the screen of the rectangle on the canvas.
     * @return
     *          the height.
     */
    double getHeight();
    /**
     * Sets the portion of the source image to be rendered on screen.
     * @param topLeft
     *          The position of the top-left corner of the source rectangle.
     * @param offset
     *          The offset of the bottom-right corner from the top-left corner.
     */
    void setSourceWindow(Coordinate topLeft, Vector2D offset);
    /**
     * Returns the width of the source image in pixels.
     * @return
     *          the width.
     */
    double getSourceWidth();
    /**
     * Returns the height of the source image in pixels.
     * @return
     *          the height.
     */
    double getSourceHeight();
    /**
     * Sets the source image of the sprite. This method resets the source windows properties.
     * @param source
     *          the new source image path.
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

}
