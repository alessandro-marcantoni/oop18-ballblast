package ballblast.view.rendering.gameobject;

import org.locationtech.jts.math.Vector2D;

import ballblast.model.gameobjects.Player;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;
import ballblast.view.rendering.SpriteSheet;

/**
 * 
 * 
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {

    private static final int COLUMNS = 4;
    private static final int ROWS = 5;
    private static final double PLAYER_OFFSET = 3;
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
//        sprite.setPivot(new Vector2D(0, -1));
    }

    @Override
    public final void render() {
//        this.getSprite().setGameObjectPosition(this.getGameObject().getPosition());
//        this.getSprite().setGameObjectWidth(this.getGameObject().getWidth());
//        this.getSprite().setGameObjectHeight(this.getGameObject().getHeight());
        this.getSprite().render();
    }
}
