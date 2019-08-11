package ballblast.view.entities;

import org.locationtech.jts.geom.Coordinate;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * 
 * Represent the entity's information to draw in the view.
 * 
 */
public interface ViewEntity {
    
    /**
     * 
     * @return 
     *          the entity position.
     */
    Coordinate getPosition();
    
    /**
     * 
     * @return 
     *          the entity shape.
     */
    Shape getShape();
    
    /**
     * 
     * @return 
     *          the entity image.
     */
    Image getPicture();
}
