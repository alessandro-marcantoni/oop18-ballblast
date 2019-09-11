package ballblast.model.inputs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ballblast.commons.Command;
import ballblast.model.components.ComponentTypes;
import ballblast.model.components.InputComponent;
import ballblast.model.components.ShooterComponent;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.Player;

/**
 * Manages inputs and redirects them to the right {@link Player} thanks {@link InputComponent}.
 */
public class InputManagerImpl implements InputManager {
    private static final Map<InputTypes, Command> COMMANDS_MAP;
    private Map<PlayerTags, InputComponent> inputHandlers;

    static {
        COMMANDS_MAP = ImmutableMap.<InputTypes, Command>builder()
                .put(InputTypes.SHOOT,             g -> findShooter(g).ifPresent(ShooterComponent::startShooting))
                .put(InputTypes.STOP_SHOOTING,     g -> findShooter(g).ifPresent(ShooterComponent::stopShooting))
                .put(InputTypes.MOVE_LEFT,         InputManagerImpl::moveLeft)
                .put(InputTypes.MOVE_RIGHT,        InputManagerImpl::moveRight)
                .put(InputTypes.STOP_MOVING_LEFT,  InputManagerImpl::stopMovingLeft)
                .put(InputTypes.STOP_MOVING_RIGHT, InputManagerImpl::stopMovingRight)
                .build();
    }

    /**
     * Class constructor.
     */
    public InputManagerImpl() {
        this.inputHandlers = ImmutableMap.of();
    }

    @Override
    public final void addInputHandler(final PlayerTags tag, final InputComponent inputComponent) {
        this.inputHandlers = ImmutableMap.<PlayerTags, InputComponent>builder()
                .putAll(this.inputHandlers)
                .put(tag, inputComponent)
                .build();
    }

    @Override
    public final void removeInputHandler(final PlayerTags tag) {
        this.inputHandlers = this.inputHandlers.entrySet().stream()
                .filter(e -> e.getKey() != tag)
                .collect(ImmutableMap.toImmutableMap(Entry::getKey, Entry::getValue));
    }

    @Override
    public final void processInputs(final PlayerTags tag, final List<InputTypes> inputs) {
        this.inputHandlers.get(tag).receiveCommands(this.translateInputs(inputs));
    }

    private List<Command> translateInputs(final List<InputTypes> toBeTranslated) {
        return toBeTranslated.stream().map(i -> COMMANDS_MAP.get(i)).collect(ImmutableList.toImmutableList());
    }

    private static Optional<ShooterComponent> findShooter(final GameObject g) {
        return g.getComponents().stream()
                .filter(c -> c.getType() == ComponentTypes.SHOOTER)
                .map(c -> (ShooterComponent) c).findFirst();
    }

    private static void stopMovingLeft(final GameObject g) {
        if (g.getVelocity().getX() < 0) {
            g.setVelocity(Vector2D.create(0, 0));
        }
    }

    private static void stopMovingRight(final GameObject g) {
        if (g.getVelocity().getX() > 0) {
            g.setVelocity(Vector2D.create(0, 0));
        }
    }

    private static void moveLeft(final GameObject g) {
        final Player player = ((Player) g);
        player.setVelocity(Vector2D.create(-player.getSpeed(), 0));
    }

    private static void moveRight(final GameObject g) {
        final Player player = ((Player) g);
        player.setVelocity(Vector2D.create(player.getSpeed(), 0));
    }
}
