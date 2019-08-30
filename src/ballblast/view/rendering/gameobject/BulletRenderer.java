package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Bullet;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;

/**
 * 
 * A renderer for {@link Bullet} {@link GameObject}.
 */
public class BulletRenderer extends GameObjectRenderer<Bullet> {
    /**
     * Constructor which creates a new {@link Bullet} Renderer given its {@link Bullet} {@link GameObject}.
     * @param sprite
     *          The {@link Sprite} used to render.
     * @param gameObject
     *          The {@link Bullet} {@link GameObject}.
     */
    public BulletRenderer(final Sprite sprite, final Bullet gameObject) {
        super(sprite, gameObject);
        this.setLayer(Layers.BULLET_LAYER);
        sprite.setSource(ImagePath.BULLET);
    }
}
