package ballblast.model.inputs;

import ballblast.model.gameobjects.GameObject;
/**
 * Represents a command to be performed, following the Command pattern.
 */
@FunctionalInterface
public interface Command {
    /**
     * Perfomrs the {@link Command}.
     * @param gameObject
     *     the {@link GameObject} to be modified.
     */
    void execute(GameObject gameObject);
}
