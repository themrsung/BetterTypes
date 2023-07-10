package oasis.bettertypes.collection.list;

import oasis.bettertypes.collection.set.BetterHashSet;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h2>BetterArrayList</h2>
 * <p>The default implementation of {@link BetterList}.</p>
 */
public class BetterArrayList<E> extends ArrayList<E> implements BetterList<E> {
    /**
     * Creates an empty list.
     */
    public BetterArrayList() {}

    /**
     * Creates a list from given entries.
     *
     * @param entries Entries
     */
    @SafeVarargs
    public BetterArrayList(E... entries) {
        super(List.of(entries));
    }

    /**
     * Creates a list from a collection.
     *
     * @param collection Collection
     */
    public BetterArrayList(@Nonnull Collection<? extends E> collection) {
        super(collection);
    }

    /**
     * Performs a shallow copy of given list.
     *
     * @param other List to copy
     */
    public BetterArrayList(@Nonnull BetterArrayList<E> other) {
        super(other);
    }

    @Nonnull
    @Override
    public BetterList<E> filter(@Nonnull Predicate<E> filter) {
        return new BetterArrayList<>(stream().filter(filter).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> BetterList<F> filter(@Nonnull Class<F> type) {
        return new BetterArrayList<>(stream().filter(type::isInstance).map(type::cast).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> BetterList<F> filter(@Nonnull Class<F> type, @Nonnull Predicate<F> filter) {
        return filter(type).filter(filter);
    }

    @Nonnull
    @Override
    public <F extends E> BetterList<F> filter(@Nonnull Predicate<E> filter, @Nonnull Class<F> type) {
        return filter(filter).filter(type);
    }

    @Nonnull
    @Override
    public <F extends E> Stream<F> map(@Nonnull Function<? super E, ? extends F> mapper) {
        return stream().map(mapper);
    }

    @Override
    public int uniqueSize() {
        return new BetterHashSet<>(this).size();
    }

    @Override
    public boolean contains(@Nonnull Predicate<E> filter, @Nonnull E entry) {
        return filter(filter).contains(entry);
    }
}
