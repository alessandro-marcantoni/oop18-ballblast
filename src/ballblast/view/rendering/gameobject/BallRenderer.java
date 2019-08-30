package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Ball;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * 
 * A renderer for {@link Ball} {@link GameObject}.
 * 
 */
public class BallRenderer extends GameObjectRenderer<Ball> {
    /**
     * @param sprite
     *          the {@link Sprite} used to render.
     * @param gameObject
     *          the {@link Ball} {@link @link GameObject}.
     */
    public BallRenderer(final Sprite sprite, final Ball gameObject) {
        super(sprite, gameObject);
        this.setLayer(Layers.BALL_LAYER);
        final Text life = new Text(Integer.toString(gameObject.getLife()));
        final StackPane pane = new StackPane();
        pane.getChildren().add((Node) sprite);
        pane.getChildren().add(life);
        pane.setAlignment(Pos.CENTER);
        sprite.setSource(ImagePath.BALL);
    }
}
