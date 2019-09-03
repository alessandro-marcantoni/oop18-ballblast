package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

/**
 * 
 * 
 */
public final class SpriteSheet {
    private final Sprite sprite;
    private final double cellWidth;
    private final double cellHeight;

    /**
     * 
     * @param sprite  sprite
     * @param columns columns
     * @param rows    rows
     */
    public SpriteSheet(final Sprite sprite, final int columns, final int rows) {
        this.sprite = sprite;
        this.cellWidth = sprite.getImageSourceWidth() / columns;
        this.cellHeight = sprite.getImageSourceHeight() / rows;
    }

    /**
     * 
     * @param x x
     * @param y y
     */
    public void setCell(final int x, final int y) {
        this.sprite.setSourceWindow(new Coordinate(x * this.cellWidth, y * this.cellHeight),
                new Vector2D(this.cellWidth, this.cellHeight));
    }
}
