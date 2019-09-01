package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;

import ballblast.model.powerups.AbstractPower;
import ballblast.model.powerups.PowerTypes;
import ballblast.view.rendering.ImagePath;
import ballblast.view.rendering.Layers;
import ballblast.view.rendering.Sprite;

/**
 * 
 */
public class PowerUpRenderer extends GameObjectRenderer<AbstractPower> {

    /**
     * 
     * @param sprite     the {@link Sprite} used to render.
     * @param gameObject the {@link Power} {@link GameObject}.
     * @throws FileNotFoundException the file not found.
     */
    public PowerUpRenderer(final Sprite sprite, final AbstractPower gameObject) throws FileNotFoundException {
        super(sprite, gameObject);
        this.setLayer(Layers.POWERUP_LAYER);
        if (gameObject.getPowerTag().equals(PowerTypes.DOUBLEFIRE)) {
            sprite.setSource(ImagePath.POWERUP_DOUBLEFIRE);
        } else if (gameObject.getPowerTag().equals(PowerTypes.FREEZE)) {
            sprite.setSource(ImagePath.POWERUP_FREEZE);
        } else if (gameObject.getPowerTag().equals(PowerTypes.SHIELD)) {
            sprite.setSource(ImagePath.POWERUP_SHIELD);
        }
    }
}