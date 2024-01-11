package org.example.guess.tools;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    static List<String> cacheTitles  = new ArrayList<>();
    static String status;
    static int countRequests;

    public GameManager(){
        status = "";
        countRequests = 0;
    }

    public static void addInCacheTitle(String info){
        cacheTitles.add(info);
    }

    public static boolean containsInCache(String cacheItem){
        return cacheTitles.contains(cacheItem);
    }

    public static void setCacheTitles(List<String> cacheTitles) {
        GameManager.cacheTitles = cacheTitles;
    }

    public static List<String> getCacheTitles(){
        return cacheTitles;
    }

    public static void resetCacheTitles(){
        for (int i = 0; i<cacheTitles.size(); i++){
            cacheTitles.add(i, null);
        }
    }

    public static void setStatus(String s){
        status = s;
    }

    public static String getStatus(){
        return status;
    }

    public static int getCountRequests() {
        return countRequests;
    }

    public static void setCountRequests() {
        countRequests ++;
    }

    public static void resetCountRequests(){
        countRequests = 0;
    }
}






