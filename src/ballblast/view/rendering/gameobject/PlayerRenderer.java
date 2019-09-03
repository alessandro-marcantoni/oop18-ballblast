package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;
import ballblast.model.gameobjects.Player;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * 
 * 
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {
    /**
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Player} {@link GameObject}
     * @throws FileNotFoundException file not found.
     */
    public PlayerRenderer(final Sprite sprite, final Player gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setSource(ImagePath.PLAYER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void render() {
        this.getSprite().render();
    }
}
