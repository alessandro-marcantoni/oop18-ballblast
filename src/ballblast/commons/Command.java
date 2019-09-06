package ballblast.commons;

/**
 * It is used to wrap a request under an object as command and passed to invoker
 * object, followion the Command pattern.
 *
 * @param <T> the type of the object used to execute the command.
 */
@FunctionalInterface
public interface Command<T> {
    /**
     * Executes the command.
     * 
     * @param t the object used to handle this command.
     */
    void execute(T t);
}
