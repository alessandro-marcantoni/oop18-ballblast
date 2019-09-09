package ballblast.commons;

import ballblast.model.gameobjects.GameObject;

/**
 * It is used to wrap a request under an object as command and passed to invoker
 * object, following the Command pattern.
 *
 */
@FunctionalInterface
public interface Command {
    /**
     * Executes the command.
     * 
     * @param gameObject the {@link GameObject} which handles the command.
     */
    void execute(GameObject gameObject);
}
