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
 * Manages inputs and redirects them to the right {@link Player}.
 */
public class InputManager {
    private static final Map<InputTypes, Command<GameObject>> COMMANDS_MAP;
    private Map<PlayerTags, InputComponent> inputHandlers;

    static {
        COMMANDS_MAP = ImmutableMap.<InputTypes, Command<GameObject>>builder()
                .put(InputTypes.SHOOT,             g -> findShooter(g).ifPresent(ShooterComponent::startShooting))
                .put(InputTypes.STOP_SHOOTING,     g -> findShooter(g).ifPresent(ShooterComponent::stopShooting))
                .put(InputTypes.MOVE_LEFT,         InputManager::moveLeft)
                .put(InputTypes.MOVE_RIGHT,        InputManager::moveRight)
                .put(InputTypes.STOP_MOVING_LEFT,  InputManager::stopMovingLeft)
                .put(InputTypes.STOP_MOVING_RIGHT, InputManager::stopMovingRight)
                .build();
    }

    /**
     * Class constructor.
     */
    public InputManager() {
        this.inputHandlers = ImmutableMap.of();
    }

    /**
     * Adds a inputHandler for a new {@link Player}.
     * 
     * @param tag            the tag identifies a specific {@link Player}.
     * @param inputComponent the {@link InputComponent} of a specific
     *                       {@link Player}.
     */
    public void addInputHandler(final PlayerTags tag, final InputComponent inputComponent) {
        this.inputHandlers = ImmutableMap.<PlayerTags, InputComponent>builder()
                .putAll(this.inputHandlers)
                .put(tag, inputComponent)
                .build();
    }

    /**
     * Removes a inputHandler associated with a specific {@link PlayerTags}.
     * 
     * @param tag the tag which identifies the inputhandler to be removed.
     */
    public void removeInputHandler(final PlayerTags tag) {
        this.inputHandlers = this.inputHandlers.entrySet().stream()
                .filter(e -> e.getKey() != tag)
                .collect(ImmutableMap.toImmutableMap(Entry::getKey, Entry::getValue));
    }

    /**
     * Translates received inputs into {@link Command}s and sends them to the right
     * {@link InputComponent}.
     * 
     * @param tag    the tag which identifes the right {@link Player}.
     * @param inputs the inputs to be translated.
     */
    public void processInputs(final PlayerTags tag, final List<InputTypes> inputs) {
        this.inputHandlers.get(tag).receiveCommands(this.translateInputs(inputs));
    }

    private List<Command<GameObject>> translateInputs(final List<InputTypes> toBeTranslated) {
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
    /**
     * All possible {@link Player}s.
     */
    public enum PlayerTags {
        /**
         * First.
         */
        FIRST,
        /**
         * Second.
         */
        SECOND;
    }
}
