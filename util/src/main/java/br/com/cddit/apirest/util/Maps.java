package br.com.cddit.apirest.util;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maps {

	private Maps() {
	}

	public static <K, V> MapBuilder<K, V> builder() {
		return builder(HashMap::new);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> MapBuilder<K, V> builderUnmodified() {
		final MapBuilder<Object, Object> builder = builder(HashMap::new);
		return (MapBuilder<K, V>) builder.unmodifiable(true).build();

	}

	public static <K, V> MapBuilder<K, V> builder(final Supplier<Map<K, V>> mapSupplier) {
		return new MapBuilder<>(mapSupplier.get());
	}

	public static <K, V> ConcurrentMapBuilder<K, V> concurrentBuilder() {
		return concurrentBuilder(ConcurrentHashMap::new);
	}

	public static <K, V> ConcurrentMapBuilder<K, V> concurrentBuilder(final Supplier<ConcurrentMap<K, V>> mapSupplier) {
		return new ConcurrentMapBuilder<>(mapSupplier.get());
	}

	public static <K, V> Map.Entry<K, V> entry(final K key, final V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	public static <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> entriesToMap() {
		return Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue());
	}

	public static <K, U> Collector<Map.Entry<K, U>, ?, ConcurrentMap<K, U>> entriesToConcurrentMap() {
		return Collectors.toConcurrentMap((e) -> e.getKey(), (e) -> e.getValue());
	}

	private static class BaseBuilder<M extends Map<K, V>, K, V> {

		protected final M map;

		public BaseBuilder(final M map) {
			this.map = map;
		}

		public BaseBuilder<M, K, V> put(final K key, final V value) {
			map.put(key, value);
			return this;
		}

		public M build() {
			return map;
		}

	}

	public static class MapBuilder<K, V> extends BaseBuilder<Map<K, V>, K, V> {

		private boolean unmodifiable;

		public MapBuilder(final Map<K, V> map) {
			super(map);
		}

		@Override
		public MapBuilder<K, V> put(final K key, final V value) {
			super.put(key, value);
			return this;
		}

		public MapBuilder<K, V> unmodifiable(final boolean unmodifiable) {
			this.unmodifiable = unmodifiable;
			return this;
		}

		@Override
		public Map<K, V> build() {
			if (unmodifiable) {
				return Collections.unmodifiableMap(super.build());
			} else {
				return super.build();
			}
		}

	}

	public static class ConcurrentMapBuilder<K, V> extends BaseBuilder<ConcurrentMap<K, V>, K, V> {

		public ConcurrentMapBuilder(final ConcurrentMap<K, V> map) {
			super(map);
		}

		@Override
		public ConcurrentMapBuilder<K, V> put(final K key, final V value) {
			super.put(key, value);
			return this;
		}

	}

	// JUST FOR EXAMPLE, SHOULD REMOVE SOON
	protected static Map<Integer, String> extJava8() {
		return Collections.unmodifiableMap(Stream.of(
				entry(0, "zero"),
				entry(1, "one"),
				entry(2, "two"),
				entry(3, "three"),
				entry(4, "four"),
				entry(5, "five"),
				entry(6, "six"),
				entry(7, "seven"),
				entry(8, "eight"),
				entry(9, "nine"),
				entry(10, "ten"),
				entry(11, "eleven"),
				entry(12, "twelve")).collect(entriesToMap()));
	}
}
