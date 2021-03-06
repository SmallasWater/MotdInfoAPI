package org.motdinfo.utils;

import com.google.gson.Gson;


/**
 * @author SmallasWater
 * Create on 2021/2/22 19:38
 * Package org.motdinfo.utils
 */
public class WebData {

    private GetValue data;

    public WebData(GetValue data){
        this.data = data;
    }

    public WebData(){
        this.data = new GetValue();
    }

    public WebData put(String key,Object value){
        data.put(key, value);
        return this;
    }


    public GetValue getData() {
        return data;
    }



}
