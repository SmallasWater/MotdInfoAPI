package org.motdinfo.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author SmallasWater
 * Create on 2021/2/22 19:38
 * Package org.motdinfo.utils
 */
public class WebData {

    private ArrayList<GetValue> data = new ArrayList<>();

    public WebData(GetValue getValue){
        put(getValue);
    }

    public WebData(){}

    public void putAll(GetValue... getValues){
        data.addAll(Arrays.asList(getValues));
    }

    public void put(GetValue getValue){
        data.add(getValue);
    }

    @Override
    public String toString() {
        if(data.size() > 0) {
            Gson gson = new Gson();
            return gson.toJson(data);
        }
        return "";
    }
}
