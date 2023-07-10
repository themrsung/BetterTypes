package oasis.bettertypes.map;

import oasis.bettertypes.collection.BetterCollection;
import oasis.bettertypes.collection.set.BetterSet;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>BetterMap</h2>
 * <p>A {@link Map} with additional features.</p>
 */
public interface BetterMap<K, V> extends Map<K, V> {
    //
    // Additional features
    //

    /**
     * Gets the value of given key.
     * <p>
     * If the value is null, this will return the given default value.
     * If the default value is used, an entry with the given default value will be automatically put.
     * </p>
     * <p>
     * It is guaranteed that this will not return null as long as the default value is not null,
     * and it is also guaranteed that changes to the return value of this method will be reflected in this map.
     * </p>
     *
     * @param key          Key to query
     * @param defaultValue Default value to be put in key's place, then returned
     * @return Value of key if found, default value if not
     */
    @Nonnull
    V getOrDefaultPut(@Nonnull K key, @Nonnull V defaultValue);

    //
    // Overrides
    //

    /**
     * Gets a set of all keys in this map.
     *
     * @return {@link BetterSet}
     */
    @Nonnull
    @Override
    BetterSet<K> keySet();

    /**
     * Gets a set of entries in this map.
     *
     * @return {@link Entry}
     */
    @Nonnull
    @Override
    BetterSet<Map.Entry<K, V>> entrySet();

    /**
     * Gets a collection of values in this map.
     *
     * @return {@link BetterCollection}
     */
    @Nonnull
    @Override
    BetterCollection<V> values();

    //
    // Streams and filtering
    //

    /**
     * Gets a stream of {@link BetterMap#entrySet()}.
     *
     * @return {@link Stream}
     */
    @Nonnull
    Stream<Entry<K, V>> stream();

    /**
     * Gets a stream of {@link BetterMap#keySet()}.
     *
     * @return {@link Stream}
     */
    @Nonnull
    Stream<K> keyStream();

    /**
     * Gets a stream of {@link BetterMap#values()}.
     *
     * @return {@link Stream}
     */
    @Nonnull
    Stream<V> valueStream();

    /**
     * Gets a filtered map, filtered by entry.
     *
     * @param filter Filter to apply on entries
     * @return Filtered map
     */
    @Nonnull
    BetterMap<K, V> entryFilter(@Nonnull Predicate<Entry<K, V>> filter);

    /**
     * Gets a filtered map, filtered by key.
     *
     * @param filter Filter to apply on keys
     * @return Filtered map
     */
    @Nonnull
    BetterMap<K, V> keyFilter(@Nonnull Predicate<K> filter);

    /**
     * Gets a filtered map, filtered by value.
     *
     * @param filter Filter to apply on values
     * @return Filtered map
     */
    @Nonnull
    BetterMap<K, V> valueFilter(@Nonnull Predicate<V> filter);

    /**
     * Filters this map by class of value.
     * All entries with a value not an instance of given class will be removed.
     *
     * @param valueType Type of value to get
     * @param <W>       Type of value
     * @return Filtered map
     */
    @Nonnull
    <W extends V> BetterMap<K, W> valueFilter(@Nonnull Class<W> valueType);

    /**
     * Filters this map by class of key.
     * All entries with a key not an instance of given class will be removed.
     *
     * @param keyType Type of key to get
     * @param <L>     Type of value
     * @return Filtered map
     */
    @Nonnull
    <L extends K> BetterMap<K, V> keyFilter(@Nonnull Class<L> keyType);

    /**
     * Filters this map by class of entry.
     * All entries not an instance of given entry will be removed.
     *
     * @param entryType Type of entry to get
     * @param <L>       A subtype of {@link K}
     * @param <W>       A subtype of {@link V}
     * @return Filtered map
     */
    @Nonnull
    <L extends K, W extends V> BetterMap<L, W> filter(@Nonnull Class<Entry<L, W>> entryType);

    /**
     * Filters this map by class of entry and value.
     * All entries which to not match the signature will be removed.
     *
     * @param keyClass   Type of key to get
     * @param valueClass Type of value to get
     * @param <L>        A subtype of {@link K}
     * @param <W>        A subtype of {@link V}
     * @return Filtered map
     */
    @Nonnull
    <L extends K, W extends V> BetterMap<L, W> filter(@Nonnull Class<L> keyClass, @Nonnull Class<W> valueClass);
}
