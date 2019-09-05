package ballblast.model.powerups;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.ComponentTypes;
import ballblast.model.gameobjects.AbstractGameObject;

/**
 * The class representing the shield {@link Power}.
 */
public final class ShieldPower extends AbstractPower {

    private ShieldPower() {
        super(PowerTypes.SHIELD);
    }

    @Override
    protected void performPower() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
        .findFirst()
        .ifPresent(c -> ((CollisionComponent) c).disable());
    }

    @Override
    protected void stopPerforming() {
        this.getPlayer().getComponents().stream()
        .filter(c -> c.getType().equals(ComponentTypes.COLLISION))
        .findFirst()
        .ifPresent(c -> ((CollisionComponent) c).enable());
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<ShieldPower, Builder> {
        @Override
        public final ShieldPower build() {
            return this.getGameObject();
        }

        @Override
        protected final ShieldPower initGameObject() {
            return new ShieldPower();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }

}
