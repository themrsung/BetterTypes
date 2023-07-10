package oasis.bettertypes.collection.set;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h2>BetterHashSet</h2>
 * <p>The default implementation of {@link BetterSet}.</p>
 */
public class BetterHashSet<E> extends HashSet<E> implements BetterSet<E> {
    /**
     * Creates an empty set.
     */
    public BetterHashSet() {}

    /**
     * Creates a set from given entries.
     *
     * @param entries Entries
     */
    @SafeVarargs
    public BetterHashSet(E... entries) {
        super(Set.of(entries));
    }

    /**
     * Creates a set from a collection.
     *
     * @param collection Collection
     */
    public BetterHashSet(@Nonnull Collection<? extends E> collection) {
        super(collection);
    }

    /**
     * Performs a shallow copy of given set.
     *
     * @param other Set to copy
     */
    public BetterHashSet(@Nonnull BetterHashSet<E> other) {
        super(other);
    }

    @Nonnull
    @Override
    public BetterHashSet<E> filter(@Nonnull Predicate<E> filter) {
        return new BetterHashSet<>(stream().filter(filter).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> BetterHashSet<F> filter(@Nonnull Class<F> type) {
        return new BetterHashSet<>(stream().filter(type::isInstance).map(type::cast).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> BetterHashSet<F> filter(@Nonnull Class<F> type, @Nonnull Predicate<F> filter) {
        return filter(type).filter(filter);
    }

    @Nonnull
    @Override
    public <F extends E> BetterHashSet<F> filter(@Nonnull Predicate<E> filter, @Nonnull Class<F> type) {
        return filter(filter).filter(type);
    }

    @Nonnull
    @Override
    public <F extends E> Stream<F> map(@Nonnull Function<? super E, ? extends F> mapper) {
        return stream().map(mapper);
    }

    @Override
    public int uniqueSize() {
        return size();
    }

    @Override
    public boolean contains(@Nonnull Predicate<E> filter, @Nonnull E entry) {
        return filter(filter).contains(entry);
    }
}
