package oasis.vortex.collection.set;

import oasis.vortex.collection.BetterCollection;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.Predicate;

/**
 * <h2>BetterSet</h2>
 * <p>{@link Set} with additional features.</p>
 */
public interface BetterSet<E> extends BetterCollection<E>, Set<E> {
    /**
     * Gets a filtered subset of this set.
     *
     * @param filter Filter
     * @return Filtered set
     */
    @Nonnull
    @Override
    BetterSet<E> filter(@Nonnull Predicate<E> filter);

    /**
     * Gets a filtered subset of this set by class.
     *
     * @param type Class to get
     * @param <F>  Type of class to get
     * @return Filtered set
     */
    @Nonnull
    @Override
    <F extends E> BetterSet<F> filter(@Nonnull Class<F> type);

    /**
     * Gets a filtered subset of this list by type and filter.
     *
     * @param type   Class to get
     * @param filter Filter to apply
     * @param <F>    Type of class to get
     * @return Filtered set
     */
    @Nonnull
    @Override
    <F extends E> BetterSet<F> filter(@Nonnull Class<F> type, @Nonnull Predicate<F> filter);

    /**
     * Gets a filtered subset by given filter, then casts the set to given type.
     *
     * @param filter Filter to apply
     * @param type   Class to get
     * @param <F>    Type of class to get
     * @return Filtered set
     */
    @Nonnull
    @Override
    <F extends E> BetterSet<F> filter(@Nonnull Predicate<E> filter, @Nonnull Class<F> type);
}
