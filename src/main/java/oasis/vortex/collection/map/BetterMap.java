package oasis.vortex.collection.map;

import oasis.vortex.collection.BetterCollection;
import oasis.vortex.collection.set.BetterSet;

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
     *     It is guaranteed that this will not return null as long as the default value is not null,
     *     and it is also guaranteed that changed to the return value of this method will be reflected in this map.
     * </p>
     *
     * @param key Key to query
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
     * @return {@link BetterSet}
     */
    @Nonnull
    @Override
    BetterSet<K> keySet();

    /**
     * Gets a set of entries in this map.
     * @return {@link Entry}
     */
    @Nonnull
    @Override
    BetterSet<Map.Entry<K, V>> entrySet();

    /**
     * Gets a collection of values in this map.
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
     * @return {@link Stream}
     */
    @Nonnull
    Stream<Entry<K, V>> entryStream();

    /**
     * Gets a stream of {@link BetterMap#keySet()}.
     * @return {@link Stream}
     */
    @Nonnull
    Stream<K> keyStream();

    /**
     * Gets a stream of {@link BetterMap#values()}.
     * @return {@link Stream}
     */
    @Nonnull
    Stream<V> valueStream();

    /**
     * Gets a filtered collection of entries.
     * @param filter Filter to apply
     * @return Filtered collection
     */
    @Nonnull
    BetterCollection<Entry<K, V>> entryFilter(@Nonnull Predicate<Entry<K, V>> filter);




    interface Entry<K, V> extends Map.Entry<K, V> {

    }
}
