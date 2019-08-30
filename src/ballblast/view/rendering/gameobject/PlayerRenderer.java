package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Player;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;

/**
 * 
 * 
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {
    /**
     * 
     * @param sprite
     *          The {@link Sprite} used to render.
     * @param gameObject
     *          The {@link Player} {@link GameObject}
     */
    public PlayerRenderer(final Sprite sprite, final Player gameObject) {
        super(sprite, gameObject);
        this.setLayer(Layers.PLAYER_LAYER);
        sprite.setSource(ImagePath.PLAYER);
    }

    @Override
    public final void render() {
        this.getSprite().render();
    }
}
