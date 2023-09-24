package com.xyz.cacheLibrary.evictionMechanismImpl;

import com.xyz.cacheLibrary.EvictionPolicy;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Deque<K> deque = new LinkedList<>();

    /**
     * Logs down an access to key
     * @param k
     */
    @Override
    public void logAccessToKey(K k) {
        searchAndRemoveKey(k);
        //add the accessed key at the head of the queue.
        deque.addFirst(k);
    }

    private void searchAndRemoveKey(K keyToRemove) {
        Iterator it = deque.iterator();
        while (it.hasNext()) {
            K currentKey = (K)it.next();
            if (currentKey == keyToRemove) {
                deque.remove(currentKey);
                break;
            }
        }
    }

    /**
     * determine the key to be removed
     * @param
     */
    @Override
    public K getKeyToEvict() {
        //remove the key which is at the tail of the queue.
        if (deque.isEmpty()) {
            throw new IllegalStateException("There is nothing to be evicted");
        }
        return deque.getLast();
    }
}
