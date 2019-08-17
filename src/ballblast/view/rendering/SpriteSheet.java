package ballblast.view.rendering;

import org.locationtech.jts.math.Vector2D;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public final class SpriteSheet {
    private final Sprite sprite;
    private final double cellWidth;
    private final double cellHeight;
    
    public SpriteSheet(final Sprite sprite, final int columns, final int rows) {
        this.sprite = sprite;
        this.cellWidth = sprite.getSourceWidth() / columns;
        this.cellHeight = sprite.getSourceHeight() / rows;
    }

    public void setCell(final int x, final int y) {
        this.sprite.setSourceWindow(
                                    new Point2D (x * this.cellWidth, y *this.cellHeight),
                                    new Vector2D (this.cellWidth, this.cellHeight));
        
    }
}