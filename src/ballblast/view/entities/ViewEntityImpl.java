package ballblast.view.entities;

import org.locationtech.jts.geom.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
/**
 * TODO.
 */
public class ViewEntityImpl implements ViewEntity {

    private final Shape shape;
    private final Image image;
    /**
     * Creates a {@linkk ViewEntityImpl} instance.
     * @param shape TODO.
     * @param image TODO.
     */
    public ViewEntityImpl(final Shape shape, final Image image) {
        this.shape = shape;
        this.image = image;
    }

    @Override
    public final Coordinate getPosition() {
        return new Coordinate(shape.getBoundsInParent().getWidth(), shape.getBoundsInParent().getHeight());
    }


    @Override
    public final Shape getShape() {
        return this.shape;
    }

    @Override
    public final Image getImage() {
        return this.image;
    }

}
