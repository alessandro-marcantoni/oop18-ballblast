package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.powerups.Power;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import ballblast.model.Model;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;

/**
 * 
 */
public class ImageSprite extends AbstractRenderer implements Sprite {

    private static final double MAX_ALPHA = 1;
    private static final double MIN_ALPHA = 0;
    private static final int DECS_OFFSET = 2;
    private static final int CENTS_OFFSET = 2;
    private static final int MAX_TEXT_WIDTH = 10;
    private static final int FONT_LARGE = 10;
    private static final int FONT_MEDIUM = 8;
    private static final int FONT_SMALL = 4;
    private Coordinate sourceTopLeft;
    private Vector2D sourceOffset;
    private Coordinate position;
    private double alpha;
    private Image image;
    private final GraphicsContext gc;
    private GameObject gameObject;
    private double gameObjectWidth;
    private double gameObjectHeight;
    private Coordinate gameObjectPosition;
    private Font usingFont;
    private Font fontLarge;
    private Font fontMedium;
    private Font fontSmall;

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
        this.gameObjectHeight = this.gameObject.getHeight();
        this.gameObjectWidth = this.gameObject.getWidth();
        this.gameObjectPosition = this.gameObject.getPosition();

        this.fontLarge = Font.font("Roboto", FONT_LARGE);
        this.fontMedium = Font.font("Roboto", FONT_MEDIUM);
        this.fontSmall = Font.font("Roboto", FONT_SMALL);
    }

    @Override
    public final void render() {
        // Draw the gameObject
        this.gc.scale(1, -1);
        this.gc.setGlobalAlpha(this.getAlpha());
        this.gc.setTextBaseline(VPos.TOP);

        if (!this.gameObject.getType().equals(GameObjectTypes.POWERUP)
                || (this.gameObject.getType().equals(GameObjectTypes.POWERUP)
                        && (!((Power) this.gameObject).isActive()))) {
            this.gc.drawImage(
                    this.image,
                    this.getSourceTopLeftCorner().getX(), this.getSourceTopLeftCorner().getY(),
                    this.image.getWidth(), this.image.getHeight(),
                    this.getGameObjectPosition().getX(), this.getGameObjectPosition().getY(),
                    this.getGameObjectWidth(), this.getGameObjectHeight());

            if (this.gameObject.getType().equals(GameObjectTypes.BALL)
                    && ((Ball) this.gameObject).getCurrentLife() > 0) {
                drawLife();
            } else if (this.gameObject.getType().equals(GameObjectTypes.BALL)
                    && !(((Ball) this.gameObject).getCurrentLife() > 0)) {
                this.renderFireworks(gc, gameObjectWidth, gameObjectHeight);
            }
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
    public final double getImageSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public final Image getImageSource() {
        return this.image;
    }

    @Override
    public final double getImageSourceHeight() {
        return this.image.getHeight();
    }

    @Override
    public final void setSource(final ImagePath source) {
        this.image = ImageLoader.getLoader().getImage(source);
        this.setSourceWindow(new Coordinate(0, 0), new Vector2D(this.getImageSourceWidth(), this.getImageSourceHeight()));
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

//    /**
//     * Returns the offset of the bottom-right corner from the top-left corner.
//     * 
//     * @return the offset in pixel.
//     */
//    protected final Vector2D getSourceOffset() {
//        return this.sourceOffset;
//    }

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

    /**
     * Static method used to render the background.
     * 
     * @param gc     the {@link GraphicsContext} of the canvas.
     * @param width  the width of the canvas.
     * @param height the height of the canvas.
     */
    public static void renderBackground(final GraphicsContext gc, final double width, final double height) {
        gc.drawImage(ImageLoader.getLoader().getImage(ImagePath.BACKGROUND), 0, 0, width, height);
    }
    /**
     * Static method used to render the background.
     * 
     * @param gc     the {@link GraphicsContext} of the canvas.
     * @param width  the width of the canvas.
     * @param height the height of the canvas.
     */
    public void renderFireworks(final GraphicsContext gc, final double width, final double height) {
        gc.drawImage(ImageLoader.getLoader().getImage(ImagePath.FIREWORKS),
                this.getGameObjectPosition().getX(), this.getGameObjectPosition().getY(),
                this.getGameObjectWidth(), this.getGameObjectHeight());
    }

    private void drawLife() {
        // Set the font
        if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.LARGE.getDiameter())) {
            this.gc.setFont(fontLarge);
            this.usingFont = fontLarge;
        } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.MEDIUM.getDiameter())) {
            this.gc.setFont(fontMedium);
            this.usingFont = fontMedium;
        } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallTypes.SMALL.getDiameter())) {
            this.gc.setFont(fontSmall);
            this.usingFont = fontSmall;
        }
        if (((Ball) this.gameObject).getCurrentLife() < 10) {
            drawUnits();
        } else if (((Ball) this.gameObject).getCurrentLife() < 100) {
            drawDecs();
        } else {
            drawCents();
        }
        // Draw life inside the ball

    }

    private void drawUnits() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3),
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

    private void drawDecs() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3) - DECS_OFFSET,
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

    private void drawCents() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3) - CENTS_OFFSET,
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

}
