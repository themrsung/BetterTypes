package oasis.bettertypes.collection.list;

import oasis.bettertypes.collection.BetterCollection;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

/**
 * <h2>BetterList</h2>
 * <p>A {@link List} with additional features.</p>
 */
public interface BetterList<E> extends BetterCollection<E>, List<E> {
    /**
     * Gets a filtered sublist of this list.
     *
     * @param filter Filter
     * @return Filtered list
     */
    @Nonnull
    @Override
    BetterList<E> filter(@Nonnull Predicate<E> filter);

    /**
     * Gets a filtered sublist of this list by class.
     *
     * @param type Class to get
     * @param <F>  Type of class to get
     * @return Filtered list
     */
    @Nonnull
    @Override
    <F extends E> BetterList<F> filter(@Nonnull Class<F> type);

    /**
     * Gets a filtered sublist of this list by type and filter.
     *
     * @param type   Class to get
     * @param filter Filter to apply
     * @param <F>    Type of class to get
     * @return Filtered list
     */
    @Nonnull
    @Override
    <F extends E> BetterList<F> filter(@Nonnull Class<F> type, @Nonnull Predicate<F> filter);

    /**
     * Gets a filtered sublist by given filter, then casts the list to given type.
     *
     * @param filter Filter to apply
     * @param type   Class to get
     * @param <F>    Type of class to get
     * @return Filtered list
     */
    @Nonnull
    @Override
    <F extends E> BetterList<F> filter(@Nonnull Predicate<E> filter, @Nonnull Class<F> type);
}
