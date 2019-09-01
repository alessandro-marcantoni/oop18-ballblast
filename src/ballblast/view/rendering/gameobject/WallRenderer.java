package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.Model;
import ballblast.model.gameobjects.Wall;
import ballblast.model.levels.Boundaries;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;

/**
 * Represents a Renderer class for {@link Wall}.
 */
public class WallRenderer extends GameObjectRenderer<Wall> {

    private static final double MAX_SIZE = 100;

    /**
     * Creates a new {@link Wall} Renderer given its {@link Wall}{@link GameObject}.
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Wall} {@link GameObject}.
     * @throws FileNotFoundException the file not found.
     */
    public WallRenderer(final Sprite sprite, final Wall gameObject) throws FileNotFoundException {
        super(sprite, gameObject);
        this.setLayer(Layers.WALL_LAYER);
        if (Boundaries.isFloor(gameObject.getPosition()) || Boundaries.isRoof(gameObject.getPosition())) {
            sprite.setSource(ImagePath.WALL_VERTICAL);
        } else if (Boundaries.isLeft(gameObject.getPosition()) || Boundaries.isRight(gameObject.getPosition())) {
        sprite.setSource(ImagePath.WALL_HORIZONTAL);
        }
    }
}
