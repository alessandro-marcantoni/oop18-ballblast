package ballblast.view.rendering.gameobject;

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
        final SpriteSheet spriteSheet = new SpriteSheet(sprite, COLUMNS, ROWS);
        spriteSheet.setCell(3, 2);
    }

    @Override
    public final void render() {
        this.getSprite().setPosition(this.getGameObject().getPosition());
        this.getSprite().setWidth(this.getGameObject().getWidth() + PLAYER_OFFSET);
        this.getSprite().setHeight(this.getGameObject().getHeight());
        this.getSprite().render();
    }
}
