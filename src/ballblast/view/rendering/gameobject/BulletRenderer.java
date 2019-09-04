package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;
import ballblast.model.gameobjects.Bullet;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * 
 * A renderer for {@link Bullet} {@link GameObject}.
 */
public class BulletRenderer extends GameObjectRenderer<Bullet> {
    /**
     * Constructor which creates a new {@link Bullet} Renderer given its
     * {@link Bullet} {@link GameObject}.
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Bullet} {@link GameObject}.
     */
    public BulletRenderer(final Sprite sprite, final Bullet gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
            sprite.setSource(ImagePath.BULLET);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
