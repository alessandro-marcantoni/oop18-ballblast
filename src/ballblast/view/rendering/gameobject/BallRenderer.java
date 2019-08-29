package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Ball;
import ballblast.view.entities.BallColors;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;
import ballblast.view.rendering.SpriteSheet;
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
    private static final int COLUMNS = 3;
    private static final int ROWS = 2;
    /**
     * @param sprite
     *          the {@link Sprite} used to render.
     * @param gameObject
     *          the {@link Ball} {@link @link GameObject}.
     */
    public BallRenderer(final Sprite sprite, final Ball gameObject) {
        super(sprite, gameObject);
        this.setLayer(Layers.BALL_LAYER);

        Text life = new Text(Integer.toString(gameObject.getLife()));
        StackPane pane = new StackPane();
        pane.getChildren().add((Node) sprite);
        pane.getChildren().add(life);
        pane.setAlignment(Pos.CENTER);

        final BallColors color = BallColors.randomColor();
        sprite.setSource(ImagePath.BALL);
        final SpriteSheet spriteSheet = new SpriteSheet(sprite, COLUMNS, ROWS);
        switch (color) {
        case BALL_BLUE:
            spriteSheet.setCell(0, 0);
            break;
        case BALL_YELLOW:
            spriteSheet.setCell(1, 0);
            break;
        case BALL_RED:
            spriteSheet.setCell(2, 0);
            break;
        case BALL_GREEN:
            spriteSheet.setCell(2, 1);
            break;
        case BALL_ORANGE:
            spriteSheet.setCell(0, 1);
            break;
        case BALL_PURPLE:
            spriteSheet.setCell(1, 1);
            break;
        default:
            break;
        }
    }
}
