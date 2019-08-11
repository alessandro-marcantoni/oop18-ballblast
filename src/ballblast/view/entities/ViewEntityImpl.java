package ballblast.view.entities;

import org.locationtech.jts.geom.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public final class ViewEntityImpl implements ViewEntity {

    private final Shape shape;
    private final Image image;
    

    public ViewEntityImpl(final Shape shape, final Image image) {
        this.shape = shape;
        this.image = image;
    }
    
    @Override
    public Coordinate getPosition() {
        return new Coordinate(shape.getBoundsInParent().getWidth(), shape.getBoundsInParent().getHeight());
    }


    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

}
