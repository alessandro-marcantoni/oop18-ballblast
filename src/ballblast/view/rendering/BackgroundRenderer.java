package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.Model;
import ballblast.model.commons.Constants;

/**
 * A renderer for the background of the GUI.
 */
public class BackgroundRenderer extends AbstractRenderer {


    private final Sprite sprite;

    /**
     * 
     * @param sprite     the sprite used to render the {@link GameObject}.
     */
    public BackgroundRenderer(final Sprite sprite) {
        this.sprite = sprite;
        this.sprite.setPosition(new Coordinate(0, 0));
        this.sprite.setGameObjectWidth(Constants.WORLD_WIDTH);
        this.sprite.setGameObjectHeight(Constants.WORLD_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.sprite.render();
    }

    /**
     * Return the {@link Sprite} used to render the {@link GameObject}.
     * 
     * @return the {@link Sprite} object.
     */
    protected Sprite getSprite() {
        return this.sprite;
    }

}
