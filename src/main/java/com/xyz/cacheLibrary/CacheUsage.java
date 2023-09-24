package com.xyz.cacheLibrary;

import com.xyz.cacheLibrary.cacheImpl.BasicCache;
import com.xyz.cacheLibrary.evictionMechanismImpl.LRUEvictionPolicy;

public class CacheUsage {
    public static void main(String[] args) {
        Cache<String, Integer> scoreCache = new BasicCache<>();
        scoreCache.putValue("Sachin", 100);
        scoreCache.putValue("Rahul", 30);
        scoreCache.putValue("Virat", 300);
        System.out.println(scoreCache.getValue("Sachin"));
        System.out.println(scoreCache.getValue("Rahul"));
        System.out.println(scoreCache.getValue("Virat"));
        System.out.println("----------------------------------------------------");
        //create a limited size cache
        Cache<String, Integer> rankCache = new BasicCache<>(5, new LRUEvictionPolicy());
        rankCache.putValue("Ind", 1);
        rankCache.putValue("Aus", 2);
        rankCache.putValue("SA", 3);
        rankCache.putValue("ENG", 4);
        rankCache.putValue("WI", 5);
        System.out.println(rankCache.getValue("Ind"));
        System.out.println(rankCache.getValue("Aus"));
        System.out.println(rankCache.getValue("SA"));
        System.out.println(rankCache.getValue("ENG"));
        System.out.println(rankCache.getValue("WI"));
        System.out.println(rankCache.getValue("Ind"));
        System.out.println("----------------------------------------------------");
        rankCache.putValue("BNG", 10);
        System.out.println(rankCache.getValue("Ind"));
        System.out.println(rankCache.getValue("SA"));
        System.out.println(rankCache.getValue("ENG"));
        System.out.println(rankCache.getValue("WI"));
        System.out.println(rankCache.getValue("BNG"));
        try {
            System.out.println(rankCache.getValue("Aus"));
        } catch (Exception e) {
            System.out.println("Expect an exception");
        }
    }
}
