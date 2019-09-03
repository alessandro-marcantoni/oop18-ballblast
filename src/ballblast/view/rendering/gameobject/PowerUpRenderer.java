package ballblast.view.rendering.gameobject;

import ballblast.model.powerups.AbstractPower;
import ballblast.model.powerups.PowerTypes;
import ballblast.view.rendering.ImagePath;
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
    public PowerUpRenderer(final Sprite sprite, final AbstractPower gameObject) {
        super(sprite, gameObject);
        try {
            if (gameObject.getPowerType().equals(PowerTypes.DOUBLEFIRE)) {
                sprite.setSource(ImagePath.POWERUP_DOUBLEFIRE);
            } else if (gameObject.getPowerType().equals(PowerTypes.SPEED)) {
                sprite.setSource(ImagePath.POWERUP_SPEED);
            } else if (gameObject.getPowerType().equals(PowerTypes.SHIELD)) {
                sprite.setSource(ImagePath.POWERUP_SHIELD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}