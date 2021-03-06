package org.motdinfo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;

/**
 * @author SmallasWater
 * Create on 2021/2/22 19:28
 * Package org.motdinfo.utils
 */
public class GetValue extends LinkedHashMap<String, Object> {


    public GetValue(String name,Object value){
        put(name, value);
    }

    GetValue(){}


    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this).replace("\"[","[").replace("]\"","]");

    }
}
