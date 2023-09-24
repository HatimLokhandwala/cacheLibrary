package com.xyz.cacheLibrary;

public interface Cache<K, V> {
    V getValue(K k);
    void putValue(K k, V v);
}
