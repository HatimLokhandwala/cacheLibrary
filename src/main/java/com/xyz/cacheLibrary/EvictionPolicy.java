package com.xyz.cacheLibrary;

public interface EvictionPolicy<K> {

    void logAccessToKey(K k);

    K getKeyToEvict();
}
