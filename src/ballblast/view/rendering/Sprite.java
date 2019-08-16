package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import javafx.geometry.Point2D;

/**
 * 
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
    Point2D getPosition();
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
    void setSourceWindow(Point2D topLeft, Vector2D offset);
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
