package ballblast.commons;

/**
 * This is a functional interface and can therefore be used as the assignment
 * target for a lambda expression or method reference.
 * 
 * @param <A> the first type of the input to the operation.
 * @param <B> the second type of the input to the operation.
 * @param <C> the third type of the input to the operation.
 */
@FunctionalInterface
public interface TripleConsumer<A, B, C> {
    /**
     * Performs this operation on the given argument.
     * 
     * @param a the first input argument.
     * @param b the second input argument.
     * @param c the third input argument.
     */
    void accept(A a, B b, C c);
}
