package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import javafx.geometry.Point2D;

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
public interface Sprite extends Renderer{
    /**
     * 
     * @param coordinate
     *          pippo
     */
    void setPosition(Coordinate coordinate);
    /**
     * 
     * @return
     *          pippo
     */
    Coordinate getPosition();
    /**
     * 
     * @param pivot
     *          pippo
     */
    void setPivot(Vector2D pivot);
    /**
     * 
     * @return
     *          pippo
     */
    Vector2D getPivot();
    /**
     * 
     * @param width 
     *          pippo
     */
    void setWidth(double width);
    /**
     * 
     * @param height
     *          pippo
     */
    void setHeight(double height);
    /**
     * 
     * @return
     *          pippo
     */
    double getWidth();
    /**
     * 
     * @return
     *          pippo
     */
    double getHeight();
    /**
     * 
     * @param topLeft
     *          pippo
     * @param offset
     *          pippo
     */
    void setSourceWindow(Coordinate topLeft, Vector2D offset);
    /**
     * 
     * @return
     *          pippo
     */
    double getSourceWidth();
    /**
     * 
     * @return
     *          pippo
     */
    double getSourceHeight();
    /**
     * 
     * @param source
     *          pippo
     */
    void setSource(ImagePath source);
    /**
     * 
     * @param alpha
     *          pippo
     */
    void setAlpha(double alpha);
    /**
     * 
     * @return
     *          pippo
     */
    double getAlpha();

}
