package ballblast.view.entities;

import ballblast.model.gameobjects.BallTypes;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class ViewEntityBall extends ViewEntityImpl {

    private BallTypes dimension;
    private BallColors color;
    
    public ViewEntityBall(final BallTypes dimension, final BallColors color, Shape shape, Image image) {
        super(shape, image);
        this.dimension = dimension;
        this.color = BallColors.randomColor();
    }
}
