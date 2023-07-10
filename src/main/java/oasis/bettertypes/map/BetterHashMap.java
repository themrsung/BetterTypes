package oasis.bettertypes.map;

import oasis.bettertypes.collection.BetterCollection;
import oasis.bettertypes.collection.list.BetterArrayList;
import oasis.bettertypes.collection.set.BetterHashSet;
import oasis.bettertypes.collection.set.BetterSet;

import javax.annotation.Nonnull;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>BetterHashMap</h2>
 * <p>The default implementation of {@link BetterMap}.</p>
 */
public class BetterHashMap<K, V> extends HashMap<K, V> implements BetterMap<K, V> {
    /**
     * Creates an empty map.
     */
    public BetterHashMap() {}

    /**
     * Creates a map from given entries.
     *
     * @param entries Entries
     */
    @SafeVarargs
    public BetterHashMap(@Nonnull Map.Entry<K, V>... entries) {
        super(Map.ofEntries(entries));
    }

    /**
     * Creates a map from given collection of entries.
     *
     * @param entries Collection of entries
     */
    public BetterHashMap(@Nonnull BetterCollection<Map.Entry<K, V>> entries) {
        entries.forEach(e -> put(e.getKey(), e.getValue()));
    }

    /**
     * Creates a new {@link BetterMap} from a default Java map.
     *
     * @param javaMap {@link Map}
     */
    public BetterHashMap(@Nonnull Map<K, V> javaMap) {
        super(javaMap);
    }

    /**
     * Creates a new map from a stream of entries.
     *
     * @param stream {@link Stream}
     */
    public BetterHashMap(@Nonnull Stream<Map.Entry<K, V>> stream) {
        stream.toList().forEach(e -> put(e.getKey(), e.getValue()));
    }

    /**
     * Performs a shallow copy of given map.
     *
     * @param other Map to copy
     */
    public BetterHashMap(@Nonnull BetterHashMap<K, V> other) {
        super(other);
    }

    @Override
    public BetterSet<Map.Entry<K, V>> entrySet() {
        return new BetterHashSet<>(super.entrySet());
    }

    @Override
    public BetterSet<K> keySet() {
        return new BetterHashSet<>(super.keySet());
    }

    @Override
    public BetterCollection<V> values() {
        return new BetterArrayList<>(super.values());
    }

    @Nonnull
    @Override
    public V getOrDefaultPut(@Nonnull K key, @Nonnull V defaultValue) {
        if (containsKey(key)) {
            return get(key);
        } else {
            put(key, defaultValue);
            return defaultValue;
        }
    }

    @Nonnull
    @Override
    public Stream<BetterMap.Entry<K, V>> stream() {
        return entrySet().stream();
    }

    @Nonnull
    @Override
    public Stream<K> keyStream() {
        return keySet().stream();
    }

    @Nonnull
    @Override
    public Stream<V> valueStream() {
        return values().stream();
    }

    @Nonnull
    @Override
    public BetterMap<K, V> filter(@Nonnull Predicate<BetterMap.Entry<K, V>> filter) {
        return new BetterHashMap<>(stream().filter(filter));
    }

    @Nonnull
    @Override
    public BetterMap<K, V> keyFilter(@Nonnull Predicate<K> filter) {
        return new BetterHashMap<>(stream().filter(e -> filter.test(e.getKey())));
    }

    @Nonnull
    @Override
    public BetterMap<K, V> valueFilter(@Nonnull Predicate<V> filter) {
        return new BetterHashMap<>(stream().filter(e -> filter.test(e.getValue())));
    }

    @Nonnull
    @Override
    public <W extends V> BetterMap<K, W> valueFilter(@Nonnull Class<W> valueType) {
        return new BetterHashMap<>(stream().filter(e -> valueType.isInstance(e.getValue()))
                .map(e -> new Entry<>(e.getKey(), valueType.cast(e.getValue()))));
    }

    @Nonnull
    @Override
    public <L extends K> BetterMap<K, V> keyFilter(@Nonnull Class<L> keyType) {
        return new BetterHashMap<>(stream().filter(e -> keyType.isInstance(e.getKey()))
                .map(e -> new Entry<>(keyType.cast(e.getKey()), e.getValue())));
    }

    @Nonnull
    @Override
    public <L extends K, W extends V> BetterMap<L, W> filter(@Nonnull Class<BetterMap.Entry<L, W>> entryType) {
        return new BetterHashMap<>(stream().filter(entryType::isInstance).map(entryType::cast));
    }

    @Nonnull
    @Override
    public <L extends K, W extends V> BetterMap<L, W> filter(@Nonnull Class<L> keyClass, @Nonnull Class<W> valueClass) {
        return new BetterHashMap<>(stream().filter(e -> keyClass.isInstance(e.getKey()) && valueClass.isInstance(e.getValue()))
                .map(e -> new Entry<>(keyClass.cast(e.getKey()), valueClass.cast(e.getClass()))));
    }

    /**
     * Default BetterImplementation of {@link Map.Entry}.
     *
     * @param <K> Class of key
     * @param <V> Class of value
     */
    public static class Entry<K, V> extends AbstractMap.SimpleEntry<K, V> implements Map.Entry<K, V> {
        public Entry(K key, V value) {
            super(key, value);
        }

        public Entry(Map.Entry<? extends K, ? extends V> entry) {
            super(entry);
        }
    }
}
