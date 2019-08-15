package ballblast.model.inputs;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;

import ballblast.model.components.ComponentTypes;
import ballblast.model.components.InputComponent;
import ballblast.model.components.ShooterComponent;
/**
 * Manages inputs and redirects them to the right player.
 */
public class InputManager {
    private static final ImmutableBiMap<InputTypes, Command> COMMANDS_MAP;
    private static final int MOVEMENT_SPEED = 5;
    private ImmutableBiMap<PlayerTags, InputComponent> inputHandlers;

    static {
        COMMANDS_MAP = ImmutableBiMap.of(
                InputTypes.MOVE_LEFT, g -> g.setVelocity(Vector2D.create(-MOVEMENT_SPEED, 0)),
                InputTypes.MOVE_RIGHT, g -> g.setVelocity(Vector2D.create(MOVEMENT_SPEED, 0)), 
                InputTypes.SHOOT, g -> g.getComponents().stream().filter(c -> c.getType() == ComponentTypes.SHOOTER)
                    .map(c -> (ShooterComponent) c).findFirst().ifPresent(c -> c.setShootingState(true)));
    }

    /**
     * Creates a {@link InputManager} instance.
     */
    public InputManager() {
        this.inputHandlers = ImmutableBiMap.of();
    }

    /**
     * Adds a inputHandler for a new {@link Player}.
     * 
     * @param tag            the tag identifies a specific {@link Player}.
     * @param inputComponent the {@link InputComponent} of a specific
     *                       {@link Player}.
     */
    public void addInputHandler(final PlayerTags tag, final InputComponent inputComponent) {
        this.inputHandlers = ImmutableBiMap.<PlayerTags, InputComponent>builder().putAll(this.inputHandlers)
                .put(tag, inputComponent).build();
    }

    /**
     * Removes a inputHandler associated with a specific {@link PlayerTags}.
     * 
     * @param tag the tag which identifies the inputhandler to be removed.
     */
    public void removeInputHandler(final PlayerTags tag) {
        this.inputHandlers = this.inputHandlers.entrySet().stream().filter(e -> e.getKey() != tag)
                .collect(ImmutableBiMap.toImmutableBiMap(Entry::getKey, Entry::getValue));
    }

    /**
     * Translates received inputs into {@link Command}s and sends them to the right
     * {@link InputComponent}.
     * 
     * @param inputs the inputs to be trnasled.
     */
    public void processInputs(final Map<PlayerTags, List<InputTypes>> inputs) {
        inputs.forEach((k, v) -> this.inputHandlers.get(k).receiveCommands(this.translateInputs(v)));
    }

    private List<Command> translateInputs(final List<InputTypes> toBeTranslated) {
        return toBeTranslated.stream().map(i -> COMMANDS_MAP.get(i)).collect(ImmutableList.toImmutableList());
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
