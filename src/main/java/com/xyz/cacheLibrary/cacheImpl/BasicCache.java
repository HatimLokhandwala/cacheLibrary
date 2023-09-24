package com.xyz.cacheLibrary.cacheImpl;


import com.xyz.cacheLibrary.Cache;
import com.xyz.cacheLibrary.EvictionPolicy;
import com.xyz.cacheLibrary.evictionMechanismImpl.LRUEvictionPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class BasicCache<K, V> implements Cache<K, V> {
    private final int cacheCapacity;
    private final EvictionPolicy evictionPolicy;
    private final Map<K, V> CACHE_MAP = new HashMap<>();

    /**
     * Creates a cache with infinite capacity and LRU eviction policy by default
     */
    public BasicCache() {
        this.cacheCapacity = Integer.MAX_VALUE;
        this.evictionPolicy = new LRUEvictionPolicy();
    }

    public BasicCache(final int capacity, final EvictionPolicy evictionPolicy) {
        Objects.requireNonNull(evictionPolicy);
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cachec capacity should be non zero ");
        }
        this.cacheCapacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    /**
     * Gets value corresponding to a given key. if the key is present in cache.
     * @param lookUpKey
     * @return value corresponding to the key, if present, throws exception otherwise
     */
    @Override
    public V getValue(K lookUpKey) {
        if (CACHE_MAP.containsKey(lookUpKey)) {
            evictionPolicy.logAccessToKey(lookUpKey);
            return CACHE_MAP.get(lookUpKey);
        }
        throw new IllegalArgumentException("There is no such key  " +  lookUpKey + " present in cache");
    }

    /**
     * Adds a pair K , V to cache
     * If the cache is full, evicts a key as per eviction policy configured.
     * @param keyToAdd
     * @param mappingValue
     * @return
     */
    @Override
    public void putValue(final K keyToAdd, final V mappingValue) {
        if (CACHE_MAP.size() >= cacheCapacity) {
            K keyToBeEvicted = (K)evictionPolicy.getKeyToEvict();
            //add log line for key to be evicted
            CACHE_MAP.remove(keyToBeEvicted);
        }
        //if the key is already present, value would be overridden
        CACHE_MAP.put(keyToAdd , mappingValue);
        evictionPolicy.logAccessToKey(keyToAdd);
    }
}
