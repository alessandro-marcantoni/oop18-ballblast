package ballblast.commons;

/**
 * 
 * @author albiunix
 *
 * @param <T>
 */
@FunctionalInterface
public interface Command<T> {
    /**
     * Executes the command.
     */
    void execute(T t);
}
