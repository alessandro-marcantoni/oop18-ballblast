package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 */
public class ImageSprite extends AbstractRenderer implements Sprite {

    private static final double DEFAULT_DIMENSION = 100;
    private static final double MAX_ALPHA = 1;
    private static final double MIN_ALPHA = 0;
    private Coordinate sourceTopLeft;
    private Vector2D sourceOffset;
    private Coordinate position;
    private Vector2D pivot;
    private double width;
    private double height;
    private double alpha;
    private Image image;
    private final GraphicsContext gc;
    /**
     * 
     */
    public ImageSprite(final GraphicsContext gc) {
        super();
        this.sourceTopLeft = new Coordinate(0, 0);
        this.sourceOffset = Vector2D.create(0, 0);
        this.pivot = Vector2D.create(0, 0);
        this.position = new Coordinate(0, 0);
        this.width = DEFAULT_DIMENSION;
        this.height = DEFAULT_DIMENSION;
        this.alpha = MAX_ALPHA;
        this.gc = gc;
        this.image = null;
    }

    @Override
    public void render() {
        this.gc.translate(this.getPosition().getX(), this.getPosition().getY());
        this.gc.scale(1, -1);
        this.gc.setGlobalAlpha(this.getAlpha());
        this.gc.drawImage(this.image, 
                          this.getSourceTopLeftCorner().getX(),
                          this.getSourceTopLeftCorner().getY(),
                          this.getSourceOffset().getX(),
                          this.getSourceOffset().getY(),
                          (this.getPivot().getX() - 1) * this.getWidth() / 2,
                          (this.getPivot().getY() - 1) * this.getHeight() / 2,
                          this.getWidth(),
                          this.getHeight());
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
    public final void setPivot(final Vector2D pivot) {
        this.pivot = pivot;
    }

    @Override
    public final Vector2D getPivot() {
        return this.pivot;
    }

    @Override
    public final void setWidth(final double width) {
        this.width = width;
    }

    @Override
    public final void setHeight(final double height) {
        this.height = height;
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final double getHeight() {
        return this.height;
    }

    @Override
    public final void setSourceWindow(final Coordinate topLeft, final Vector2D offset) {
        this.sourceTopLeft = topLeft;
        this.sourceOffset = offset;
    }

    @Override
    public final double getSourceWidth() {
        return this.getWidth();
    }
    /**
     * 
     * @return
     *          the image width.
     */
    public double getImageSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public final double getSourceHeight() {
        return this.getHeight();
    }
    /**
     * 
     * @return
     *          the image height.
     */
    public double getImageSourceHeight() {
        return this.image.getHeight();
    }

    @Override
    public final void setSource(final ImagePath source) {
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
    
    protected final Coordinate getSourceTopLeftCorner() {
        return this.sourceTopLeft;
    }
    
    protected final Vector2D getSourceOffset() {
        return this.sourceOffset;
    }

}
