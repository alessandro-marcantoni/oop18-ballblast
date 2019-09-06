package ballblast.model.components;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.commons.Command;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManager.PlayerTags;

/**
 * Makes a {@link GameObject} controllable through the use of inputs (for
 * example via keyboard).
 */
public class InputComponent extends AbstractComponent {
    private final InputManager inputManager;
    private final PlayerTags tag;
    private List<Command<GameObject>> commands;

    /**
     * Class constructor.
     * 
     * @param inputManager the {@link InputManager} used to received inputs.
     * @param tag          the {@link PlayerTags} used to identifies the
     *                     {@link InputComponent} inside the {@link InputManager}.
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
     * 
     * @param list the {@link List} of {@link Command}s to be resolved.
     */
    public final void receiveCommands(final List<Command<GameObject>> list) {
        this.commands = ImmutableList.copyOf(list);
    }

    private void resolveCommands() {
        this.commands.forEach(c -> c.execute(this.getParent()));
        this.emptyCommands();
    }

    private void emptyCommands() {
        this.commands = ImmutableList.of();
    }
}
