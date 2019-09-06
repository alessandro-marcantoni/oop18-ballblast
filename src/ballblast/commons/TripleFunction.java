package ballblast.commons;

/**
 * Is a functional interface and can therefore be used as the assignment
 * target for a lambda expression or method reference.
 * 
 * @param <A> the first type of the input to the operation.
 * @param <B> the second type of the input to the operation.
 * @param <C> the third type of the input to the operation.
 * @param <R> the the type of the result of the function.
 */
@FunctionalInterface
public interface TripleFunction<A, B, C, R> {
    /**
     * Performs this operation on the given argument.
     * 
     * @param a the first input argument.
     * @param b the second input argument.
     * @param c the third input argument.
     * @return the function result.
     */
    R apply(A a, B b, C c);
}
