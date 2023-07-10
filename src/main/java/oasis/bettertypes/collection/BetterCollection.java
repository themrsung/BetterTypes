package oasis.bettertypes.collection;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>BetterCollection</h2>
 * <p>{@link Collection} with additional features</p>
 */
public interface BetterCollection<E> extends Collection<E> {
    /**
     * Gets a filtered subcollection of this collection.
     *
     * @param filter Filter
     * @return Filtered collection
     */
    @Nonnull
    BetterCollection<E> filter(@Nonnull Predicate<E> filter);

    /**
     * Gets a filtered subcollection of this collection by class.
     *
     * @param type Class to get
     * @param <F>  Type of class to get
     * @return Filtered collection
     */
    @Nonnull
    <F extends E> BetterCollection<F> filter(@Nonnull Class<F> type);

    /**
     * Gets a filtered subcollection by type and filter.
     *
     * @param type   Class to get
     * @param filter Filter to apply
     * @param <F>    Type of class to get
     * @return Filtered collection
     */
    @Nonnull
    <F extends E> BetterCollection<F> filter(@Nonnull Class<F> type, @Nonnull Predicate<F> filter);

    /**
     * Gets a filters subcollection by filter, then casts the collection to given type.
     *
     * @param filter Filter to apply
     * @param type   Class to get
     * @param <F>    Type of class to get
     * @return Filtered collection
     */
    @Nonnull
    <F extends E> BetterCollection<F> filter(@Nonnull Predicate<E> filter, @Nonnull Class<F> type);

    /**
     * Gets a mapped stream of this collection.
     *
     * @param mapper Mapper to apply
     * @param <F>    Type of stream to get
     * @return Mapped stream
     */
    @Nonnull
    <F extends E> Stream<F> map(@Nonnull Function<? super E, ? extends F> mapper);

    /**
     * Gets the number of unique items in this collection.
     * Entries with the same signature will be considered duplicates and not counted.
     *
     * @return Unique size
     */
    @Nonnegative
    int uniqueSize();

    /**
     * Checks if the filtered collection contains given entry.
     *
     * @param filter Filter to apply
     * @param entry  Entry to query
     * @return {@code true} if the filtered collection contains given entry
     */
    boolean contains(@Nonnull Predicate<E> filter, @Nonnull E entry);
}
