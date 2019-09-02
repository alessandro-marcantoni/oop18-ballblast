package ballblast.view.rendering;

import java.io.FileNotFoundException;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;

/**
 * 
 */
public class ImageSprite extends AbstractRenderer implements Sprite {

    private static final double DEFAULT = 100;
    private static final double MAX_ALPHA = 1;
    private static final double MIN_ALPHA = 0;
    private static final int TEXT_X_OFFSET = 1;
    private static final int TEXT_Y_OFFSET = 3;
    private static final int MAX_TEXT_WIDTH = 4;
    private static final int FONT_LARGE = 10;
    private static final int FONT_MEDIUM = 8;
    private static final int FONT_SMALL = 4;
    private Coordinate sourceTopLeft;
    private Vector2D sourceOffset;
    private Coordinate position;
    private double alpha;
    private Image image;
    private final GraphicsContext gc;
    private final GameObject gameObject;
    private double gameObjectWidth;
    private double gameObjectHeight;
    private Coordinate gameObjectPosition;

    /**
     * Creates a new Image sprite with the given GraphicsContext.
     * 
     * @param gc         the {@link GraphicsContext}.
     * @param gameObject the {@link GameObject} to be rendered.
     */
    public ImageSprite(final GraphicsContext gc, final GameObject gameObject) {
        super();
        this.sourceTopLeft = new Coordinate(0, 0);
        this.sourceOffset = Vector2D.create(0, 0);
        this.position = new Coordinate(0, 0);
        this.alpha = MAX_ALPHA;
        this.gc = gc;
        this.image = null;
        this.gameObject = gameObject;
        this.gameObjectWidth = DEFAULT;
        this.gameObjectWidth = DEFAULT;
        this.gameObjectPosition = new Coordinate(0, 0);
    }

    @Override
    public final void render() {
        // Draw the gameObject
        this.gc.scale(1, -1);
        this.gc.setGlobalAlpha(this.getAlpha());
        this.gc.drawImage(
                // the source image
                this.image,
                // the source rectangle's coordinate position.
                this.getSourceTopLeftCorner().getX(), this.getSourceTopLeftCorner().getY(),
                // the source rectangle's dimension (width and height).
                this.image.getWidth(), this.image.getHeight(),
                // the destination rectangle's coordinate position.
                this.gameObject.getPosition().getX(), this.gameObject.getPosition().getY(),
                // the destination rectangle's dimension (width and height).
                this.gameObject.getWidth(), this.gameObject.getHeight());

        if (this.gameObject.getType().equals(GameObjectTypes.BALL) && ((Ball) this.gameObject).getCurrentLife() > 0) {
            // Set the font
            if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.LARGE.getDiameter())) {
                this.gc.setFont(new Font(FONT_LARGE));
            } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.MEDIUM.getDiameter())) {
                this.gc.setFont(new Font(FONT_MEDIUM));
            } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.SMALL.getDiameter())) {
                this.gc.setFont(new Font(FONT_SMALL));
            }
            // Draw life inside the ball
            this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                    this.gameObject.getPosition().getX() + this.gameObject.getWidth() / 4 + TEXT_X_OFFSET,
                    this.gameObject.getPosition().getY() + this.gameObject.getHeight() / 2 + TEXT_Y_OFFSET,
                    MAX_TEXT_WIDTH);
        }
    }

    @Override
    public final void setPosition(final Coordinate coordinate) {
        this.position = coordinate;
    }

    @Override
    public final Coordinate getPosition() {
        return this.position;
    }

    @Override
    public final void setSourceWindow(final Coordinate topLeft, final Vector2D offset) {
        this.sourceTopLeft = topLeft;
        this.sourceOffset = offset;
    }

    @Override
    public final double getSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public final Image getImageSource() {
        return this.image;
    }

    /**
     * 
     * @return the image width.
     */
    public double getImageSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public final double getSourceHeight() {
        return this.image.getHeight();
    }

    /**
     * 
     * @return the image height.
     */
    public double getImageSourceHeight() {
        return this.image.getHeight();
    }

    @Override
    public final void setSource(final ImagePath source) throws FileNotFoundException {
        this.image = ImageLoader.getLoader().getImage(source);
        this.setSourceWindow(new Coordinate(0, 0), new Vector2D(this.getSourceWidth(), this.getSourceHeight()));
    }

    @Override
    public final void setAlpha(final double alpha) {
        this.alpha = Math.min(MAX_ALPHA, Math.max(MIN_ALPHA, alpha));
    }

    @Override
    public final double getAlpha() {
        return this.alpha;
    }

    /**
     * Returns the position of the top-left corner of the source rectangle.
     * 
     * @return the top-left corner.
     */
    protected final Coordinate getSourceTopLeftCorner() {
        return this.sourceTopLeft;
    }

    /**
     * Returns the offset of the bottom-right corner from the top-left corner.
     * 
     * @return the offset in pixel.
     */
    protected final Vector2D getSourceOffset() {
        return this.sourceOffset;
    }

    @Override
    public final void setGameObjectWidth(final double width) {
        this.gameObjectWidth = width;
    }

    @Override
    public final double getGameObjectWidth() {
        return this.gameObjectWidth;
    }

    @Override
    public final void setGameObjectHeight(final double height) {
        this.gameObjectHeight = height;
    }

    @Override
    public final double getGameObjectHeight() {
        return this.gameObjectHeight;
    }

    @Override
    public final void setGameObjectPosition(final Coordinate position) {
        this.gameObjectPosition = position;
    }

    @Override
    public final Coordinate getGameObjectPosition() {
        return this.gameObjectPosition;
    }

}
