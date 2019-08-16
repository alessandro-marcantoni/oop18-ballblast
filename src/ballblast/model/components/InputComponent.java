package ballblast.model.components;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.model.inputs.Command;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * Makes a {@link GameObject} controllable through the use of inputs (for example via keyboard).
 */
public class InputComponent extends AbstractComponent {
    private final InputManager inputManager;
    private final PlayerTags tag;
    private ImmutableList<Command> commands;
    /**
     * Creats an {@link InputComponent} instance.
     * @param inputManager
     *     the {@link InputManager} used to received inputs.
     * @param tag
     *     the {@link PlayerTags} used to identifies the {@link InputComponent} inside the {@link InputManager}.
     */
    public InputComponent(final InputManager inputManager, final PlayerTags tag) {
        super(ComponentTypes.INPUT);
        this.inputManager = inputManager;
        this.tag = tag;
        this.commands = ImmutableList.of();
    }

    @Override
    public final void enable() {
        super.enable();
        this.inputManager.addInputHandler(tag, this);
    }

    @Override
    public final void disable() {
        super.disable();
        this.inputManager.removeInputHandler(this.tag);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.resolveCommands();
        }
    }
    /**
     * Receives {@link Command}s to be resolved.
     * @param toBeResolved
     *     the {@link List} of {@link Command}s to be resolved.
     */
    public final void receiveCommands(final List<Command> toBeResolved) {
        this.commands = ImmutableList.copyOf(toBeResolved);
    }

    private void resolveCommands() {
        this.commands.forEach(c -> c.execute(this.getParent()));
    }
}