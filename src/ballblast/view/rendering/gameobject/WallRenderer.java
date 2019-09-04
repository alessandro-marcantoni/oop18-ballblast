package ballblast.view.rendering.gameobject;


import ballblast.model.gameobjects.Wall;
import ballblast.model.levels.Boundaries;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * Represents a Renderer class for {@link Wall}.
 */
public class WallRenderer extends GameObjectRenderer<Wall> {
    /**
     * Creates a new {@link Wall} Renderer given its {@link Wall}{@link GameObject}.
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Wall} {@link GameObject}.
     * @throws FileNotFoundException the file not found.
     */
    public WallRenderer(final Sprite sprite, final Wall gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
        if (Boundaries.isFloor(gameObject.getPosition()) || Boundaries.isRoof(gameObject.getPosition())) {
            sprite.setSource(ImagePath.WALL_VERTICAL);
        } else if (Boundaries.isLeft(gameObject.getPosition()) || Boundaries.isRight(gameObject.getPosition())) {
        sprite.setSource(ImagePath.WALL_HORIZONTAL);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
