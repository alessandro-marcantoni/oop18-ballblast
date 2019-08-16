package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.GameObject;
import ballblast.view.rendering.AbstractRenderer;
import ballblast.view.rendering.Sprite;
/**
 * 
 * 
 * @param <G>
 */
public abstract class GameObjectRenderer<G extends GameObject> extends AbstractRenderer {

    private final Sprite sprite;
    private final G gameObject;

    /**
     * 
     * @param sprite
     *          the sprite used to render the {@link GameObject}.
     * @param gameObject
     *          the {@link GameObject} to render.
     */
    public GameObjectRenderer(final Sprite sprite, final G gameObject) {
        this.sprite = sprite;
        this.gameObject = gameObject;
    }

    @Override
    public final void render() {
        this.sprite.setPosition(this.gameObject.getPosition());
        this.sprite.setWidth(this.gameObject.getWidth());
        this.sprite.setHeight(this.gameObject.getHeight());
        this.sprite.render();
    }

    /**
     * Returns the {@link GameObject} that this object will render.
     * @return
     *          the {@link GameObject}.
     */
    protected G getGameObject() {
        return this.gameObject;
    }

    /**
     * Return the {@link Sprite} used to render the {@link GameObject}.
     * @return
     *          the {@link Sprite} object.
     */
    protected Sprite getSprite() {
        return this.sprite;
    }
}
