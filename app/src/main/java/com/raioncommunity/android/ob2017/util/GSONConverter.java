package com.raioncommunity.android.ob2017.util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bradhawk on 9/25/2016.
 */

public class GSONConverter {

    public static <T> Set<String> fromListToSetString(List<T> lists) {
        Gson gson = new Gson();

        Set<String> stringSet;
        ArrayList<String> stringsToSave = new ArrayList<>();

        for(T list : lists) {
            stringsToSave.add(gson.toJson(list));
        }

        stringSet = new HashSet<>(stringsToSave);
        return stringSet;
    }

    public static <T> List<T> fromSetStringToList(Set<String> stringSet, Class<T> tClass) {
        Gson gson = new Gson();

        if (stringSet == null) {
            Log.d("STRING_SET_TAG", "fromSetStringToList: stringSet:" + stringSet);
        }

        List<T> returnValue = new ArrayList<>();
        for(String string : stringSet) {
            returnValue.add(gson.fromJson(string, tClass));
        }

        return returnValue;
    }

    public static <T> String fromObjectToString(T object) {
        Gson gson = new Gson();

        return gson.toJson(object);
    }

    public static <T> T fromStringToObject(String json, Class<T> tClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, tClass);
    }
}
